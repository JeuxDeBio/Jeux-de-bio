/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class Bouton extends JComponent {

    public Bouton() {
    }

    public Bouton(int i, Controleur controleur, Jeu jeu) {
        JLabel lblTexteBouton = new JLabel(controleur.getNomNiveau(jeu, i), JLabel.CENTER);
        lblTexteBouton.setSize(113, 20);
        lblTexteBouton.setLocation(10, 32);
        this.add(lblTexteBouton);
    }

    public Bouton(String texte) {
        JLabel lblTexteBouton = new JLabel(texte, JLabel.CENTER);
        lblTexteBouton.setSize(258, 20);
        lblTexteBouton.setLocation(10, 25);
        this.add(lblTexteBouton);
    }

}
