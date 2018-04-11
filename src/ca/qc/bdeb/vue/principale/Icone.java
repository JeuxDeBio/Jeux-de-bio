/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Icone extends JComponent {

    private Controleur controleur;
    private Image image;

    private final int largeur = 82, hauteur = 99;

    public Icone(String location) {
        image = Toolkit.getDefaultToolkit().getImage(location);
        this.setSize(largeur, hauteur);
    }

    public Icone(Controleur controleur) {
        this.controleur = controleur;
        if (controleur.logInEtudiant()) {
            image = Toolkit.getDefaultToolkit().getImage(controleur.getEtudiant().getLocationIcone());
        } else if (controleur.logInProfesseur()) {
            image = Toolkit.getDefaultToolkit().getImage(controleur.getProfesseur().getLocationIcone());
        }
        this.setSize(largeur, hauteur);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
