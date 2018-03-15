/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Joueur extends JComponent {

    private final int largeur = 36, hauteur = 76;
    private BoiteChoix choix;
    private boolean faitChoix = false;

    public Joueur() {
        setSize(largeur, hauteur);
    }

    public void setChoix(BoiteChoix choix) {
        this.choix = choix;
    }

    public void faitchoixTrue() {
        faitChoix = true;
    }

    public void faitChoixFalse() {
        this.faitChoix = false;
    }

    public boolean faitChoix() {
        return faitChoix;
    }

    public BoiteChoix getChoix() {
        return choix;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);
    }

}
