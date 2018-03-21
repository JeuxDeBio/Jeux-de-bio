/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.io.BufferedWriter;
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

    private final String locationFenetrePrincipale = "Ecrans\\Principale\\FenetrePrincipale.png";

    private final String locationFenetrePrincipaleLogIn = "Ecrans\\Principale\\FenetrePrincipaleLogIn.png";
    private final String locationFenetreSelection = "Ecrans\\Principale\\FenetreSelection.png";
    private final String locationFenetreinscription = "Ecrans\\Principale\\FenetreInscription.png";


    private final String locationRobot1 = "Ecrans\\Speed Run\\Robot 1.png";
    private final String locationRobot2 = "Ecrans\\Speed Run\\Robot 2.png";
    private final String locationRobot3 = "Ecrans\\Speed Run\\Robot 3.png";
    private final String locationCoeur = "Ecrans\\Speed Run\\Coeur.png";

    ArrayList<Etudiant> listeUtilisateurs = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxDragDrop = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxShooter = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxCoureur = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxSpeedRun = new ArrayList<>();

    Etudiant etudiant;

    private boolean logIn = false;

    public Modele() {
        creerUtilisateur("123");
        creerUtilisateur("456");
        creerUtilisateur("789");

        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 1.txt"));
        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 2.txt"));

        listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, "Information niveaux\\Shooter\\Niveau 1.txt"));

        listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, "Information niveaux\\Coureur\\Niveau 1.txt"));
        listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, "Information niveaux\\Coureur\\Niveau 2.txt"));

        listeNiveauxSpeedRun.add(new Niveau(Jeu.SPEED_RUN, "Information niveaux\\Speed Run\\Niveau 1.txt"));

    }

    public String getLocationFenetrePrincipale() {
        return locationFenetrePrincipale;
    }

    public String getLocationFenetrePrincipaleLogIn() {
        return locationFenetrePrincipaleLogIn;
    }

    public String getLocationFenetreSelection() {
        return locationFenetreSelection;
    }

    public String getLocationFenetreinscription() {
        return locationFenetreinscription;
    }

    public Utilisateur getUtilisateur() {
        return this.etudiant;
    }

    public void validerUtilisateur(String da, char[] motdepasse) {
        for (int i = 0; i < listeUtilisateurs.size(); i++) {
            if (da.equals(listeUtilisateurs.get(i).getDa())) {
                String motDePasse = "";
                for (int j = 0; j < motdepasse.length; j++) {
                    motDePasse += motdepasse[j];
                }
                if (motDePasse.equals(listeUtilisateurs.get(i).getMotDePasse())) {
                    this.etudiant = listeUtilisateurs.get(i);
                    this.logIn = true;
                    majObserver();
                } else {
                }
            } else {
            }
        }

    }

    public boolean logIn() {
        return logIn;
    }

    public void logOut() {
        this.logIn = false;
        this.etudiant = null;
    }

    public boolean etudiantExiste(String da) {
        //faire des verifications
        return true;
    }

    public void creerUtilisateur(String motDePasse) {
        String informations = "Utilisateurs\\Etudiants\\" + motDePasse;

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(informations));
            bufferedWriter.write(motDePasse);
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

    public void calculerScoreDragDrop(int i, int nombreErreurs, int temps) {
        int score = 0;
        if (etudiant != null) {
            etudiant.setCurrentScore(Jeu.DRAG_DROP, i, score, temps);
        } else {
            listeNiveauxDragDrop.get(i).setScore(score);
        }
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

    public int getScoreNiveau(Jeu jeu, int i) {
        int score = 0;

        if (etudiant != null) {
            score = etudiant.getCurrentScore();
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

        return score;
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

    public void calculerScoreSpeedRun(int i, int joueurScore, int nombreQuestions) {
        int score = 0;
        if (etudiant != null) {
            etudiant.setCurrentScore(Jeu.SPEED_RUN, i, score, 0);
        } else {
            listeNiveauxSpeedRun.get(i).setScore(score);
        }
    }

    public void majObserver() {
        setChanged();
        notifyObservers();
    }

}
