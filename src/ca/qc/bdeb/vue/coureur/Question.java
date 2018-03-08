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
public class Question extends JComponent{
    private int nbChoix;
    Position position;
    private String question;
    private String rep1;
    private String rep2;
    private String rep3;
    private String rep4;
    private String rep5;
     
     public enum Position{
         GAUCHE_EXT,
         GAUCHE_INT,
         MILIEU,
         DROITE_INT,
         DROITE_EXT;
     }

    public Question(int nbChoix, Position position, String rep1, String rep2, String rep3, String question) {
        this.nbChoix = nbChoix;
        this.position = position;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.question = question;
        setSize(800, 150);
    }

    public Question(int nbChoix, Position position, String rep1, String rep2, String rep3, String rep4, String question) {
        this.nbChoix = nbChoix;
        this.position = position;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.rep4 = rep4;
        this.question = question;
        setSize(800, 150);
    }

    public Question(int nbChoix, Position position, String rep1, String rep2, String rep3, String rep4, String rep5, String question) {
        this.nbChoix = nbChoix;
        this.position = position;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.rep4 = rep4;
        this.rep5 = rep5;
        this.question = question;
        setSize(800, 150);
    }

  

    public int getNbChoix() {
        return nbChoix;
    }

    public Position getPosition() {
        return position;
    }

    public String getRep1() {
        return rep1;
    }

    public String getRep2() {
        return rep2;
    }

    public String getRep3() {
        return rep3;
    }

    public String getRep4() {
        return rep4;
    }

    public String getRep5() {
        return rep5;
    }
    
     public void paintComponents (Graphics g){
         g.setColor(Color.yellow);
         g.fillRect(0, 0, 799, 25);
         g.setColor(Color.BLACK);
        switch (nbChoix){
            case 3 :
                g.fillRect(0, 0, 10, 25);
                g.fillRect(267, 0, 10, 25);
                g.fillRect(533, 0, 10, 25);
                break;
        }
    }
}
