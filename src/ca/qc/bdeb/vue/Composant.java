/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import javax.swing.JComponent;

/**
 *
 * @author Niopo
 */
public class Composant extends JComponent {

    Couleur couleur;
    private int vitesse;
    Direction direction;

    public enum Couleur {
        BLEU,
        ROUGE;
    }

    public enum Direction {
        N,
        S,
        E,
        O,
        NE,
        NO,
        SE,
        SO;
    }

    public Composant(Couleur couleur, int vitesse, Direction direction) {
        this.couleur = couleur;
        this.vitesse = vitesse;
        this.direction = direction;
    }

    

    public Couleur getCouleur() {
        return couleur;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
    
}
