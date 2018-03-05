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
 * @author Niopo
 */
public class Perso extends JComponent{
     Position position;
     
     public enum Position{
         GAUCHE_EXT,
         GAUCHE_INT,
         MILIEU,
         DROITE_INT,
         DROITE_EXT;
     }

    public Perso(Position position) {
        this.position = position;
        setSize(36, 76);
    }
           
     public void paintComponent(Graphics g){
         g.setColor(Color.red);
      g.fillRect(35,75, 0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
     
}
