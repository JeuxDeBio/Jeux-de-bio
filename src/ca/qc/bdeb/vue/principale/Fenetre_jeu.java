/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.coureur.Monde_Coureur;
import ca.qc.bdeb.vue.dragDrop.Monde_Drag_Drop;
import ca.qc.bdeb.vue.shooter.Monde_Shooter;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *  *
 * @author 1649904
 */
public class Fenetre_jeu extends JFrame {

    Controleur controleur;
    Modele modele;

    JLabel lblTimer = new JLabel("00:00", JLabel.CENTER);
    Fenetre_selection fenetreSelection;
    Fenetre_principale fenetrePrincipale;

    Jeu jeu;

    int niveauID;

    public Fenetre_jeu(Jeu jeu, Fenetre_selection fenetreSelection, Fenetre_principale fenetrePrincipale, Controleur controleur, Modele modele, int niveauID) {
        this.jeu = jeu;
        this.fenetreSelection = fenetreSelection;
        this.fenetrePrincipale = fenetrePrincipale;
        this.modele = modele;
        this.controleur = controleur;
        this.niveauID = niveauID;

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
                Monde_Drag_Drop mondeDragDrop = new Monde_Drag_Drop(lblTimer, this, controleur, modele);
                this.add(mondeDragDrop);
                this.add(lblTimer, BorderLayout.NORTH);
                break;
            case SHOOTER:
                Monde_Shooter mondeShooter = new Monde_Shooter(this);
                this.add(mondeShooter);
                break;
            case COUREUR:
                Monde_Coureur mondeCoureur = new Monde_Coureur(this);
                this.add(mondeCoureur);
                break;
            case SPEED_RUN:
        }

    }

    private void creerEvenements() {
        
    }

    public void fermerFenetre() {
        fenetreSelection.fermerFenetreJeu();
        fenetrePrincipale.finJeu();
    }

    public int getNiveauID() {
        return niveauID;
    }
}
