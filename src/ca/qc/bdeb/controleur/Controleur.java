//Note: toutes les methodes QUI NE SONT PAS DE SIMPLES GETTERS ont une javadoc 
package ca.qc.bdeb.controleur;

import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.modele.Professeur;
import ca.qc.bdeb.vue.dragDrop.BoiteReponseConstruction;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import ca.qc.bdeb.vue.principale.Icone;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * Le controleur de l'application. Lien entre les classes de vue et le modele
 *
 * @author Batikan & Nicolas
 */
public class Controleur {

    private Modele modele;

    /**
     * Le constructeur de la classe
     */
    public Controleur() {
        modele = new Modele();
        FenetrePrincipale fenetre = new FenetrePrincipale(this, modele);
        fenetre.setLocation((((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - fenetre.getWidth()) / 2, (((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - fenetre.getHeight()) / 2));
    }

    /**
     * Fournit les informations pour valider l'identite d'un etudiant
     *
     * @param etudiantDA le DA fourni par lutilisateur
     * @param etudiantMotdepasse le mot de passe fourni par l'utilisateur
     */
    public void validerEtudiant(String etudiantDA, char[] etudiantMotdepasse) {
        modele.validerEtudiant(etudiantDA, etudiantMotdepasse);
    }

    /**
     * Verifie si un etudiant est identifie dans le modele
     *
     * @return si un etudiant est identifie dans le modele
     */
    public boolean logInEtudiant() {
        return modele.logInEtudiant();
    }

    /**
     * Log out l'etudiant identifie
     */
    public void logOutEtudiant() {
        modele.logOutEtudiant();
    }

    /**
     * Fournit les informations pour valider l'identite d'un professeur
     *
     * @param professeurNU le nom d'utilisateur fourni par l'utilisateur
     * @param professeurMotdepasse le mot de passe fourni par l'utilisateur
     */
    public void validerProfesseur(String professeurNU, char[] professeurMotdepasse) {
        modele.validerProfesseur(professeurNU, professeurMotdepasse);
    }

    /**
     * Verifie si un professeur est identifie dans le modele
     *
     * @return si un professeur est identifie dans le modele
     */
    public boolean logInProfesseur() {
        return modele.logInProfesseur();
    }

    /**
     * Log our le professeur identifie
     */
    public void logOutProfesseur() {
        modele.logOutProfesseur();
    }

    public String getLocationFenetrePrincipale() {
        return modele.getLocationFenetrePrincipale();
    }

    public String getLocationInformation(Jeu jeu, int i) {
        return modele.getLocationInformation(jeu, i);
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

    /**
     * Verifie si le DA fourni par l'utilisateur correspond a un DA qu'un
     * professeur a permis
     *
     * @param da le DA fourni par l'utilisateur
     * @return si le DA est permis par un professeur
     */
    public boolean etudiantPermis(String da) {
        return modele.etudiantPermis(da);
    }

    /**
     * Verifie si le DA fourni par l'utilisateur correspond au DA d'un etudiant
     * qui a deja cree son compte
     *
     * @param da le DA fourni par l'utilisateur
     * @return si le DA correspond a un DA existant
     */
    public boolean etudiantExisteDeja(String da) {
        return modele.etudiantExisteDeja(da);
    }

    public String getNomProfesseurNouveauEtudiant() {
        return modele.getNomProfesseurNouveauEtudiant();
    }

    public String getCodeGroupeNouveauEtudiant() {
        return modele.getCodeGroupeNouveauEtudiant();
    }

    /**
     * Verifie si le nom d'utilisateur fourni par l'utilisateur correspond a un
     * nom d'utilisateur qu'un administrateur a deja permis
     *
     * @param nu le NU fourni par l'utilisateur
     * @return si le NU est permis par un admin
     */
    public boolean professeurPermis(String nu) {
        return modele.professeurPermis(nu);
    }

    /**
     * Verifie si le nom d'utilisateur fourni par l'utilisateur correspond au NU
     * d'un professeur qui a deja cree son compte
     *
     * @param nu le NU fourni par l'utilisateur
     * @return si le NU correspond a un NU existant
     */
    public boolean professeurExisteDeja(String nu) {
        return modele.professeurExisteDeja(nu);
    }

    /**
     * Verifie si le nom d'utilisateur fourni par l'admin correspond a un NU qui
     * a deja ete authentifie, sans qu'un professeur cree son compte
     *
     * @param nu le NU fourni par l'admin
     * @return si le NU est deja authentifie
     */
    public boolean professeurDejaAuthentifie(String nu) {
        return modele.professeurDejaAuthentifie(nu);
    }

    /**
     * Cree un compte etudiant avec les donnees fournies par l'utilisateur
     *
     * @param da le DA fourni par l'utilisateur
     * @param motDePasse le mot de passe fourni par l'utilisateur
     * @param nom le nom fourni par l'utilisateur
     */
    public void creerEtudiant(String da, String motDePasse, String nom) {
        modele.creerEtudiant(da, motDePasse, nom);
    }

    /**
     * Cree un compte professeur avec les donnees fournies par l'utitilateur
     *
     * @param nu le NU fourni par l'utilisateur
     * @param mdp le mot de passe fourni par l'utilisateur
     * @param nom le nom fourni par l'utilisateur
     * @param session le session fourni par l'utilisateur
     */
    public void creerProfesseur(String nu, String mdp, String nom, String session) {
        modele.creerProfesseur(nu, mdp, nom, session);
    }

    public Etudiant getEtudiant() {
        return modele.getEtudiant();
    }

    public Professeur getProfesseur() {
        return modele.getProfesseur();
    }

    public String getNomNiveau(Jeu jeu, int codeNiveau) {
        return modele.getNomNiveau(jeu, codeNiveau);
    }

    public String getLocationNiveau(Jeu jeu, int codeNiveau) {
        return modele.getLocationNiveau(jeu, codeNiveau);
    }

    public String getLocationNiveauCorrige(Jeu jeu, int codeNiveau) {
        return modele.getLocationNiveauCorrige(jeu, codeNiveau);
    }

    public int[] getSizeImageDragDrop(int codeNiveau) {
        return modele.getSizeImageDragDrop(codeNiveau);
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

    public String getLocationTitreDragDrop() {
        return modele.getLocationTitreDragDrop();
    }

    public String getLocationTitreShooter() {
        return modele.getLocationTitreShooter();
    }

    public String getLocationTitreCoureur() {
        return modele.getLocationTitreCoureur();
    }

    public String getLocationTitreSpeedRun() {
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

    public String getLocationIconeApplication() {
        return modele.getLocationIconeApplication();
    }

    public void calculerScoreDragDrop(int i, int nombreErreurs) {
        modele.calculerScoreDragDrop(i, nombreErreurs);
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

    /**
     * Change le mot de passe de l'etudiant identifie avec le mot de passe
     * fourni par l'etudiant
     *
     * @param nouveauMDP le nouveau mot de passe fourni par l'etudiant
     */
    public void etudiantModificationMDP(String nouveauMDP) {
        modele.etudiantModificationMDP(nouveauMDP);
    }

    /**
     * Change le mot de passe du professeur identifie avec le mot de passe
     * fourni par le professeur
     *
     * @param nouveauMDP le nouveau mot de passe fourni par le professeur
     */
    public void professeurModificationMDP(String nouveauMDP) {
        modele.professeurModificationMDP(nouveauMDP);
    }

    public String getLogInErrorLog() {
        return modele.getLogInErrorLog();
    }

    public String getMessageErreur(int i) {
        return modele.getMessageErreur(i);
    }

    /**
     * Enleve l'etudiant du groupe fournis
     *
     * @param groupe le groupe de l'etudiant a enlever
     * @param etudiant l'etudiant a enlever
     */
    public void enleverEtudiant(Groupe groupe, Etudiant etudiant) {
        modele.enleverEtudiant(groupe, etudiant);
    }

    /**
     * Enleve le professeur fourni
     *
     * @param professeur le professeur a enlever
     */
    public void enleverProfesseur(Professeur professeur) {
        modele.enleverProfesseur(professeur);
    }

    /**
     * Enlever le groupe fourni
     *
     * @param groupe le groupe a enlever
     */
    public void enleverGroupe(Groupe groupe) {
        modele.enleverGroupe(groupe);
    }

    public ArrayList<Icone> getListeIcones() {
        return modele.getListeIcones();
    }

    public Icone getIconeVierge() {
        return modele.getIconeVierge();
    }

    public ArrayList<Professeur> getListeProfesseurs() {
        return modele.getListeProfesseurs();
    }

    /**
     * Authentifier le nom d'utiliateur fourni et permet la creation d'un compte
     * professeur avec ce nom d'utilisateur
     *
     * @param nuAdmis le nom d'utilisateur fourni
     */
    public void ajouterProfesseurNUAdmis(String nuAdmis) {
        modele.ajouterProfesseurNUAdmis(nuAdmis);
    }

    /**
     * Creer un groupe avec le code du groupe et les DA des etudiants fournis
     *
     * @param liste contient le code et les DA etudiants
     */
    public void creerGroupe(ArrayList<String> liste) {
        modele.creerGroupe(liste);
    }

    /**
     * Creer un admin
     *
     * @param professeur le professeur qui devient l'admin
     */
    public void cederAdmin(Professeur professeur) {
        modele.cederAdmin(professeur);
    }

    /**
     * Modifier un niveau Drag & Drop
     *
     * @param nom le nom du niveau
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrigee le location de l'image du corrige
     * @param tailleImage la taille (width et height) des images (ils doivent
     * avoir la meme taille)
     * @param listeBoites la liste des boites de reponses
     * @param id l'identifiant du niveau modifie
     */
    public void modifierNiveauDragDrop(String nom, String locationImage, String locationImageCorrigee, int[] tailleImage, ArrayList<BoiteReponseConstruction> listeBoites, int id) {
        String taille = "";
        taille += tailleImage[0] + ";" + tailleImage[1];
        String index = "";
        for (int i = 1; i < listeBoites.size(); i++) {
            index += i + ";";
        }
        index += listeBoites.size();
        modele.modifierNiveauDragDrop(nom, locationImage, locationImageCorrigee, taille, listeBoites, index, id);

    }

    /**
     * Creer un niveau Drag & Drop
     *
     * @param titre le titre du niveau
     * @param locationImage le location de l'image de jeu
     * @param locationImageCorrige le location de l'image du corrige
     * @param largeur le largeur de l'image
     * @param hauteur la hauteur de l'image
     * @param liste la liste des boites de reponses
     */
    public void creerNiveauDragDrop(String titre, String locationImage, String locationImageCorrige, String largeur, String hauteur, ArrayList<BoiteReponseConstruction> liste) {
        modele.creerNiveauDragDrop(titre, locationImage, locationImageCorrige, largeur, hauteur, liste);
    }

    public int getIdNiveau(Jeu jeu, int a) {
        return modele.getIDNiveau(jeu, a);
    }

    /**
     * Ferme l'application
     */
    public void fermerApp() {
        modele.fermerApp();
    }

    /**
     * Reload toutes les donnees
     */
    public void refresh() {
        modele.refresh();
    }
}
