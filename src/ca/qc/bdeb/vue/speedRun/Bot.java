/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.speedRun;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Bot extends JComponent {

    private Controleur controleur;

    private int pointsVie = 3;

    private int largeur = 82, hauteur = 99;
    private int coeurWidth;

    private ArrayList<Coeur> listeCoeurs = new ArrayList<>();

    private Image image;

    public Bot(Controleur controleur) {
        this.controleur = controleur;
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationRobot1());
                break;
            case 1:
                image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationRobot2());
                break;
            case 2:
                image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationRobot3());
        }
        coeurWidth = updateCoeurs();
        this.setSize(largeur + 5 + coeurWidth, hauteur);
    }

    private int updateCoeurs() {
        int coeurWidth = 0;
        for (int i = 0; i < pointsVie; i++) {
            Coeur coeur = new Coeur(controleur);
            coeur.setLocation(0, (coeur.getHeight() + 1) * i);
            this.add(coeur);
            listeCoeurs.add(coeur);
            coeurWidth = coeur.getWidth();
        }
        return coeurWidth;
    }

    public void enleverPointDeVie() {
        pointsVie--;
        listeCoeurs.get(listeCoeurs.size() - 1).detruire();
        listeCoeurs.remove(listeCoeurs.get(listeCoeurs.size() - 1));
    }

    public int getPointsVie() {
        return pointsVie;
    }

    public boolean botDetruit() {
        return this.pointsVie == 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, coeurWidth + 5, 0, this);
    }

}
