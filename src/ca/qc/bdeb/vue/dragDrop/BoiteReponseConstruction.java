/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 *
 * @author Niopo
 */
public class BoiteReponseConstruction extends JComponent {

    private int largeur = 20, hauteur = 20;
    private int positionX = 0, positionY = 0;
    
    private String reponse;
    private boolean hold = false;

    public BoiteReponseConstruction(String reponse) {
        this.reponse = reponse;
         this.setSize(largeur, hauteur);
    }

    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, largeur - 1, hauteur - 1);
    }
    
    public void holdTrue() {
        this.hold = true;
    }

    public void holdFalse() {
        this.hold = false;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isHold() {
        return hold;
    }

    public String getReponse() {
        return reponse;
    }
    

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY() + 15, 20, 20);
    }
    

}
