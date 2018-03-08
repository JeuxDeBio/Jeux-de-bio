/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class Ronde_question extends JComponent {

    Boite_question boite;

    private int largeur = 20, hauteur = 20;
    private int initialX = 100, initialY = 100;

    private boolean hold = false;

    public Ronde_question(String texte) {
        boite = new Boite_question(texte);
        this.setSize(boite.getWidth(), boite.getHeight());
        this.add(boite);
        boite.setLocation(0, 0);
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void occupeTrue() {
        boite.occupeTrue();
    }

    public void occupeFalse() {
        boite.occupeFalse();
    }

    public boolean occupe() {
        return boite.occupe();
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

    public void holdTrue() {
        this.hold = true;
    }

    public void holdFalse() {
        this.hold = false;
    }

    public boolean hold() {
        return hold;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY() + 15, 20, 20);
    }
}
