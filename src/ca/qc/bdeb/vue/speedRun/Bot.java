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
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Bot extends JComponent {

    private int pointsVie = 3;

    private int largeur = 100, hauteur = 120;

    private Image image;

    public Bot(Controleur controleur) {
        this.setSize(largeur, hauteur);

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

    }

    public void enleverPointDeVie() {
        pointsVie--;
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
        g.drawImage(image, 0, 0, this);
    }

}
