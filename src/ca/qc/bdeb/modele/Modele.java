/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private final String locationListeEtudiants = "Utilisateurs\\listeEtudiants.txt";

    private final String locationFenetrePrincipale = "Ecrans\\Principale\\FenetrePrincipale.png";

    private final String locationFenetreEtudiant = "Ecrans\\Principale\\FenetreEtudiant.png";
    private final String locationFenetreProfesseur = "Ecrans\\Principale\\FenetreProfesseur.png";
    private final String locationFenetreSelection = "Ecrans\\Principale\\FenetreSelection.png";
    private final String locationFenetreinscriptionEtudiants = "Ecrans\\Principale\\FenetreInscriptionEtudiants.png";
    private final String locationFenetreinscriptionProfesseurs = "Ecrans\\Principale\\FenetreInscriptionEtudiants.png"; // changer l'image pour prof

    private final String locationRobot1 = "Ecrans\\Speed Run\\Robot 1.png";
    private final String locationRobot2 = "Ecrans\\Speed Run\\Robot 2.png";
    private final String locationRobot3 = "Ecrans\\Speed Run\\Robot 3.png";
    private final String locationCoeur = "Ecrans\\Speed Run\\Coeur.png";

    ArrayList<Niveau> listeNiveauxDragDrop = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxShooter = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxCoureur = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxSpeedRun = new ArrayList<>();

    private boolean logInEtudiant = false;
    private boolean logInProfesseur = false;

    private ArrayList<Professeur> listeProfesseurs = new ArrayList<>();
    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();

    private Etudiant etudiant;
    private Professeur professeur;

    public Modele() {
        lectureEtudiants();
        lectureProfesseurs();

        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 1.txt"));
        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 2.txt"));
        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 3.txt"));
        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 4.txt"));

        listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, "Information niveaux\\Shooter\\Niveau 1.txt"));

        listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, "Information niveaux\\Coureur\\Niveau 1.txt"));
        listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, "Information niveaux\\Coureur\\Niveau 2.txt"));

        listeNiveauxSpeedRun.add(new Niveau(Jeu.SPEED_RUN, "Information niveaux\\Speed Run\\Niveau 1.txt"));

    }

    private void lectureProfesseurs() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationListeProfesseurs));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeProfesseurs.add(new Professeur(ligne, this));
                ligne = bufferedReader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lectureEtudiants() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationListeEtudiants));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeEtudiants.add(new Etudiant(ligne));
                ligne = bufferedReader.readLine();
            }
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

    public String getLocationFenetreInscriptionEtudiants() {
        return locationFenetreinscriptionEtudiants;
    }

    public String getLocationFenetreInscriptionProfesseurs() {
        return locationFenetreinscriptionProfesseurs;
    }

    public void validerEtudiant(String da, char[] motdepasse) {
        String motDePasse = "";
        for (int i = 0; i < motdepasse.length; i++) {
            motDePasse += motdepasse[i];
        }

        for (int i = 0; i < listeProfesseurs.size(); i++) {
            for (int j = 0; j < listeProfesseurs.get(i).getListeGroupes().size(); j++) {
                for (int k = 0; k < listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().size(); k++) {
                    if (da.equals(listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).getDa()) && motDePasse.equals(listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k).getMotDePasse())) {
                        logInEtudiant = true;
                        this.etudiant = listeProfesseurs.get(i).getListeGroupes().get(j).getListeEtudiants().get(k);
                        majObserver();
                    }
                }
            }
        }
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

        for (int i = 0; i < listeProfesseurs.size(); i++) {
            if (nomUtilisateur.equals(listeProfesseurs.get(i).getNomUtilisateur()) && motDePasse.equals(listeProfesseurs.get(i).getMotDePasse())) {
                logInProfesseur = true;
                this.professeur = listeProfesseurs.get(i);
                majObserver();
            }
        }
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
            etudiantPermis = listeProfesseurs.get(i).etudiantPermis(da);
            if (etudiantPermis) {
                System.out.println(listeProfesseurs.get(i).getNomUtilisateur());
                break;
            }
        }
        return etudiantPermis;
    }

    public boolean professeurExiste(String da) {

        return true;
    }

    public void creerUtilisateur(String motDePasse) {
        String informations = "Utilisateurs\\Etudiants\\" + motDePasse + ".txt";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(informations));
            bufferedWriter.write(motDePasse);
            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creerProfesseur(String nom) {
        String informations = "Utilisateurs\\Professeurr\\" + nom;

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(informations));
            bufferedWriter.write(nom);
            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
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

    public void calculerScoreDragDrop(int i, int nombreErreurs) {
        double scoreDouble = ((double) (listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size() - nombreErreurs) / listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) * 10000;
        int score = (int) scoreDouble;

        if (logInEtudiant) {

        } else {
            listeNiveauxDragDrop.get(i).setScore(score / 100);
        }
    }

    public void calculerScoreShooter(int i, int nombreBots) {
        int score = nombreBots * 5;

        if (logInEtudiant) {

        } else {
            listeNiveauxShooter.get(i).setScore(score);
        }
    }

    public void calculerScoreCoureur(int i) {
        double scoreDouble = ((double) (listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) / listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) * 10000;
        int score = (int) scoreDouble;

        if (logInEtudiant) {

        } else {
            listeNiveauxCoureur.get(i).setScore(score / 100);
        }
    }

    public void calculerScoreSpeedRun(int i, int joueurScore) {
        double scoreDouble = ((double) (listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) / listeNiveauxDragDrop.get(i).getQuestionsDragDrop().size()) * 10000;
        int score = (int) scoreDouble;

        if (logInEtudiant) {

        } else {
            listeNiveauxSpeedRun.get(i).setScore(score / 100);
        }
    }

    public int getScoreNiveau(Jeu jeu, int i) {
        int score = 0;

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

        return score;
    }

    public void majObserver() {
        setChanged();
        notifyObservers();
    }

}
