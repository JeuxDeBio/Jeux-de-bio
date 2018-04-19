/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.tutorial;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
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
    
    public void pasAffiche() {
        this.afficheProgres = false;
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
