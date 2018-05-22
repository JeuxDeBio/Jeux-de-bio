//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.etudiant;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Batikan
 */
public class FenetreStatistiqueEtudiant extends JFrame {

    private Controleur controleur;

    private MondeStatistiquesEtudiant mondeEtudiant;
    private MondeStatistiquesJeu mondeJeu;

    private FenetrePrincipale fenetre;

    /**
     * Constructeur de la fenetre si l'etudiant identifie appelle la classe
     *
     * @param controleur le controleur
     * @param fenetre la fenetre principale
     */
    public FenetreStatistiqueEtudiant(Controleur controleur, FenetrePrincipale fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.mondeEtudiant = new MondeStatistiquesEtudiant(controleur, this);
        this.add(mondeEtudiant);

        this.setTitle("Statistiques de " + controleur.getEtudiant().getDa());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Constructeur de la fenetre si le professeur identifie appelle la classe
     * pour un de ses etudiants
     *
     * @param controleur le controleur
     * @param fenetre la fenetre principale
     * @param etudiant l'etudiant fourni par le professeur
     */
    public FenetreStatistiqueEtudiant(Controleur controleur, FenetrePrincipale fenetre, Etudiant etudiant) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.mondeEtudiant = new MondeStatistiquesEtudiant(controleur, this, etudiant);
        this.add(mondeEtudiant);

        this.setTitle("Statistiques de " + etudiant.getDa());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Met le jeu choisie par l'etudant
     *
     * @param jeu le jeu a afficher
     */
    public void mettreJeuEtudiant(Jeu jeu) {
        this.remove(mondeEtudiant);
        this.mondeJeu = new MondeStatistiquesJeu(controleur, this, jeu);
        this.add(mondeJeu);
        this.validate();
        this.repaint();
    }

    /**
     * Met le jeu choisie par le professeur
     *
     * @param jeu le jeu a afficher
     * @param etudiant l'etudiant fourni
     */
    public void mettreJeuProfesseur(Jeu jeu, Etudiant etudiant) {
        this.remove(mondeEtudiant);
        this.mondeJeu = new MondeStatistiquesJeu(controleur, this, jeu, etudiant);
        this.add(mondeJeu);
        this.validate();
        this.repaint();
    }

    public void fermerJeu() {
        this.remove(mondeJeu);
        this.add(mondeEtudiant);
        this.validate();
        this.repaint();
    }

}
