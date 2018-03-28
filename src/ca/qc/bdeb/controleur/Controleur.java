/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controleur;

import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.Toolkit;
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
        fenetre.setLocation((((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - fenetre.getWidth()) / 2, 20);
    }

    public void validerEtudiant(String etudiantDA, char[] etudiantMotdepasse) {
        modele.validerEtudiant(etudiantDA, etudiantMotdepasse);
    }

    public boolean logInEtudiant() {
        return modele.logInEtudiant();
    }

    public void logOutEtudiant() {
        modele.logOutEtudiant();
    }

    public void validerProfesseur(String professeurDA, char[] professeurMotdepasse) {
        modele.validerProfesseur(professeurDA, professeurMotdepasse);
    }

    public boolean logInProfesseur() {
        return modele.logInProfesseur();
    }

    public void logOutProfesseur() {
        modele.logOutProfesseur();
    }

    public String getLocationFenetrePrincipale() {
        return modele.getLocationFenetrePrincipale();
    }

    public String getLocationFenetreEtudiant() {
        return modele.getLocationFenetreEtudiant();
    }

    public String getLocationFenetreProfesseur() {
        return modele.getLocationFenetreProfesseur();
    }

    public String getLocationFenetreSelection() {
        return modele.getLocationFenetreSelection();
    }

    public String getLocationFenetreInscription(String personne) {
        if (personne.equals("etudiant")) {
            return modele.getLocationFenetreInscriptionEtudiants();
        } else {
            return modele.getLocationFenetreInscriptionProfesseurs();
        }
    }

    public boolean etudiantPermis(String da) {
        return modele.etudiantPermis(da);
    }

    public boolean professeurExiste(String da) {
        return modele.professeurExiste(da);
    }

    public void creerProfesseur(String nom) {
        modele.creerProfesseur(nom);
    }

    public Etudiant getEtudiant() {
        return modele.getEtudiant();
    }

    public void creerEtudiant(String motDePasse) {
        modele.creerUtilisateur(motDePasse);

    }

    public String getNomNiveau(Jeu jeu, int i) {
        return modele.getNomNiveau(jeu, i);
    }

    public String getLocationNiveau(Jeu jeu, int i) {
        return modele.getLocationNiveau(jeu, i);
    }

    public String getLocationNiveauCorrige(Jeu jeu, int i) {
        return modele.getLocationNiveauCorrige(jeu, i);
    }

    public int[] getSizeImageDragDrop(int i) {
        return modele.getSizeImageDragDrop(i);
    }

    public ArrayList<int[]> getCoordonneesBoitesReponsesDragDrop(int i) {
        return modele.getCoordonneesBoitesReponsesDragDrop(i);
    }

    public ArrayList getQuestionsDragDrop(int i) {
        return modele.getQuestionsDragDrop(i);
    }

    public ArrayList getPositionQuestions(int i) {
        return modele.getPositionReponsesCoureur(i);
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

    public String getLocationCoeur() {
        return modele.getLocationCoeur();
    }

    public void calculerScoreSpeedRun(int i, int joueurScore, int nombreQuestions) {
        modele.calculerScoreSpeedRun(i, joueurScore, nombreQuestions);
    }

    public int getScoreNiveau(Jeu jeu, int i) {
        return modele.getScoreNiveau(jeu, i);
    }
}
