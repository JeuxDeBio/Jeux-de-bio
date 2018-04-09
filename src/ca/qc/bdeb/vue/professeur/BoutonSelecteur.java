/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class BoutonSelecteur extends JComponent {

    private final int largeur = 20, hauteur = 20;
    private boolean estPresse = false;

    public BoutonSelecteur() {
        this.setSize(largeur, hauteur);
    }
    
    public void press(){
        this.estPresse = !this.estPresse;
    }
    
    public boolean estPresse(){
        return estPresse;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (estPresse){
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.WHITE);
        }
        
        g.fillRect(0, 0, largeur, hauteur);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);
    }
    
    

}
