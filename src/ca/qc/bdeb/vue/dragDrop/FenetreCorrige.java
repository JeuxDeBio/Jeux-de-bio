/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class FenetreCorrige extends JFrame {

    private Controleur controleur;
    private FenetreJeu fenetre;

    private ImageCorrige image;

    public FenetreCorrige(Controleur controleur, FenetreJeu fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setTitle("Corrige - Niveau: " + this.controleur.getNomNiveau(Jeu.DRAG_DROP, fenetre.getNiveauID()));
        this.add(new JLabel("Fermer cette fenetre pour revenir au menu principale", JLabel.CENTER), BorderLayout.NORTH);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setState(Frame.NORMAL);

        this.image = new ImageCorrige(controleur, fenetre, this);
        this.add(image);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
                fenetre.fermerFenetre();
                FenetreCorrige.this.dispose();
            }

        });

        this.pack();
        this.setVisible(true);
    }

    public void fermerFenetre() {
        this.dispose();
    }

}
