//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Batikan
 */
public class FenetreModificationIcone extends JFrame {

    private Controleur controleur;

    private FenetrePrincipale fenetre;

    private MondeModificationIcone mondeModification;
    private MondeVisualisationIcone mondeVisualisation;

    public FenetreModificationIcone(Controleur controleur, FenetrePrincipale fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Cree l'interface graphique
     */
    private void creerInterface() {
        this.setTitle("Modification de l'icône");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        this.setResizable(false);
        mondeModification = new MondeModificationIcone(controleur, this);
        this.add(mondeModification);
    }

    /**
     * Met le monde de visualisation des icones
     */
    public void ajouterMondeVisualisation() {
        this.setTitle("Voir toutes les icônes");
        this.remove(mondeModification);
        mondeVisualisation = new MondeVisualisationIcone(controleur, this);
        this.add(mondeVisualisation);
        pack();
        invalidate();
        repaint();
    }

    /**
     * Met le monde de modification des icones
     */
    public void ajouterMondeModification() {
        this.setTitle("Modification de l'icône");
        this.remove(mondeVisualisation);
        mondeModification = new MondeModificationIcone(controleur, this);
        this.add(mondeModification);
        pack();
        invalidate();
        repaint();
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreModificationIcone();
    }

    public void updateFenetre() {
        fenetre.updateFenetre();
    }
}
