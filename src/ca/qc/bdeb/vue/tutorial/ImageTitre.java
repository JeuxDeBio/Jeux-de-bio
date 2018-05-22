//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.tutorial;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class ImageTitre extends JComponent {

    private Image image;

    private final int largeur = 250, hauteur = 50;

    public ImageTitre(Controleur controleur, Jeu jeu) {
        String locationImage = "";

        switch (jeu) {
            case DRAG_DROP:
                locationImage = controleur.getLocationTitreDragDrop();
                break;
            case SHOOTER:
                locationImage = controleur.getLocationTitreShooter();
                break;
            case COUREUR:
                locationImage = controleur.getLocationTitreCoureur();
                break;
            case SPEED_RUN:
                locationImage = controleur.getLocationTitreSpeedRun();
        }

        image = Toolkit.getDefaultToolkit().getImage(locationImage);

        this.setSize(largeur, hauteur);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
