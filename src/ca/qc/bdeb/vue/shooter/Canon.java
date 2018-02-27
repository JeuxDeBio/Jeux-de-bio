/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.shooter;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Niopo
 */
class Canon extends JComponent{
    
    Direction direction;
    private boolean peutTirer;
    private int nbVies =3;
    public enum Direction{
            N,
            S,
            E,
            O,
            NE,
            NO,
            SE,
            SO;
            
    }
    
    public Canon(){
        setSize(51,70);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isPeutTirer() {
        return peutTirer;
    }

    public void setPeutTirer(boolean peutTirer) {
        this.peutTirer = peutTirer;
    }

    public int getNbVies() {
        return nbVies;
    }

    public void setNbVies(int nbVies) {
        this.nbVies = nbVies;
    }
    
    public void paintComponent(Graphics g){
       direction = Direction.S;
        g.setColor(Color.DARK_GRAY);
        switch(direction){
            case S:
                g.fillRect(18, 25, 14, 45);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(0, 0, 50, 50);
        
    }
}
