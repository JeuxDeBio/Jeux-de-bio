//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Batikan
 */
public class FenetreSelection extends JFrame {

    private Modele modele;

    private Controleur controleur;

    private FenetrePrincipale fenetrePrincipale;

    private FenetreJeu fenetreJeu;
    private FenetreModification fenetreModification;

    private MondeSelection monde;

    private Jeu jeu;

    public FenetreSelection(Modele modele, Controleur controleur, FenetrePrincipale fenetrePrincipale, Jeu jeu, FenetreJeu fenetreJeu, String action, FenetreModification fenetreModification) {
        this.modele = modele;
        this.controleur = controleur;
        this.fenetrePrincipale = fenetrePrincipale;
        this.jeu = jeu;
        this.fenetreJeu = fenetreJeu;
        this.fenetreModification = fenetreModification;

        creerInterface(action);

        this.pack();
        this.setVisible(true);

    }

    /**
     * Cree l'interface graphique
     *
     * @param action le type de selection (jeu ou modification de niveau)
     */
    private void creerInterface(String action) {
        this.monde = new MondeSelection(modele, controleur, this, jeu, action);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Sélection de niveaux");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        this.setResizable(false);
        this.add(monde);
    }

    public void fermerFenetreSelection() {
        fenetrePrincipale.fermerFenetreSelection();
    }

    /**
     * Ouvre un niveau de jeu
     * @param i l'identifiant du niveau
     * @param action le type de selection
     */
    public void ouvrirFenetreJeu(int i, String action) {
        if (action.equals("jouer")) {
            this.fenetreJeu = new FenetreJeu(jeu, this, fenetrePrincipale, controleur, modele, i);
            fenetreJeu.setLocation(fenetrePrincipale.getX() + (fenetrePrincipale.getWidth() - fenetreJeu.getWidth()) / 2, fenetrePrincipale.getY());
        } else {
            this.fenetreModification = new FenetreModification(jeu, this, fenetrePrincipale, fenetreJeu, controleur, modele, i);
            fenetreModification.setLocation(fenetrePrincipale.getX() + (fenetrePrincipale.getWidth() - fenetreModification.getWidth()) / 2, fenetrePrincipale.getY());
        }
    }

    public void fermerFenetreJeu() {
        this.fenetreJeu.dispose();
    }

    public void fermerFenetreModification() {
        this.fenetreModification.dispose();
    }

    public void finJeu() {
        fenetrePrincipale.finJeu();
    }
}
