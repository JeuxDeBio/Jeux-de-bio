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

    MondePrincipale mondePrincipale;
    Monde_principale_logIn monde_principale_logIn;

    FenetreSelection fenetreSelection;
    FenetreJeu fenetreJeu;

    public FenetrePrincipale(Controleur controleur, Observable observable) {
        modele = (Modele) observable;
        modele.addObserver(this);
        this.controleur = controleur;

        creerInterface();
        creerEvenements();

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
        this.mondePrincipale = new MondePrincipale(modele, controleur, this);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Jeux de bio!");
        this.add(mondePrincipale);
    }

    private void creerEvenements() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
                System.out.println("closing");
                controleur.saveQuit();
                System.exit(0);
            }

        });
    }

    public void ouvrirFenetreSelectionJeu(Jeu jeu) {
        this.fenetreSelection = new FenetreSelection(modele, controleur, this, jeu, fenetreJeu);
    }

    public void fermerFenetreSelection() {
        this.fenetreSelection.dispose();
    }

    public void logIn() {
        this.mondePrincipale.reset();
        this.mondePrincipale.logIn();
    }

    public void logOut() {
        this.mondePrincipale.logOut();
    }

    public void finJeu() {
        if (mondePrincipale.login()) {
            monde_principale_logIn.finJeu();
        } else {
            mondePrincipale.finJeu();
        }
    }

}
