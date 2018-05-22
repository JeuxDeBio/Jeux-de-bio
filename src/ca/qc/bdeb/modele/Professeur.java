//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Batikan
 */
public class Professeur {

    private Modele modele;

    private String information;
    private String nom;
    private String nomUtilisateur;
    private String motDePasse;
    private String locationIcone;
    private String session;

    private ArrayList<String> listeDAPermis = new ArrayList<>();
    private ArrayList<Groupe> listeDAPermisGroupe = new ArrayList<>();
    private ArrayList<Groupe> listeGroupes = new ArrayList<>();

    private boolean informationsModifies = false;

    private TypeUtilisateur type = TypeUtilisateur.PROFESSEUR;

    private String host = "jdbc:derby://localhost:1527/Jeux de bio DB";
    private String uName = "JeuxDeBio";
    private String uPass = "mot_de_passe0";

    private boolean estAdmin = false;

    public Professeur(String nomUtilisateur, Modele modele) {
        this.nomUtilisateur = nomUtilisateur;
        this.modele = modele;
        lectureInformation();
    }

    public Professeur(String nu, String mdp, String nom, String session) {
        creerFichiers(nu, mdp, nom, session);
    }

    /**
     * Lit les donnees de la base de donnees
     */
    private void lectureInformation() {
        try {

            String SQL = "SELECT * FROM PROFESSEUR";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString("NU").equals(nomUtilisateur)) {

                    nom = rs.getString("NOM");
                    motDePasse = rs.getString("MOTDEPASSE");
                    if (rs.getBoolean("ESTADMIN")) {
                        estAdmin = true;
                        type = TypeUtilisateur.ADMIN;
                    }
                    locationIcone = rs.getString("LOCATIONICONE");
                    session = rs.getString("SESSION");

                    String[] splitGroupe = rs.getString("LISTEGROUPES").split(";");
                    for (int i = 0; i < splitGroupe.length; i++) {
                        listeGroupes.add(new Groupe(nomUtilisateur + ";" + splitGroupe[i], modele, this));
                    }
                }
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        lectureDAPermis();
    }

    /**
     * Lit les DA permis pour le professeur
     */
    private void lectureDAPermis() {
        try {

            String SQL = "SELECT * FROM DAPERMIS";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            String[] splitDa = rs.getString("DAPERMIS").split(";");
            while (rs.next()) {
                listeDAPermis.add(splitDa[0]);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getLocationIcone() {
        return locationIcone;
    }

    public String getSession() {
        return session;
    }

    public String getInformation() {
        return information;
    }

    public TypeUtilisateur getType() {
        return type;
    }

    public ArrayList<Groupe> getListeGroupes() {
        return listeGroupes;
    }

    /**
     * Verifie si le DA fourni est permis par le professeur
     *
     * @param da le DA fourni
     * @return si le DA fourni est permis
     */
    public boolean etudiantPermis(String da) {
        boolean etudiantPermis = false;
        for (int i = 0; i < listeDAPermis.size(); i++) {
            if (da.equals(listeDAPermis.get(i))) {
                etudiantPermis = true;
            }
        }
        return etudiantPermis;
    }

    public Groupe getGroupeNouveauEtudiant(String da) {
        return listeDAPermisGroupe.get(listeDAPermis.indexOf(da));
    }

    public void setMotDePasse(String nouveauMDP) {
        motDePasse = nouveauMDP;
        informationsModifies = true;
    }

    public void setLocationIcone(String locationIcone) {
        this.locationIcone = locationIcone;
        informationsModifies = true;
    }

    /**
     * Ajouter le groupe fourni parmi les groupes du professeur
     *
     * @param information Fichier texte du nouveau groupe
     */
    public void ajouterGroupe(String information) {
        Groupe groupe = new Groupe(information, modele, this);
        listeGroupes.add(groupe);
        informationsModifies = true;
    }

    public void updateFichierProfesseur() {
        try {
            String SQL = "SELECT * FROM PROFESSEUR";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                if (rs.getString("NU").equals(nomUtilisateur)) {
                    rs.updateString("MOTDEPASSE", motDePasse);
                    rs.updateString("LOCATIONICONE", locationIcone);
                    rs.updateString("SESSION", session);
                    rs.updateString("NOM", nom);
                    rs.updateBoolean("ESTADMIN", estAdmin);

                    String stringGroupe = "";
                    for (int i = 0; i < listeGroupes.size(); i++) {
                        stringGroupe += listeGroupes.get(i).getCode();
                        if (i != listeGroupes.size() - 1) {
                            stringGroupe += ";";
                        }
                    }
                    rs.updateString("LISTEGRPOUPES", stringGroupe);
                    rs.updateRow();
                }
            }

            stmt.close();
            rs.close();

            SQL = "SELECT * FROM DAPERMIS";
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public boolean informationsModifies() {
        return informationsModifies;
    }

    public void setAdmin() {
        this.estAdmin = true;
        this.type = TypeUtilisateur.ADMIN;
        informationsModifies = true;
    }

    public void enleverAdmin() {
        this.estAdmin = false;
        this.type = TypeUtilisateur.PROFESSEUR;
        informationsModifies = true;
    }

    public boolean estAdmin() {
        return estAdmin;
    }

    public void creerFichiers(String nu, String mdp, String nom, String session) {
        try {
            String SQL = "SELECT * FROM DAPERMIS";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                for (String da : listeDAPermis) {
                    if (rs.getString("DAPERMIS").equals(da)) {
                        listeDAPermis.remove(da);
                    }
                }
            }
            for (int i = 0; i < listeDAPermis.size(); i++) {
                rs.insertRow();
                rs.updateString("DAPERMIS", listeDAPermis.get(i));
                rs.updateRow();
            }

            stmt.close();
            rs.close();

            SQL = "SELECT * FROM PROFESSEUR";
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);

            rs.updateString("NU", nu);
            rs.updateString("MOTDEPASSE", mdp);
            rs.updateString("NOM", nom);
            rs.updateBoolean("ESTADMIN", !false);
            rs.updateString("LOCATIONICONE", null);
            rs.updateString("SESSION", session);
            rs.updateString("LISTEGROUPES", null);

            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        information = "Utilisateurs\\Professeurs\\" + nu + ".txt";
    }

    /**
     * Enlever l'etudiant de la liste de DA permis
     *
     * @param etudiant l'etudiant a enlever
     */
    public void updateDAPermisEnleverEtudiant(Etudiant etudiant) {
        try {
            String SQL = "SELECT * FROM DAPERMIS";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            for (int i = 0; i < listeDAPermis.size(); i++) {
                if (listeDAPermis.get(i).equals(etudiant.getDa())) {

                    while (rs.next()) {
                        if (rs.getString("DAPERMIS").equals(listeDAPermis.get(i))) {
                            rs.deleteRow();
                        }
                    }
                    listeDAPermis.remove(listeDAPermis.get(i));
                    listeDAPermisGroupe.remove(listeDAPermisGroupe.get(i));

                }
            }

            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Enlever groupe fourni
     *
     * @param groupe le groupe a enlever
     */
    public void updateInformationsEnleverGroupe(Groupe groupe) {
        listeGroupes.remove(groupe);

        ArrayList<Integer> listeAEnlever = new ArrayList<>();

        for (int i = 0; i < listeDAPermisGroupe.size(); i++) {
            if (listeDAPermisGroupe.get(i).getCode().equals(groupe.getCode())) {
                listeAEnlever.add(i);
            }
        }
        try {
            String SQL = "SELECT * FROM DAPERMIS";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);
            String prof = "";
            for (int i = listeDAPermisGroupe.size(); i >= 0; i--) {
                if (listeAEnlever.contains(i)) {
                    while (!rs.getString("GROUPE").equals(listeDAPermisGroupe.get(i))) {
                        rs.next();
                    }
                    prof = rs.getString("PROF");
                    rs.deleteRow();
                }
            }
            stmt.close();
            rs.close();
            SQL = "SELECT * FROM PROFESSEUR";
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            String[] splitListeGroupe;
            String groupes = "";
            while (!rs.getString("NU").equals(prof)) {
                rs.next();
            }

            for (int i = listeDAPermisGroupe.size(); i >= 0; i--) {
                if (listeAEnlever.contains(i)) {

                    splitListeGroupe = rs.getString("LISTEGROUPE").split(";");
                    for (int j = 0; j < splitListeGroupe.length; j++) {
                        if (!listeDAPermis.equals(splitListeGroupe[j])) {
                            groupes += splitListeGroupe[j];
                        }

                    }
                    rs.updateString("LISTEGROUPES", groupes);
                    listeDAPermis.remove(listeDAPermis.get(i));
                    listeDAPermisGroupe.remove(listeDAPermisGroupe.get(i));
                }
            }

            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        updateFichierProfesseur();
    }

}
