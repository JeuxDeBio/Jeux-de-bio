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

    private final String locationFenetrePrincipale = "Ecrans\\Fenetre_principale.png";
    private final String locationFenetrePrincipaleLogIn = "Ecrans\\Fenetre_principale_logIn.png";
    private final String locationFenetreSelection = "Ecrans\\Fenetre_selection.png";

    ArrayList<Etudiant> listeUtilisateurs = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxDragDrop = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxShooter = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxCoureur = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxSpeedRun = new ArrayList<>();

    Etudiant etudiant;

    private boolean logIn = false;

    public Modele() {
        creerUtilisateur("123", "123", "Adam", "Adam");
        creerUtilisateur("456", "456", "Bob", "Bob");
        creerUtilisateur("789", "789", "Chris", "Chris");

        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "Information niveaux\\Drag & Drop\\Niveau 1.txt"));
        //listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "niveau 3", "", ""));

        listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, "Information niveaux\\Shooter\\Niveau 1.txt"));
        //listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, "niveau 2", "", ""));

        listeNiveauxCoureur.add(new Niveau(Jeu.COUREUR, "Information niveaux\\Coureur\\Niveau 1.txt"));
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

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void validerUtilisateur(String da, char[] motdepasse) {
        for (int i = 0; i < listeUtilisateurs.size(); i++) {
            if (da.equals(listeUtilisateurs.get(i).getDa())) {
                String motDePasse = "";
                for (int j = 0; j < motdepasse.length; j++) {
                    motDePasse += motdepasse[j];
                }
                if (motDePasse.equals(listeUtilisateurs.get(i).getMotDePasse())) {
                    this.utilisateur = listeUtilisateurs.get(i);
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
        this.utilisateur = null;
    }

    private void creerUtilisateur(String da, String motDePasse, String nom, String prenom) {
        String informations = "Utilisateurs\\Etudiants\\" + da + "_" + nom + prenom;

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(informations));
            bufferedWriter.write(da);
            bufferedWriter.newLine();
            bufferedWriter.write(motDePasse);
            bufferedWriter.newLine();
            bufferedWriter.write(nom);
            bufferedWriter.newLine();
            bufferedWriter.write(prenom);
            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }

        listeUtilisateurs.add(new Etudiant(da, motDePasse, nom, prenom, informations));
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

    public int[][] getCoordonneesBoitesReponsesDragDrop(int i) {
        return listeNiveauxDragDrop.get(i).getCoordonnees();
    }

    public ArrayList getQuestionsDragDrop(int i) {
        return listeNiveauxDragDrop.get(i).getQuestions();
    }

    public ArrayList getPositionReponses(int i) {
        return listeNiveauxCoureur.get(i).getPositionReponsesCoureur();
    }

    public ArrayList getReponseCoureur(int i) {
        return listeNiveauxCoureur.get(i).getReponsesCoureur();
    }
    
    public ArrayList getQuestionsCoureur(int i){
        return listeNiveauxCoureur.get(i).getQuestionCoureur();
    }

    public void calculerScoreDragDrop(int i, int nombreErreurs, int temps) {
        listeNiveauxDragDrop.get(i).setScore((100 - (nombreErreurs * 10)) - (temps / 30));
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
