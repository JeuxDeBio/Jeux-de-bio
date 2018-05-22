//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.tutorial;

import ca.qc.bdeb.controleur.Controleur;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class Fleche extends JComponent {

    private Image image;

    private int largeur, hauteur;

    public Fleche(Controleur controleur, Direction direction) {
        String locationImage = "";

        switch (direction) {
            case GAUCHE:
                largeur = 50;
                hauteur = 40;
                locationImage = controleur.getLocationFlecheGauche();
                break;
            case DROITE:
                largeur = 50;
                hauteur = 40;
                locationImage = controleur.getLocationFlecheDroite();
                break;
            case BAS:
                largeur = 40;
                hauteur = 50;
                locationImage = controleur.getLocationFlecheBas();
                break;
            case HAUT:
                largeur = 40;
                hauteur = 50;
                locationImage = controleur.getLocationFlecheHaut();
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
