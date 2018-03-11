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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *  *
 * @author 1649904
 */
public class FenetreJeu extends JFrame {

    private Controleur controleur;
    private Modele modele;

    private JLabel lblTimerDragDrop = new JLabel("00:00", JLabel.CENTER);
    private FenetreSelection fenetreSelection;
    private FenetrePrincipale fenetrePrincipale;

    private Jeu jeu;

    private int niveauID;

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuJeu = new JMenu("Jeu");
    private JMenuItem mnuValiderDragDrop = new JMenuItem("Valider vos reponses!");

    public FenetreJeu(Jeu jeu, FenetreSelection fenetreSelection, FenetrePrincipale fenetrePrincipale, Controleur controleur, Modele modele, int niveauID) {
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
                Monde_Drag_Drop mondeDragDrop = new Monde_Drag_Drop(lblTimerDragDrop, this, controleur, modele);
                this.add(mondeDragDrop);
                this.add(lblTimerDragDrop, BorderLayout.NORTH);

                mnuJeu.add(mnuValiderDragDrop);
                mnuBar.add(mnuJeu);
                this.setJMenuBar(mnuBar);

                mnuValiderDragDrop.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        mondeDragDrop.validationPoints();
                    }
                });

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
