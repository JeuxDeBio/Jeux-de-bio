/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
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

    Monde_principale monde_principale;
    MondePrincipaleLogIn mondePrincipaleLogIn;

    FenetreSelection fenetreSelection;
    FenetreJeu fenetreJeu;

    private boolean logIn = false;

    public FenetrePrincipale(Controleur controleur, Observable observable) {
        modele = (Modele) observable;
        modele.addObserver(this);
        this.controleur = controleur;

        creerInterface();

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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Jeux de bio!");
        this.add(monde_principale);
    }

    public void ouvrirFenetreSelectionJeu(Jeu jeu) {
        this.fenetreSelection = new FenetreSelection(modele, controleur, this, jeu, fenetreJeu);
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

}
