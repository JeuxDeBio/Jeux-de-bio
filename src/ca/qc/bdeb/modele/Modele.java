/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import ca.qc.bdeb.vue.dragDrop.BoiteReponseConstruction;
import ca.qc.bdeb.vue.principale.Icone;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    private final String locationListeProfesseurs = "Utilisateurs\\listeProfesseurs.txt";
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

    private void lecture() {
        lectureEtudiants();
        lectureProfesseurs();
        lectureIcones();
        lectureNiveaux();
        lectureNUAdmisProfesseurs();
        lectureMessagesErreurs();
    }

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

//        
//        -------------------
//                ---------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
//                -------------------------------
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Information niveaux\\Drag & Drop\\listeNiveaux.txt"));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                //listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, ligne));
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

//        --------------
//                ------------------
//                -------------------
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationListeProfesseurs));
//            String ligne = bufferedReader.readLine();
//            while (ligne != null) {
//                listeProfesseurs.add(new Professeur(ligne, this));
//                ligne = bufferedReader.readLine();
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

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

    private void lectureIcones() {
        listeIcones.clear();

        File dossier = new File("Utilisateurs\\Icones");
        File[] listeFiles = dossier.listFiles();

        for (int i = 0; i < listeFiles.length; i++) {
            listeIcones.add(new Icone(listeFiles[i].getAbsolutePath(), listeFiles[i].getName()));
        }
    }

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

//        ----------------
//                ------------------------
//                ---------------------
//                -----------------
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("Utilisateurs\\Professeurs\\NUPermis.txt"));
//            String ligne = bufferedReader.readLine();
//            while (ligne != null) {
//                listeNUAdmisProfesseurs.add(ligne);
//                ligne = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void lectureMessagesErreurs() {////// a changer peut être si on ne les laisse pas dans des files
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

    public String getLocationInformation(Jeu jeu, int i) {
        return listeNiveauxDragDrop.get(i).getLocationInformation();
    }

    public String getLocationFlecheBas() {
        return locationFlecheBas;
    }

    public void calculerScoreDragDrop(int i, int nombreErreurs) {
        double scoreDouble = ((double) (listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size() - nombreErreurs) / listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) * 10000;
        int score = (int) scoreDouble / 100;

        if (logInEtudiant) {
            etudiant.setScore(Jeu.DRAG_DROP, i, score);
        } else {
            listeNiveauxDragDrop.get(i).setScore(score);
        }
    }

    public void calculerScoreShooter(int i, int nombreBots) {
        int score = nombreBots * 5;

        if (logInEtudiant) {
            etudiant.setScore(Jeu.SHOOTER, i, score);
        } else {
            listeNiveauxShooter.get(i).setScore(score);
        }
    }

    public void calculerScoreCoureur(int i, int nombreBonneReponses) {
        double scoreDouble = ((double) (nombreBonneReponses) / listeNiveauxCoureur.get(i).getQuestionCoureur().size()) * 10000;
        int score = (int) scoreDouble / 100;

        if (logInEtudiant) {
            etudiant.setScore(Jeu.COUREUR, i, score);
        } else {
            listeNiveauxCoureur.get(i).setScore(score);
        }
    }

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

    public void enleverEtudiant(Groupe groupe, Etudiant etudiant) {
        groupe.enleverEtudiant(etudiant);
        professeur.updateDAPermisEnleverEtudiant(etudiant);
    }

    public void enleverGroupe(Groupe groupe) {
        professeur.updateInformationsEnleverGroupe(groupe);
    }

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
//        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(informations));
//            for (int i = 0; i < liste.size(); i++) {
//                bufferedWriter.write(liste.get(i));
//                bufferedWriter.newLine();
//            }
//            bufferedWriter.close();
//
//            bufferedWriter = new BufferedWriter(new FileWriter("Utilisateurs\\Professeurs\\" + professeur.getNomUtilisateur() + "DAPermis.txt", true));
//
//            for (int i = 1; i < liste.size(); i++) {
//                bufferedWriter.write(liste.get(i) + ";" + liste.get(0));
//                bufferedWriter.newLine();
//            }
//            bufferedWriter.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
//        }

        professeur.ajouterGroupe(informations);
    }

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
            while(rs.next()){
                String[] split = rs.getString("CODE").split(";");
                if(split.equals(professeur.getNomUtilisateur())){
                    rs.deleteRow();
                    rs.first();
                }
            }
            
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("fail");
        }

        listeProfesseurs.remove(professeur);

//        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Utilisateurs\\listeProfesseurs.txt"));
//            for (int i = 0; i < listeProfesseurs.size(); i++) {
//                bufferedWriter.write(listeProfesseurs.get(i).getInformation());
//                bufferedWriter.newLine();
//            }
//            bufferedWriter.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

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

//        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Information niveaux\\Drag & Drop\\" + titre + ".txt"));
//            bufferedWriter.write(titre);
//            bufferedWriter.newLine();
//            bufferedWriter.write(lien1);
//            bufferedWriter.newLine();
//            bufferedWriter.write(lien2);
//            bufferedWriter.newLine();
//            bufferedWriter.write(largeur + ";" + hauteur);
//            bufferedWriter.newLine();
//            for (int i = 0; i < listeReponses.size(); i++) {
//                bufferedWriter.write(listeReponses.get(i).getPositionX() + ";" + listeReponses.get(i).getPositionY() + ":" + listeReponses.get(i).getReponse());
//                bufferedWriter.newLine();
//            }
//
//            bufferedWriter.close();
//
//            bufferedWriter = new BufferedWriter(new FileWriter("Information niveaux\\Drag & Drop\\listeNiveaux.txt", true));
//            bufferedWriter.newLine();
//            bufferedWriter.write("Information niveaux\\Drag & Drop\\" + titre + ".txt");
//            bufferedWriter.close();
//        } catch (IOException ex) {
//
//        }
    }

    public int getIDNiveau(Jeu jeu, int a) {
        return listeNiveauxDragDrop.get(a).getID();
    }

    public void cederAdmin(Professeur professeur) {
        professeur.setAdmin();
        this.professeur.enleverAdmin();
    }

    public void majObserver() {
        setChanged();
        notifyObservers();
    }

}
