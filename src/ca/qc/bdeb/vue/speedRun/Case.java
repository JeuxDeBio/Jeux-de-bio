//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.speedRun;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class Case extends JComponent {

    private final int largeur = 50, hauteur = 25;
    private boolean afficheProgres = false;

    public Case() {
        this.setSize(largeur, hauteur);
    }

    public void affiche() {
        this.afficheProgres = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (afficheProgres) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, largeur, hauteur);
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);
    }

}
