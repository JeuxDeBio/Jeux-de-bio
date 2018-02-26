/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

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
public class Fenetre_principale extends JFrame implements Observer {

    Controleur controleur;
    Modele modele;

    Monde_principale monde_principale;
    Monde_principale_logIn monde_principale_logIn;

    Fenetre_selection fenetre_selection;

    public Fenetre_principale(Controleur controleur, Observable observable) {
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
        this.monde_principale = new Monde_principale(modele, controleur, this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Jeux de bio!");
        this.add(monde_principale);
    }

    public void ouvrirFenetreSelectionJeu(Jeu jeu) {
        this.fenetre_selection = new Fenetre_selection(modele, controleur, this, jeu);

    }

    public void fermerFenetreJeu() {
        this.fenetre_selection.dispose();
        this.monde_principale.finJeu();
    }

    public void logIn() {
        this.monde_principale.reset();
        this.remove(monde_principale);
        this.monde_principale_logIn = new Monde_principale_logIn(modele, this);
        this.add(monde_principale_logIn);
        this.validate();
        this.repaint();
    }

    public void logOut() {
        this.remove(monde_principale_logIn);
        this.add(monde_principale);
        this.validate();
        this.repaint();
    }
}
