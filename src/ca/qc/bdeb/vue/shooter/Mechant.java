/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.shooter;

import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author Niopo
 */
public class Mechant extends Composant{
    private boolean vivant = true;

    public Mechant(Couleur couleur, int vitesse, Direction direction) {
        super(couleur, vitesse, direction);
        this.setSize(51,51);
    }

    public void paintComponent(Graphics g) {
        switch (couleur) {
            case BLEU:
                g.setColor(Color.BLUE);
                break;
            case ROUGE:
                g.setColor(Color.RED);
                break;
        }
        g.fillOval(0, 0, 50, 50);
    }

    
    public boolean isVivant() {
        return vivant;
    }

   
    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }

   

    
    
    
}
