//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.modele;

import ca.qc.bdeb.vue.dragDrop.BoiteReponseConstruction;
import ca.qc.bdeb.vue.principale.Icone;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1649904
 */
public class Modele extends Observable {

    private final String locationMessagesErreurs = "Utilisateurs\\listeMessagesErreurs.txt";

    private final String locationFenetrePrincipale = "Ecrans\\Principale\\FenetrePrincipale.png";
    private final String locationFenetreEtudiant = "Ecrans\\Principale\\FenetreEtudiant.png";
    private final String locationFenetreProfesseur = "Ecrans\\Principale\\FenetreProfesseur.png";
    private final String locationFenetreSelection = "Ecrans\\Principale\\FenetreSelection.png";
    private final String locationFenetreinscriptionEtudiants1 = "Ecrans\\Principale\\FenetreInscriptionEtudiants1.png";
    private final String locationFenetreinscriptionEtudiants2 = "Ecrans\\Principale\\FenetreInscriptionEtudiants2.png";
    private final String locationFenetreinscriptionProfesseurs1 = "Ecrans\\Principale\\FenetreInscriptionProfesseurs1.png";
    private final String locationFenetreinscriptionProfesseurs2 = "Ecrans\\Principale\\FenetreInscriptionProfesseurs2.png";
    private final String locationFenetreModificationtMDP = "Ecrans\\Principale\\FenetreModificationMDP.png";
    private final String locationFenetreStatistiquesEtudiant = "Ecrans\\Principale\\FenetreStatistiquesEtudiant.png";
    private final String locationFenetreStatistiquesJeu = "Ecrans\\Principale\\FenetreStatistiquesJeu.png";
    private final String locationFenetreStatistiquesGroupe = "Ecrans\\Principale\\FenetreStatistiquesGroupe.png";
    private final String locationFenetreClasses = "Ecrans\\Principale\\FenetreClasses.png";
    private final String locationFenetreModificationIcone = "Ecrans\\Principale\\FenetreModificationIcone.png";
    private final String locationFenetreAjoutClasses = "Ecrans\\Principale\\FenetreAjoutClasses.png";

    private final String locationRobot1 = "Ecrans\\Speed Run\\Robot 1.png";
    private final String locationRobot2 = "Ecrans\\Speed Run\\Robot 2.png";
    private final String locationRobot3 = "Ecrans\\Speed Run\\Robot 3.png";
    private final String locationCoeur = "Ecrans\\Speed Run\\Coeur.png";

    private final String locationTitreDragDrop = "Ecrans\\Tutorial\\TitreDragDrop.png";
    private final String locationTitreShooter = "Ecrans\\Tutorial\\TitreShooter.png";
    private final String locationTitreCoureur = "Ecrans\\Tutorial\\TitreCoureur.png";
    private final String locationTitreSpeedRun = "Ecrans\\Tutorial\\TitreSpeedRun.png";
    private final String locationFlecheGauche = "Ecrans\\Tutorial\\FlecheGauche.png";
    private final String locationFlecheDroite = "Ecrans\\Tutorial\\FlecheDroite.png";
    private final String locationFlecheHaut = "Ecrans\\Tutorial\\FlecheHaut.png";
    private final String locationFlecheBas = "Ecrans\\Tutorial\\FlecheBas.png";

    private final String locationIconeApplication = "Ecrans\\Principale\\Icon.png";

    ArrayList<Niveau> listeNiveauxDragDrop = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxShooter = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxCoureur = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxSpeedRun = new ArrayList<>();

    private boolean logInEtudiant = false;
    private boolean logInProfesseur = false;
    private boolean updateNUPermis = false;

    private ArrayList<Professeur> listeProfesseurs = new ArrayList<>();
    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
    private ArrayList<String> listeNUAdmisProfesseurs = new ArrayList<>();
    private ArrayList<String> listeMessagesErreurs = new ArrayList<>();

    private ArrayList<Icone> listeIcones = new ArrayList<>();

    private Etudiant etudiant;
    private Professeur professeur;

    private Professeur professeurNouveauEtudiant;
    private Groupe groupeNouveauEtudiant;

    private String logInErrorLog = " ";

    private String host = "jdbc:derby://localhost:1527/Jeux de bio DB";
    private String uName = "JeuxDeBio";
    private String uPass = "mot_de_passe0";

    public Modele() {
        lecture();
    }

    public void refresh() {
        fermerApp();
        lecture();
    }

    /**
     * La lecture de la base de donnees
     */
    private void lecture() {
        lectureEtudiants();
        lectureProfesseurs();
        lectureIcones();
        lectureNiveaux();
        lectureNUAdmisProfesseurs();
        lectureMessagesErreurs();
    }

    /**
     * Lit l'information sur les niveaux
     */
    private void lectureNiveaux() {
        listeNiveauxDragDrop.clear();
        listeNiveauxCoureur.clear();
        listeNiveauxSpeedRun.clear();

        try {
            String SQL = "SELECT * FROM NIVEAUDRAGDROP";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, ID));
            }

            SQL = "SELECT * FROM NIVEAUCOUREUR";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                //listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, ID));
            }

            SQL = "SELECT * FROM NIVEAUSPEEDRUN";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                //listeNiveauxSpeedRun.add(new Niveau(Jeu.SPEED_RUN, ID));
            }
            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Information niveaux\\Drag & Drop\\listeNiveaux.txt"));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                ligne = bufferedReader.readLine();
            }
            bufferedReader = new BufferedReader(new FileReader("Information niveaux\\Shooter\\listeNiveaux.txt"));
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, ligne));
                ligne = bufferedReader.readLine();
            }
            bufferedReader = new BufferedReader(new FileReader("Information niveaux\\Coureur\\listeNiveaux.txt"));
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, ligne));
                ligne = bufferedReader.readLine();
            }
            bufferedReader = new BufferedReader(new FileReader("Information niveaux\\Speed Run\\listeNiveaux.txt"));
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeNiveauxSpeedRun.add(new Niveau(Jeu.SPEED_RUN, ligne));
                ligne = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lit l'information sur les professeurs
     */
    private void lectureProfesseurs() {
        listeProfesseurs.clear();

        try {
            String SQL = "SELECT * FROM PROFESSEUR";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String nu = rs.getString("NU");
                listeProfesseurs.add(new Professeur(nu, this));
            }
            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Lit l'information sur les etudiants
     */
    private void lectureEtudiants() {
        listeEtudiants.clear();

        try {

            String SQL = "SELECT * FROM ETUDIANT";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String da = rs.getString("DA");
                String mdp = rs.getString("MOTDEPASSE");
                String nom = rs.getString("NOM");
                String locationIcone = rs.getString("LOCATIONICONE");
                String scoresDragDrop = rs.getString("SCORESDRAGDROP");
                String scoresCoureur = rs.getString("SCORESCOUREUR");
                String scoresSpeedRun = rs.getString("SCORESSPEEDRUN");
                listeEtudiants.add(new Etudiant(da, mdp, nom, locationIcone, scoresDragDrop, scoresCoureur, scoresSpeedRun));
                System.out.println(da);
                System.out.println(listeEtudiants.size());
            }
            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    }

    /**
     * Lit l'information sur les icones
     */
    private void lectureIcones() {
        listeIcones.clear();

        File dossier = new File("Utilisateurs\\Icones");
        File[] listeFiles = dossier.listFiles();

        for (int i = 0; i < listeFiles.length; i++) {
            listeIcones.add(new Icone(listeFiles[i].getAbsolutePath(), listeFiles[i].getName()));
        }
    }

    /**
     * Lit les noms d'utilisateur authentifies
     */
    private void lectureNUAdmisProfesseurs() {
        listeNUAdmisProfesseurs.clear();

        try {

            String SQL = "SELECT * FROM NUTIPERMIS";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String nuPermis = rs.getString("NUPERMIS");
                listeNUAdmisProfesseurs.add(nuPermis);
            }
            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Lit les messages d'erreurs
     */
    private void lectureMessagesErreurs() {
        listeMessagesErreurs.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationMessagesErreurs));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeMessagesErreurs.add(ligne);
                ligne = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Etudiant> getListeEtudiants() {
        return listeEtudiants;
    }

    public ArrayList<Professeur> getListeProfesseurs() {
        return listeProfesseurs;
    }

    public ArrayList<Icone> getListeIcones() {
        return listeIcones;
    }

    public Icone getIconeVierge() {
        return new Icone("Utilisateurs\\Icones\\iconeVierge.png");
    }

    public String getLocationFenetrePrincipale() {
        return locationFenetrePrincipale;
    }

    public String getLocationFenetreEtudiant() {
        return locationFenetreEtudiant;
    }

    public String getLocationFenetreProfesseur() {
        return locationFenetreProfesseur;
    }

    public String getLocationFenetreSelection() {
        return locationFenetreSelection;
    }

    public String getLocationFenetreInscriptionEtudiants1() {
        return locationFenetreinscriptionEtudiants1;
    }

    public String getLocationFenetreInscriptionEtudiants2() {
        return locationFenetreinscriptionEtudiants2;
    }

    public String getLocationFenetreInscriptionProfesseurs1() {
        return locationFenetreinscriptionProfesseurs1;
    }

    public String getLocationFenetreInscriptionProfesseurs2() {
        return locationFenetreinscriptionProfesseurs2;
    }

    public String getLocationFenetreModiicationMDP() {
        return locationFenetreModificationtMDP;
    }

    public String getLocationFenetreStatistiquesEtudiant() {
        return locationFenetreStatistiquesEtudiant;
    }

    public String getLocationFenetreStatistiquesJeu() {
        return locationFenetreStatistiquesJeu;
    }

    public String getLocationFenetreStatistiquesGroupe() {
        return locationFenetreStatistiquesGroupe;
    }

    public String getLocationFenetreClasses() {
        return locationFenetreClasses;
    }

    public String getLocationFenetreModificationIcone() {
        return locationFenetreModificationIcone;
    }

    public String getLocationFenetreAjoutClasses() {
        return locationFenetreAjoutClasses;
    }

    /**
     * Valide l'identite de l'etudiant
     *
     * @param da le DA fourni par l'utilisateur
     * @param motdepasse le mot de passe fourni par l'utilisateur
     */
    public void validerEtudiant(String da, char[] motdepasse) {
        String motDePasse = "";
        for (int i = 0; i < motdepasse.length; i++) {
            motDePasse += motdepasse[i];
        }

        boolean daInexistant = true;
        boolean motDePasseIncorrecte = true;

        for (int i = 0; i < listeProfesseurs.size(); i++) {
            for (int j = 0; j < listeProfesseurs.get(i).getListeGroupes().size(); j++) {
                for (int k = 0; k < listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().size(); k++) {
                    System.out.println(listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).getDa());
                    if (da.equals(listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).getDa())) {
                        daInexistant = false;
                        if (motDePasse.equals(listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).getMotDePasse())) {
                            motDePasseIncorrecte = false;
                            logInEtudiant = true;
                            this.etudiant = listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k);
                        }
                    }
                }
            }
        }
        if (daInexistant) {
            logInErrorLog = listeMessagesErreurs.get(0);
        } else if (motDePasseIncorrecte) {
            logInErrorLog = listeMessagesErreurs.get(2);
        }
        majObserver();

    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public boolean logInEtudiant() {
        return logInEtudiant;
    }

    public void logOutEtudiant() {
        this.logInEtudiant = false;
        this.etudiant = null;
    }

    /**
     * Valide l'identite du professeur
     *
     * @param nomUtilisateur le nom d'utilisateur fourni par l'utiliasteur
     * @param motdepasse le mot de passe fourni par l'utilisateur
     */
    public void validerProfesseur(String nomUtilisateur, char[] motdepasse) {
        String motDePasse = "";
        for (int i = 0; i < motdepasse.length; i++) {
            motDePasse += motdepasse[i];
        }

        boolean nuInexistant = true;
        boolean motDePasseIncorrecte = true;

        for (int i = 0; i < listeProfesseurs.size(); i++) {
            if (nomUtilisateur.equals(listeProfesseurs.get(i).getNomUtilisateur())) {
                nuInexistant = false;
                if (motDePasse.equals(listeProfesseurs.get(i).getMotDePasse())) {
                    motDePasseIncorrecte = false;
                    logInProfesseur = true;
                    this.professeur = listeProfesseurs.get(i);
                }
            }
        }

        if (nuInexistant) {
            logInErrorLog = listeMessagesErreurs.get(1);
        } else if (motDePasseIncorrecte) {
            logInErrorLog = listeMessagesErreurs.get(2);
        }
        majObserver();
    }

    public boolean logInProfesseur() {
        return logInProfesseur;
    }

    public void logOutProfesseur() {
        this.logInProfesseur = false;
        this.professeur = null;
    }

    /**
     * Verifie si le DA fourni par l'utilisateur est permis par un professeur
     *
     * @param da le DA
     * @return si le DA est permis
     */
    public boolean etudiantPermis(String da) {
        boolean etudiantPermis = false;
        for (int i = 0; i < listeProfesseurs.size(); i++) {
            if (listeProfesseurs.get(i).etudiantPermis(da)) {
                professeurNouveauEtudiant = listeProfesseurs.get(i);
                groupeNouveauEtudiant = listeProfesseurs.get(i).getGroupeNouveauEtudiant(da);
                etudiantPermis = listeProfesseurs.get(i).etudiantPermis(da);
            }
        }
        return etudiantPermis;
    }

    /**
     * Verifie si le DA est deja enregistre
     *
     * @param da le DA
     * @return si le DA est deja enregistre
     */
    public boolean etudiantExisteDeja(String da) {
        boolean etudiantExisteDeja = false;
        for (int i = 0; i < listeEtudiants.size(); i++) {
            if (da.equals(listeEtudiants.get(i).getDa())) {
                etudiantExisteDeja = da.equals(listeEtudiants.get(i).getDa());
            }
        }
        return etudiantExisteDeja;
    }

    public String getNomProfesseurNouveauEtudiant() {
        return professeurNouveauEtudiant.getNom();
    }

    public String getCodeGroupeNouveauEtudiant() {
        return groupeNouveauEtudiant.getCode();
    }

    /**
     * Verifie si le nom d'utilisateur fourni est authentifie par un admin
     *
     * @param nu le nom d'utilisateur
     * @return si le nom d'utilisateur est authentifie
     */
    public boolean professeurPermis(String nu) {
        boolean professeurPermis = false;
        for (int i = 0; i < listeNUAdmisProfesseurs.size(); i++) {
            if (nu.equals(listeNUAdmisProfesseurs.get(i))) {
                professeurPermis = true;
                break;
            }
        }
        return professeurPermis;
    }

    /**
     * Verifie si le nom d'utilisateur est deja enregistre
     *
     * @param nu le nom d'utilisateur
     * @return si le nom d'utilisateur est deja enregistre
     */
    public boolean professeurExisteDeja(String nu) {
        boolean professeurExisteDeja = false;
        for (int i = 0; i < listeProfesseurs.size(); i++) {
            if (nu.equals(listeProfesseurs.get(i).getNomUtilisateur())) {
                professeurExisteDeja = true;
                break;
            }
        }
        return professeurExisteDeja;
    }

    /**
     * Verifie si le nom d'utilisateur fourni par l'admin est deja authentifie
     *
     * @param nu le nom d'utilisateur
     * @return si le nom d'utilisateur est authentifie
     */
    public boolean professeurDejaAuthentifie(String nu) {
        boolean professeurDejaAuthentifie = false;
        for (int i = 0; i < listeNUAdmisProfesseurs.size(); i++) {
            if (nu.equals(listeNUAdmisProfesseurs.get(i))) {
                professeurDejaAuthentifie = true;
                break;
            }
        }
        return professeurDejaAuthentifie;
    }

    /**
     * Cree l'etudiant avec les donnees fournies par l'utilisateur
     *
     * @param da le DA
     * @param mdp le mot de passe
     * @param nom le nom
     */
    public void creerEtudiant(String da, String mdp, String nom) {
        String locationIcone = "Utilisateurs\\Icones\\iconeVierge.png";
        String ligneScores = "0;0;0;0;0;0;0;0;0;0";

        try {
            String SQL = "SELECT * FROM ETUDIANT";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            rs.moveToInsertRow();

            rs.updateString("DA", da);
            rs.updateString("MOTDEPASSE", mdp);
            rs.updateString("NOM", nom);
            rs.updateString("LOCATIONICONE", locationIcone);
            rs.updateString("SCORESDRAGDROP", ligneScores);
            rs.updateString("SCORESCOUREUR", ligneScores);
            rs.updateString("SCORESSPEEDRUN", ligneScores);

            rs.insertRow();

            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        Etudiant etudiant = new Etudiant(da, mdp, nom, locationIcone, ligneScores, ligneScores, ligneScores);
        listeEtudiants.add(etudiant);
        groupeNouveauEtudiant.ajouterEtudiant(etudiant);
        etudiant.setGroupe(groupeNouveauEtudiant);
        etudiant.setProfesseur(professeurNouveauEtudiant);
        professeurNouveauEtudiant.updateDAPermisEnleverEtudiant(etudiant);
    }

    /**
     * Cree un professeur avec les donnees fournies par l'utilisateur
     *
     * @param nu le nom d'utiliateur
     * @param mdp le mot de passe
     * @param nom le nom
     * @param session la session
     */
    public void creerProfesseur(String nu, String mdp, String nom, String session) {
        listeNUAdmisProfesseurs.remove(nu);
        updateNUPermis = true;

        listeProfesseurs.add(new Professeur(nu, mdp, nom, session));
        String locationIcone = "Utilisateurs\\Icones\\iconeVierge.png";
        try {
            String SQL = "SELECT * FROM PROFESSEUR";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            rs.moveToInsertRow();

            rs.updateString("NU", nu);
            rs.updateString("MOTDEPASSE", mdp);
            rs.updateString("NOM", nom);
            rs.updateBoolean("ESTADMIN", false);
            rs.updateString("LOCATIONICONE", locationIcone);
            rs.updateString("SESSION", null);
            rs.updateString("DAPERMIS", null);
            rs.updateString("LISTEGROUPES", null);

            rs.insertRow();

            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }
    }

    public String getNomNiveau(Jeu jeu, int i) {
        String nomNiveau = "À venir";

        switch (jeu) {
            case DRAG_DROP:
                if (i < listeNiveauxDragDrop.size()) {
                    nomNiveau = listeNiveauxDragDrop.get(i).getNom();
                }
                break;
            case SHOOTER:
                if (i < listeNiveauxShooter.size()) {
                    nomNiveau = listeNiveauxShooter.get(i).getNom();
                }
                break;
            case COUREUR:
                if (i < listeNiveauxCoureur.size()) {
                    nomNiveau = listeNiveauxCoureur.get(i).getNom();
                }
                break;
            case SPEED_RUN:
                if (i < listeNiveauxSpeedRun.size()) {
                    nomNiveau = listeNiveauxSpeedRun.get(i).getNom();
                }
        }

        return nomNiveau;
    }

    public String getLocationNiveau(Jeu jeu, int i) {
        String locationNiveau = "";

        switch (jeu) {
            case DRAG_DROP:
                locationNiveau = listeNiveauxDragDrop.get(i).getLocation();
                break;
            case SHOOTER:
                locationNiveau = listeNiveauxShooter.get(i).getLocation();
                break;
            case COUREUR:
                locationNiveau = listeNiveauxCoureur.get(i).getLocation();
                break;
            case SPEED_RUN:
                locationNiveau = listeNiveauxSpeedRun.get(i).getLocation();
        }
        return locationNiveau;
    }

    public String getLocationNiveauCorrige(Jeu jeu, int i) {
        String locationNiveauCorrige = "";

        switch (jeu) {
            case DRAG_DROP:
                locationNiveauCorrige = listeNiveauxDragDrop.get(i).getLocationCorrige();
                break;
            case SHOOTER:
                locationNiveauCorrige = listeNiveauxShooter.get(i).getLocationCorrige();
                break;
            case COUREUR:
                locationNiveauCorrige = listeNiveauxCoureur.get(i).getLocationCorrige();
                break;
            case SPEED_RUN:
                locationNiveauCorrige = listeNiveauxSpeedRun.get(i).getLocationCorrige();
        }
        return locationNiveauCorrige;
    }

    public int[] getSizeImageDragDrop(int i) {
        return listeNiveauxDragDrop.get(i).getSizeImageDragDrop();
    }

    public ArrayList<int[]> getCoordonneesBoitesReponsesDragDrop(int i) {
        return listeNiveauxDragDrop.get(i).getCoordonneesDragDrop();
    }

    public ArrayList getQuestionsDragDrop(int i) {
        return listeNiveauxDragDrop.get(i).getQuestionsDragDrop();
    }

    public ArrayList getPositionReponsesCoureur(int i) {
        return listeNiveauxCoureur.get(i).getPositionReponsesCoureur();
    }

    public ArrayList getReponseCoureur(int i) {
        return listeNiveauxCoureur.get(i).getReponsesCoureur();
    }

    public ArrayList getQuestionsCoureur(int i) {
        return listeNiveauxCoureur.get(i).getQuestionCoureur();
    }

    public ArrayList getQuestionsSpeedRun(int i) {
        return listeNiveauxSpeedRun.get(i).getQuestionsSpeedRun();
    }

    public ArrayList getReponsesSpeedRun(int i) {
        return listeNiveauxSpeedRun.get(i).getReponsesSpeedRun();
    }

    public String getLocationRobot1() {
        return locationRobot1;
    }

    public String getLocationRobot2() {
        return locationRobot2;
    }

    public String getLocationRobot3() {
        return locationRobot3;
    }

    public String getLocationCoeur() {
        return locationCoeur;
    }

    public String getLocationTitreDragDrop() {
        return locationTitreDragDrop;
    }

    public String getLocationTitreShooter() {
        return locationTitreShooter;
    }

    public String getLocationTitreCoureur() {
        return locationTitreCoureur;
    }

    public String getLocationTitreSpeedRun() {
        return locationTitreSpeedRun;
    }

    public String getLocationFlecheDroite() {
        return locationFlecheDroite;
    }

    public String getLocationFlecheGauche() {
        return locationFlecheGauche;
    }

    public String getLocationFlecheHaut() {
        return locationFlecheHaut;
    }

    public String getLocationIconeApplication() {
        return locationIconeApplication;
    }

    public String getLocationInformation(Jeu jeu, int i) {
        return listeNiveauxDragDrop.get(i).getLocationInformation();
    }

    public String getLocationFlecheBas() {
        return locationFlecheBas;
    }

    /**
     * Calcule le score d'un niveau Drag & Drop
     *
     * @param i l'identifiant du niveau
     * @param nombreErreurs le nombre d'erreurs
     */
    public void calculerScoreDragDrop(int i, int nombreErreurs) {
        double scoreDouble = ((double) (listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size() - nombreErreurs) / listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) * 10000;
        int score = (int) scoreDouble / 100;

        if (logInEtudiant) {
            etudiant.setScore(Jeu.DRAG_DROP, i, score);
        } else {
            listeNiveauxDragDrop.get(i).setScore(score);
        }
    }

    /**
     * Calcule le score d'un niveau Coureur
     *
     * @param i l'identifiant du niveau
     * @param nombreBonneReponses nombre de bonnes reponses
     */
    public void calculerScoreCoureur(int i, int nombreBonneReponses) {
        double scoreDouble = ((double) (nombreBonneReponses) / listeNiveauxCoureur.get(i).getQuestionCoureur().size()) * 10000;
        int score = (int) scoreDouble / 100;

        if (logInEtudiant) {
            etudiant.setScore(Jeu.COUREUR, i, score);
        } else {
            listeNiveauxCoureur.get(i).setScore(score);
        }
    }

    /**
     * Calcule le score d'un niveau Speed Run
     *
     * @param i l'identifiant du niveau
     * @param joueurScore le score du joueur
     */
    public void calculerScoreSpeedRun(int i, int joueurScore) {
        double scoreDouble = ((double) (joueurScore) / ((listeNiveauxSpeedRun.get(i).getQuestionsSpeedRun().size()) / 3)) * 10000;
        int score = (int) scoreDouble / 100;

        if (logInEtudiant) {
            etudiant.setScore(Jeu.SPEED_RUN, i, score);
        } else {
            listeNiveauxSpeedRun.get(i).setScore(score);
        }
    }

    public int getScoreNiveau(Jeu jeu, int i) {
        int score = 0;

        if (logInEtudiant) {
            score = etudiant.getScoreNiveau();
        } else {
            switch (jeu) {
                case DRAG_DROP:
                    score = listeNiveauxDragDrop.get(i).getScore();
                    break;
                case SHOOTER:
                    score = listeNiveauxShooter.get(i).getScore();
                    break;
                case COUREUR:
                    score = listeNiveauxCoureur.get(i).getScore();
                    break;
                case SPEED_RUN:
                    score = listeNiveauxSpeedRun.get(i).getScore();
            }
        }
        refresh();
        return score;
    }

    public void etudiantModificationMDP(String nouveauMDP) {
        etudiant.setMotDePasse(nouveauMDP);
    }

    public void professeurModificationMDP(String nouveauMDP) {
        professeur.setMotDePasse(nouveauMDP);
    }

    /**
     * Enleve l'etudiant du groupe fournis par le professeur
     *
     * @param groupe le groupe de l'etudiant a enlever
     * @param etudiant l'etudiant a enlever
     */
    public void enleverEtudiant(Groupe groupe, Etudiant etudiant) {
        groupe.enleverEtudiant(etudiant);
        professeur.updateDAPermisEnleverEtudiant(etudiant);
    }

    /**
     * Enleve le groupe fourni par le professeur
     *
     * @param groupe le groupe a enlever
     */
    public void enleverGroupe(Groupe groupe) {
        professeur.updateInformationsEnleverGroupe(groupe);
    }

    /**
     * Fermer l'application
     */
    public void fermerApp() {
        for (int i = 0; i < listeProfesseurs.size(); i++) {
            if (listeProfesseurs.get(i).informationsModifies()) {
                listeProfesseurs.get(i).updateFichierProfesseur();
            }

            for (int j = 0; j < listeProfesseurs.get(i).getListeGroupes().size(); j++) {
                if (listeProfesseurs.get(i).getListeGroupes().get(j).informationsModifies()) {
                    listeProfesseurs.get(i).getListeGroupes().get(j).updateFichierGroupe();
                }

                for (int k = 0; k < listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().size(); k++) {
                    if (listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).informationsModifies()) {
                        listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).updateFichierEtudiant();
                    }
                }
            }
        }

        if (updateNUPermis) {
            updateNUPermis();
        }

    }

    public String getLogInErrorLog() {
        return logInErrorLog;
    }

    public String getMessageErreur(int i) {
        return listeMessagesErreurs.get(i);
    }

    /**
     * Authentifier un nom d'utilisateur
     *
     * @param nuAdmis le nom d'utilisateur authentifie
     */
    public void ajouterProfesseurNUAdmis(String nuAdmis) {
        listeNUAdmisProfesseurs.add(nuAdmis);
        updateNUPermis = true;
    }

    private void updateNUPermis() {
        try {
            String SQL = "SELECT * FROM NUTIPERMIS";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                rs.deleteRow();
            }

            for (int i = 0; i < listeNUAdmisProfesseurs.size(); i++) {
                rs.moveToInsertRow();

                rs.updateString("NUPERMIS", listeNUAdmisProfesseurs.get(i));

                rs.insertRow();
            }

            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }

    }

    /**
     * Cree un groupe avec les donnees fournies
     *
     * @param liste le code du groupe (liste.get(0)) et la liste des etudiants
     * permis dans ce groupe
     */
    public void creerGroupe(ArrayList<String> liste) {
        String informations = professeur.getNomUtilisateur() + liste.get(0);
        String etudiants = "";
        for (int i = 1; i < liste.size(); i++) {
            etudiants += liste.get(i);
            if (i < liste.size() - 1) {
                etudiants += ";";
            }
        }
        try {
            String SQL = "SELECT * GROUPE";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            rs.moveToInsertRow();

            rs.updateString("CODE", informations);
            rs.updateString("LISTEETUDIANTS", etudiants);

            rs.insertRow();

            stmt.close();
            rs.close();

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }
        professeur.ajouterGroupe(informations);
    }

    /**
     * Enleve le professeur fourni par l'admin
     *
     * @param professeur le professeur a enlever
     */
    public void enleverProfesseur(Professeur professeur) {
        try {
            String SQL = "SELECT * FROM PROFESSEUR";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                if (professeur.getNomUtilisateur().equals(rs.getString("NU"))) {
                    rs.deleteRow();
                }
            }

            stmt.close();
            rs.close();

            SQL = "SELECT * FROM GROUPE";
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String[] split = rs.getString("CODE").split(";");
                if (split.equals(professeur.getNomUtilisateur())) {
                    rs.deleteRow();
                    rs.first();
                }
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }

        listeProfesseurs.remove(professeur);
    }

    /**
     * Modifie un niveau Drag & Drop
     *
     * @param nom le nom
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrigee le location de l'image du corrige
     * @param taille la taille (width et height) de l'image
     * @param listeBoites la liste des Boites des reponses
     * @param index l'index
     * @param ID l'identifiant du niveau
     */
    public void modifierNiveauDragDrop(String nom, String locationImage, String locationImageCorrigee, String taille, ArrayList<BoiteReponseConstruction> listeBoites, String index, int ID) {

        try {
            String SQL = "SELECT * FROM NIVEAUDRAGDROP";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(ID);
                if (rs.getInt("ID") == ID) {
                    rs.deleteRow();
                    System.out.println("deleted");
                }
            }
            rs.moveToInsertRow();

            rs.updateString("NOM", nom);
            rs.updateString("LOCATIONIMAGE", locationImage);
            rs.updateString("LOCATIONIMAGECORRIGE", locationImageCorrigee);
            rs.updateString("GRANDEURIMAGE", taille);
            rs.updateString("INDEXQUESTION", index);
            rs.updateInt("ID", ID);

            rs.insertRow();

            stmt.close();
            rs.close();

            for (int i = 1; i <= listeBoites.size(); i++) {
                SQL = "SELECT * FROM QUESTIONDRAGDROP";
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery(SQL);
                String string = ID + ";" + i;
                System.out.println(string);
                while (rs.next()) {
                    if (rs.getString("INDEX").equals(string)) {
                        System.out.println("deleted");
                        rs.deleteRow();
                    }
                }
            }
            for (int i = 1; i <= listeBoites.size(); i++) {
                rs.moveToInsertRow();
                rs.updateString("INDEX", ID + ";" + i);
                rs.updateString("TEXTE", listeBoites.get(i - 1).getReponse());
                rs.updateInt("X", listeBoites.get(i - 1).getPositionX());
                rs.updateInt("Y", listeBoites.get(i - 1).getPositionY());
                rs.insertRow();
            }

            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }
    }

    /**
     * Cree un niveau Drag & Drop
     *
     * @param nom le nom du niveau
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrigee le location de l'image du corrige
     * @param largeur le largeur de l'image
     * @param hauteur la hauteur de l'image
     * @param listeBoites la liste des Boites de reponse
     */
    public void creerNiveauDragDrop(String nom, String locationImage, String locationImageCorrigee, String largeur, String hauteur, ArrayList<BoiteReponseConstruction> listeBoites) {
        try {
            String taille = largeur + ";" + hauteur;
            String index = "";
            int ID = 0;
            for (int i = 0; i < listeBoites.size(); i++) {
                index += i + 1;
                if (i != listeBoites.size() - 1) {
                    index += ";";
                }
            }

            String SQL = "SELECT * FROM NIVEAUDRAGDROP";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                if (rs.getInt("ID") > ID) {
                    ID = rs.getInt("ID");
                }
            }
            ID = ID + 1;

            rs.moveToInsertRow();

            rs.updateString("NOM", nom);
            rs.updateString("LOCATIONIMAGE", locationImage);
            rs.updateString("LOCATIONIMAGECORRIGE", locationImageCorrigee);
            rs.updateString("GRANDEURIMAGE", taille);
            rs.updateString("INDEXQUESTION", index);
            rs.updateInt("ID", ID);

            rs.insertRow();

            stmt.close();
            rs.close();

            SQL = "SELECT * FROM QUESTIONDRAGDROP";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            for (int i = 1; i <= listeBoites.size(); i++) {
                rs.moveToInsertRow();
                rs.updateString("INDEX", ID + ";" + i);
                rs.updateString("TEXTE", listeBoites.get(i - 1).getReponse());
                rs.updateInt("X", listeBoites.get(i - 1).getPositionX());
                rs.updateInt("Y", listeBoites.get(i - 1).getPositionY());
                rs.insertRow();
            }

            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }
    }

    public int getIDNiveau(Jeu jeu, int a) {
        return listeNiveauxDragDrop.get(a).getID();
    }

    /**
     * Mettre le professeur fourni l'admin
     *
     * @param professeur a mettre admin
     */
    public void cederAdmin(Professeur professeur) {
        professeur.setAdmin();
        this.professeur.enleverAdmin();
    }

    public void majObserver() {
        setChanged();
        notifyObservers();
    }

}
