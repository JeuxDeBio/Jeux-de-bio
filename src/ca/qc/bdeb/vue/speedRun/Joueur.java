/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.speedRun;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Joueur extends JComponent {

    private Controleur controleur;
    private int pointVie = 3;
    private int score = 0;
    
    private int largeur = 100, hauteur = 120;

    public Joueur(Controleur controleur) {
        this.controleur = controleur;
        this.setSize(largeur, hauteur);
    }

    public void botElimine() {
        score++;
    }

    public void mauvaiseReponse() {
        pointVie--;
    }

    public int getScore() {
        return score;
    }

    public int getPointVie() {
        return pointVie;
    }

    public boolean joueurDetruit() {
        return pointVie == 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, largeur, hauteur);
    }
    
    
}
