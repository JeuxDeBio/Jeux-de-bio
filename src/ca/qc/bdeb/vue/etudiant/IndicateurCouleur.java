//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.etudiant;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class IndicateurCouleur extends JComponent {

    private Color color;
    private final int largeur = 21, hauteur = 21;

    public IndicateurCouleur(Color color) {
        this.color = color;
        this.setSize(largeur, hauteur);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(color);
        g.fillRect(0, 0, largeur, hauteur);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);
    }

}
