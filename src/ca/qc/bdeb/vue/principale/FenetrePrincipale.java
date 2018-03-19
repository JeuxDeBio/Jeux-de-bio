/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 *
 * @author 1649904
 */
public class FenetrePrincipale extends JFrame implements Observer {

    Controleur controleur;
    Modele modele;

    MondePrincipale monde_principale;
    MondePrincipaleLogIn monde_principale_logIn;

    FenetreSelection fenetreSelection;
    FenetreJeu fenetreJeu;

    private boolean logIn = false;

    public FenetrePrincipale(Controleur controleur, Observable observable) {
        modele = (Modele) observable;
        modele.addObserver(this);
        this.controleur = controleur;

        creerInterface();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
                fermerApp();
            }

        });

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (modele.logIn()) {
            logIn();
        }
    }

    private void creerInterface() {
        this.monde_principale = new MondePrincipale(modele, controleur, this);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Jeux de bio!");
        this.setResizable(false);
        this.add(monde_principale);
    }

    public void ouvrirFenetreSelectionJeu(Jeu jeu) {
        this.fenetreSelection = new FenetreSelection(modele, controleur, this, jeu, fenetreJeu);
        fenetreSelection.setLocation(this.getX() + (this.getWidth() - fenetreSelection.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreSelection.getHeight()) / 2);
    }

    public void fermerFenetreSelection() {
        this.fenetreSelection.dispose();
    }

    public void logIn() {
        this.monde_principale.reset();
        this.remove(monde_principale);
        this.monde_principale_logIn = new MondePrincipaleLogIn(modele, this);
        this.add(monde_principale_logIn);
        this.validate();
        this.repaint();
        this.logIn = true;
    }

    public void logOut() {
        this.remove(monde_principale_logIn);
        this.add(monde_principale);
        this.validate();
        this.repaint();
        this.logIn = false;
    }

    public void finJeu() {
        if (logIn) {
            monde_principale_logIn.finJeu();
        } else {
            monde_principale.finJeu();
        }
    }

    private void fermerApp() {
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter?", "Fermer l'application?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
