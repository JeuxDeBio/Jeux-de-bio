/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controleur;

import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.modele.Professeur;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import ca.qc.bdeb.vue.principale.Icone;
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
        fenetre.setLocation((((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - fenetre.getWidth()) / 2, (((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - fenetre.getHeight()) / 2));
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

    public String getLocationFenetreModificationMDP() {
        return modele.getLocationFenetreModiicationMDP();
    }

    public String getLocationFenetreStatistiquesEtudiant() {
        return modele.getLocationFenetreStatistiquesEtudiant();
    }

    public String getLocationFenetreStatistiquesJeu() {
        return modele.getLocationFenetreStatistiquesJeu();
    }

    public String getLocationFenetreStatistiquesGroupe() {
        return modele.getLocationFenetreStatistiquesGroupe();
    }

    public String getLocationFenetreClasses() {
        return modele.getLocationFenetreClasses();
    }

    public String getLocationFenetreModificationIcone() {
        return modele.getLocationFenetreModificationIcone();
    }

    public String getLocationFenetreInscriptionEtudiants1() {
        return modele.getLocationFenetreInscriptionEtudiants1();
    }

    public String getLocationFenetreInscriptionEtudiants2() {
        return modele.getLocationFenetreInscriptionEtudiants2();
    }

    public String getLocationFenetreInscriptionProfesseurs1() {
        return modele.getLocationFenetreInscriptionProfesseurs1();
    }

    public String getLocationFenetreInscriptionProfesseurs2() {
        return modele.getLocationFenetreInscriptionProfesseurs2();
    }

    public String getLocationFenetreAjoutClasses() {
        return modele.getLocationFenetreAjoutClasses();
    }

    public boolean etudiantPermis(String da) {
        return modele.etudiantPermis(da);
    }

    public boolean etudiantExisteDeja(String da) {
        return modele.etudiantExisteDeja(da);
    }

    public String getNomProfesseurNouveauEtudiant() {
        return modele.getNomProfesseurNouveauEtudiant();
    }

    public String getCodeGroupeNouveauEtudiant() {
        return modele.getCodeGroupeNouveauEtudiant();
    }

    public boolean professeurPermis(String da) {
        return modele.professeurPermis(da);
    }

    public boolean professeurExisteDeja(String nu) {
        return modele.professeurExisteDeja(nu);
    }

    public boolean professeurDejaAuthentifie(String nu) {
        return modele.professeurDejaAuthentifie(nu);
    }

    public void creerEtudiant(String da, String motDePasse, String nom) {
        modele.creerEtudiant(da, motDePasse, nom);
    }

    public void creerProfesseur(String nu, String mdp, String nom, String session) {
        modele.creerProfesseur(nu, mdp, nom, session);
    }

    public Etudiant getEtudiant() {
        return modele.getEtudiant();
    }

    public Professeur getProfesseur() {
        return modele.getProfesseur();
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
    
    public String getLocationTitreDragDrop(){
        return modele.getLocationTitreDragDrop();
    }
    
    public String getLocationTitreShooter(){
        return modele.getLocationTitreShooter();
    }
    
    public String getLocationTitreCoureur(){
        return modele.getLocationTitreCoureur();
    }
    
    public String getLocationTitreSpeedRun(){
        return modele.getLocationTitreSpeedRun();
    }
    
    public String getLocationFlecheDroite() {
        return modele.getLocationFlecheDroite();
    }

    public String getLocationFlecheGauche() {
        return modele.getLocationFlecheGauche();
    }

    public String getLocationFlecheHaut() {
        return modele.getLocationFlecheHaut();
    }

    public String getLocationFlecheBas() {
        return modele.getLocationFlecheBas();
    }

    public void calculerScoreDragDrop(int i, int nombreErreurs) {
        modele.calculerScoreDragDrop(i, nombreErreurs);
    }

    public void calculerScoreShooter(int i, int nombreBots) {
        modele.calculerScoreShooter(i, nombreBots);
    }

    public void calculerScoreCoureur(int i, int nombreBonneReponses) {
        modele.calculerScoreCoureur(i, nombreBonneReponses);
    }

    public void calculerScoreSpeedRun(int i, int joueurScore) {
        modele.calculerScoreSpeedRun(i, joueurScore);
    }

    public int getScoreNiveau(Jeu jeu, int i) {
        return modele.getScoreNiveau(jeu, i);
    }

    public void etudiantModificationMDP(String nouveauMDP) {
        modele.etudiantModificationMDP(nouveauMDP);
    }

    public void professeurModificationMDP(String nouveauMDP) {
        modele.professeurModificationMDP(nouveauMDP);
    }

    public String getLogInErrorLog() {
        return modele.getLogInErrorLog();
    }

    public void enleverEtudiant(Groupe groupe, Etudiant etudiant) {
        modele.enleverEtudiant(groupe, etudiant);
    }

    public void enleverProfesseur(Professeur professeur) {
        modele.enleverProfesseur(professeur);
    }

    public void enleverGroupe(Groupe groupe) {
        modele.enleverGroupe(groupe);
    }

    public ArrayList<Icone> getListeIcones() {
        return modele.getListeIcones();
    }
    
    public Icone getIconeVierge(){
        return modele.getIconeVierge();
    }

    public ArrayList<Professeur> getListeProfesseurs() {
        return modele.getListeProfesseurs();
    }

    public void ajouterProfesseurNUAdmis(String nuAdmis) {
        modele.ajouterProfesseurNUAdmis(nuAdmis);
    }

    public void creerGroupe(ArrayList<String> liste) {
        modele.creerGroupe(liste);
    }

    public void cederAdmin(Professeur professeur) {
        modele.cederAdmin(professeur);
    }

    public void fermerApp() {
        modele.fermerApp();
    }

    public void refresh() {
        modele.refresh();
    }
}
