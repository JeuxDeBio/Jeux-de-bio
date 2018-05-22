//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.coureur;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Nicolas
 */
public class Boite extends JComponent {

    private final int largeur = 16, hauteur = 16;
    private boolean questionComplete = false;

    public Boite() {
        this.setSize(largeur, hauteur);
    }

    public void affiche() {
        this.questionComplete = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (!questionComplete) {
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, largeur - 1, hauteur - 1);
            g.setColor(Color.GREEN);
            g.fillRect(6, 0, 4, 16);
            g.fillRect(0, 6, 16, 4);
        }
        g.setColor(Color.RED);
        g.fillRect(0, 0, largeur - 1, hauteur - 1);
    }

}
