/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Joueur extends JComponent {

    private final int largeur = 82, hauteur = 99;
    private BoiteChoix choix;
    private boolean faitChoix = false;
    private int score = 0;
    
    private Image image;

    public Joueur(Controleur controleur) {
        setSize(largeur, hauteur);
        
        String location = "";
        if (controleur.logInEtudiant()) {
            location = controleur.getEtudiant().getLocationIcone();
        } else {
            location = controleur.getIconeVierge().getLocationIcone();
        }

        image = Toolkit.getDefaultToolkit().getImage(location);
    }

    public void setChoix(BoiteChoix choix) {
        this.choix = choix;
    }

    public void faitChoixTrue() {
        faitChoix = true;
    }

    public void faitChoixFalse() {
        this.faitChoix = false;
    }

    public boolean faitChoix() {
        return faitChoix;
    }

    public BoiteChoix getChoix() {
        return choix;
    }

    public void ajouterPoint() {
        score++;
    }

    public int getScore() {
        return score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
