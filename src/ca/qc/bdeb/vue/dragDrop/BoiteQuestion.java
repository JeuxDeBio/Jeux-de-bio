/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class BoiteQuestion extends JComponent {

    private int largeur = 200, hauteur = 30;
    private JLabel lblQuestion = new JLabel();

    private boolean occupe = false;

    private String texte;

    public BoiteQuestion(String texte) {
        this.texte = texte;
        this.setSize(largeur, hauteur);
        lblQuestion.setText(texte);
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

    public boolean occupe() {
        return occupe;
    }

    public String getTexte() {
        return texte;
    }

}
