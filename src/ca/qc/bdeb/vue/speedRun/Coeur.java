//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.speedRun;

import ca.qc.bdeb.controleur.Controleur;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class Coeur extends JComponent {

    private Controleur controleur;
    private Image image;
    private final int largeur = 32, hauteur = 32;
    boolean detruite = false;

    public Coeur(Controleur controleur) {
        this.controleur = controleur;
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationCoeur());
        this.setSize(largeur, hauteur);
    }

    /**
     * Detruit cette coeur
     */
    public void detruire() {
        this.detruite = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (!detruite) {
            g.drawImage(image, 0, 0, this);
        }
    }

}
