//Toutes les methodes QUI NE SONT PAS DE SIMPE GETTER ont une javadoc
package ca.qc.bdeb.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe groupe
 *
 * @author Batikan & Nicolas
 */
public class Groupe {

    private Modele modele;

    private String code;

    private Professeur professeur;

    private String information;

    private boolean informationsModifies = false;

    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();

    private String host = "jdbc:derby://localhost:1527/Jeux de bio DB";
    private String uName = "JeuxDeBio";
    private String uPass = "mot_de_passe0";

    public Groupe(String information, Modele modele, Professeur professeur) {
        this.information = information;
        this.modele = modele;
        this.professeur = professeur;
        lectureInformation();
    }

    /**
     * Lecture de la base de donnees
     */
    private void lectureInformation() {
        System.out.println(information);
        try {
            String SQL = "SELECT * FROM GROUPE";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString("CODE").equals(information)) {
                    String[] splitDa = rs.getString("LISTEETUDIANTS").split(";");
                    for (int i = 0; i < modele.getListeEtudiants().size(); i++) {
                        for (int j = 0; j < splitDa.length; j++) {
                            if (splitDa[j].equals(modele.getListeEtudiants().get(i).getDa())) {
                                modele.getListeEtudiants().get(i).setGroupe(this);
                                modele.getListeEtudiants().get(i).setProfesseur(professeur);
                                listeEtudiants.add(modele.getListeEtudiants().get(i));
                            }
                        }
                    }
                }

            }
            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public String getCode() {
        return code;
    }

    public String getInformation() {
        return information;
    }

    /**
     * Ajouter l'etudiant fourni dans le groupe
     *
     * @param etudiant a ajouter
     */
    public void ajouterEtudiant(Etudiant etudiant) {
        listeEtudiants.add(etudiant);
        informationsModifies = true;
    }

    /**
     * Enlever l'etudiant fourni du groupe
     *
     * @param etudiant l'etudiant a enlever
     */
    public void enleverEtudiant(Etudiant etudiant) {
        listeEtudiants.remove(etudiant);
        informationsModifies = true;
    }

    public boolean informationsModifies() {
        return informationsModifies;
    }

    /**
     * Update la base de donnees
     */
    public void updateFichierGroupe() {

        try {
            String SQL = "SELECT * FROM GROUPE";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);
            String etudiants = "";
            for (int i = 0; i < listeEtudiants.size(); i++) {
                etudiants += listeEtudiants.get(i);
                if (i < listeEtudiants.size() - 1) {
                    etudiants += ";";
                }
            }
            while (rs.next()) {
                if (rs.getString("CODE").equals(information)) {
                    rs.updateString("LISTEETUDIANTS", etudiants);
                }
            }
            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public ArrayList<Etudiant> getListeEtudiants() {
        return listeEtudiants;
    }

}
