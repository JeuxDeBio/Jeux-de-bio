/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.coureur.MondeCoureur;
import ca.qc.bdeb.vue.dragDrop.MondeDragDrop;
import ca.qc.bdeb.vue.shooter.MondeShooter;
import ca.qc.bdeb.vue.speedRun.MondeSpeedRun;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 *  *
 * @author 1649904
 */
public class FenetreJeu extends JFrame {

    private Controleur controleur;
    private Modele modele;

    private JLabel lblTimer = new JLabel("", JLabel.CENTER);
    private JLabel lblQuestion = new JLabel("", JLabel.CENTER);

    private JTextField txtReponse = new JTextField("");

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

        this.pack();
        this.setVisible(true);
    }

    private void creerInterface() {
        this.setTitle("Nom du jeu");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        switch (jeu) {
            case DRAG_DROP:
                MondeDragDrop mondeDragDrop = new MondeDragDrop(lblTimer, this, controleur);
                this.add(mondeDragDrop);
                this.add(lblTimer, BorderLayout.NORTH);

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
                MondeShooter mondeShooter = new MondeShooter(this);
                this.add(mondeShooter);
                break;
            case COUREUR:
                MondeCoureur mondeCoureur = new MondeCoureur(lblQuestion, this, controleur, modele);
                this.add(mondeCoureur);
                break;
            case SPEED_RUN:
                MondeSpeedRun mondeSpeedRun = new MondeSpeedRun(lblQuestion, txtReponse, lblTimer, this, controleur, modele);
                this.add(mondeSpeedRun);
        }

    }

    public void fermerFenetre() {
        fenetreSelection.fermerFenetreJeu();
        fenetrePrincipale.finJeu();
    }

    public int getNiveauID() {
        return niveauID;
    }
}
