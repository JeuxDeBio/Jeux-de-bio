/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class Boite_reponse extends JComponent {

    private int largeur = 20, hauteur = 20;
    private int initialX = 0, initialY = 0;

    private boolean estOccupe = false;
    private boolean bonneReponse = false;

    private Ronde_question question;
    
    private String texte;

    public Boite_reponse(String texte) {
        this.setSize(largeur, hauteur);
        this.texte = texte;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, largeur, hauteur);
        if (estOccupe) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, largeur, hauteur);
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);
        if (!estOccupe) {
            g.drawLine(0, 0, largeur, hauteur);
            g.drawLine(0, hauteur, largeur, 0);
        }
    }

    public boolean estOccupe() {
        return estOccupe;
    }

    public void occupeTrue(Ronde_question question) {
        this.estOccupe = true;
        this.question = question;
    }

    public void occupeFalse() {
        this.estOccupe = false;
        this.question = null;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public void verification() {
        this.bonneReponse = (question != null) && (question.getBoite().getTexte().equals(this.texte));
        
    }

    public boolean bonneReponse() {
        return bonneReponse;
    }

    public Ronde_question getQuestion() {
        return question;
    }

}
