/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.tutorial;

import ca.qc.bdeb.vue.speedRun.*;
import ca.qc.bdeb.controleur.Controleur;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Joueur extends JComponent {

    private Controleur controleur;
    private int pointsVie = 3;
    private int score = 0;

    private Image image;

    private int largeur = 82, hauteur = 99;

    private ArrayList<Coeur> listeCoeurs = new ArrayList<>();

    public Joueur(Controleur controleur) {
        this.controleur = controleur;
        int coeurWidth = updateCoeurs();
        this.setSize(largeur + 5 + coeurWidth, hauteur);

        String location = "";
        if (controleur.logInEtudiant()) {
            location = controleur.getEtudiant().getLocationIcone();
        } else {
            location = controleur.getIconeVierge().getLocationIcone();
        }

        image = Toolkit.getDefaultToolkit().getImage(location);
    }

    private int updateCoeurs() {
        int coeurWidth = 0;

        for (int i = 0; i < pointsVie; i++) {
            Coeur coeur = new Coeur(controleur);
            coeur.setLocation(largeur + 5, (coeur.getHeight() + 1) * i);
            this.add(coeur);
            listeCoeurs.add(coeur);
            coeurWidth = coeur.getWidth();
        }
        return coeurWidth;
    }

    public void botElimine() {
        score++;
    }

    public void mauvaiseReponse() {
        pointsVie--;
        listeCoeurs.get(listeCoeurs.size() - 1).detruire();
        listeCoeurs.remove(listeCoeurs.get(listeCoeurs.size() - 1));
    }

    public int getScore() {
        return score;
    }

    public int getPointVie() {
        return pointsVie;
    }

    public boolean joueurDetruit() {
        return pointsVie == 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }
}
