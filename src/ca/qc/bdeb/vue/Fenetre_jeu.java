/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.modele.Jeu;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ljkeh.vldfhv
 * @author 1649904
 */
public class Fenetre_jeu extends JFrame {

    JLabel lblTimer = new JLabel("00:00", JLabel.CENTER);
    Fenetre_principale fenetre;

    Jeu jeu;
    Monde_Drag_Drop monde_drag;
    Monde_Shooter monde_shooter;

    public Fenetre_jeu(Jeu jeu, Fenetre_principale fenetre) {
        this.jeu = jeu;
        this.fenetre = fenetre;
        creerInterface();
        creerEvenements();
        this.pack();
        this.setVisible(true);
    }

    private void creerInterface() {
        this.setTitle("Nom du jeu");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        switch (jeu) {
            case DRAG_DROP:
                this.add(monde_drag);
                this.add(lblTimer, BorderLayout.NORTH);
                break;
            case SHOOTER:
                monde_shooter = new Monde_Shooter(this);
                this.add(monde_shooter);
                break;
            case COUREUR:
                break;
            case SPEED_RUN:
        }

    }

    private void creerEvenements() {

    }

    public void fermerFenerte() {
        fenetre.fermerFenetreJeu();
    }

}
