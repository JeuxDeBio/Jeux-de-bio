//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.coureur;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class BoiteChoix extends JComponent {

    private int largeur = 100, hauteur = 50;
    private JLabel lblChoix = new JLabel("", JLabel.CENTER);
    private String texte;
    private int position;

    public BoiteChoix(String texte, int position) {
        this.texte = texte;
        this.position = position;
        this.setSize(largeur, hauteur);
        lblChoix.setText(texte);
        lblChoix.setSize(largeur, hauteur);
        lblChoix.setLocation(0, 0);
        this.add(lblChoix);
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, largeur, hauteur);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);
    }

    public String getTexte() {
        return texte;
    }

    public int getPosition() {
        return position;
    }

}
