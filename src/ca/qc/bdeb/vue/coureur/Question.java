/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

/**
 *
 * @author Niopo
 */
public class Question {
    private int nbChoix;
    Position position;
     
     public enum Position{
         GAUCHE_EXT,
         GAUCHE_INT,
         MILIEU,
         DROITE_INT,
         DROITE_EXT;
     }

    public Question(int nbChoix, Position position) {
        this.nbChoix = nbChoix;
        this.position = position;
    }

    public int getNbChoix() {
        return nbChoix;
    }

    public Position getPosition() {
        return position;
    }
     
}
