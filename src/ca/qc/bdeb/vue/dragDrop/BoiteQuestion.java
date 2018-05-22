//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Boite de question
 *
 * @author Batikan
 */
public class BoiteQuestion extends JComponent {

    private int largeur = 250, hauteur = 30;
    private JLabel lblQuestion = new JLabel();

    private boolean occupe = false;

    private String texte;

    /**
     * Constructeur pour un niveau de jeu
     *
     * @param texte le texte a afficher dans la boite
     */
    public BoiteQuestion(String texte) {
        this.texte = texte;
        this.setSize(largeur, hauteur);
        lblQuestion.setText(texte);
        lblQuestion.setSize(largeur, hauteur);
        lblQuestion.setLocation(30, 0);
        this.add(lblQuestion);
    }

    /**
     * Constructeur pour le niveau tutoriel
     */
    public BoiteQuestion() {
        largeur = 60;
        this.setSize(largeur, hauteur);
        lblQuestion.setText("^.^");
        lblQuestion.setSize(largeur, hauteur);
        lblQuestion.setLocation(30, 0);
        this.add(lblQuestion);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (!occupe) {
            g.setColor(Color.WHITE);
            g.fillRect(20, 0, largeur - 20, hauteur);
            g.setColor(Color.BLACK);
            g.drawRect(20, 0, largeur - 21, hauteur - 1);
            g.setColor(Color.RED);
            g.fillOval(0, 5, 20, 20);
            g.setColor(Color.BLACK);
            g.drawOval(0, 5, 20, 20);
        }
    }

    public void occupeTrue() {
        occupe = true;
    }

    public void occupeFalse() {
        occupe = false;
    }

    /**
     * Verifie si la boite de reponses contient un ronde de question
     *
     * @return si la boite de reponses contient un ronde de question
     */
    public boolean occupe() {
        return occupe;
    }

    public String getTexte() {
        return texte;
    }

}
