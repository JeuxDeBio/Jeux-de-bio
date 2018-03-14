/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controleur;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.util.ArrayList;

/**
 *
 * @author 164990
 */
public class Controleur {

    private Modele modele;

    public Controleur() {
        modele = new Modele();
        FenetrePrincipale fenetre = new FenetrePrincipale(this, modele);
    }

    public void validerUtilisateur(String utilisateurDA, char[] utilisateurMotDePasse) {
        modele.validerUtilisateur(utilisateurDA, utilisateurMotDePasse);
    }

    public String getLocationFenetrePrincipale() {
        return modele.getLocationFenetrePrincipale();
    }

    public String getLocationFenetrePrincipaleLogIn() {
        return modele.getLocationFenetrePrincipaleLogIn();
    }

    public String getLocationFenetreSelection() {
        return modele.getLocationFenetreSelection();
    }

    public String getNomNiveau(Jeu jeu, int i) {
        return modele.getNomNiveau(jeu, i);
    }

    public String getLocationNiveau(Jeu jeu, int i) {
        return modele.getLocationNiveau(jeu, i);
    }

    public int[][] getCoordonneesBoitesReponsesDragDrop(int i) {
        return modele.getCoordonneesBoitesReponsesDragDrop(i);
    }

    public ArrayList getQuestionsDragDrop(int i) {
        return modele.getQuestionsDragDrop(i);
    }

    public ArrayList getPositionQuestions(int i) {
        return modele.getPositionReponses(i);
    }

    public ArrayList getReponsesCoureur(int i) {
        return modele.getReponseCoureur(i);
    }

    public ArrayList getQuestionsCoureur(int i) {
        return modele.getQuestionsCoureur(i);
    }

    public void calculerScoreDragDrop(int i, int nombreErreurs, int temps) {
        modele.calculerScoreDragDrop(i, nombreErreurs, temps);
    }

    public ArrayList getQuestionsSpeedRun(int i) {
        return modele.getQuestionsSpeedRun(i);
    }

    public ArrayList getReponsesSpeedRun(int i) {
        return modele.getReponsesSpeedRun(i);
    }
    
    public String getLocationRobot1() {
        return modele.getLocationRobot1();
    }

    public String getLocationRobot2() {
        return modele.getLocationRobot2();
    }

    public String getLocationRobot3() {
        return modele.getLocationRobot3();
    }
    
    public void calculerScoreSpeedRun(int i, int joueurScore, int nombreQuestions){
        modele.calculerScoreSpeedRun(i, joueurScore, nombreQuestions);
    }
    

    public int getScoreNiveau(Jeu jeu, int i) {
        return modele.getScoreNiveau(jeu, i);
    }
}
