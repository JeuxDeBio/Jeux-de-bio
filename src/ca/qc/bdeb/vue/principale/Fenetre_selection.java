/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
import javax.swing.JFrame;

/**
 *
 * @author 1649904
 */
public class Fenetre_selection extends JFrame {

    private Modele modele;

    private Controleur controleur;

    private Fenetre_principale fenetrePrincipale;

    private Fenetre_jeu fenetreJeu;

    private Monde_selection monde;

    private Jeu jeu;

    public Fenetre_selection(Modele modele, Controleur controleur, Fenetre_principale fenetrePrincipale, Jeu jeu, Fenetre_jeu fenetreJeu) {
        this.modele = modele;
        this.controleur = controleur;
        this.fenetrePrincipale = fenetrePrincipale;
        this.jeu = jeu;
        this.fenetreJeu = fenetreJeu;

        creerInterface();

        this.pack();
        this.setVisible(true);

    }

    private void creerInterface() {
        this.monde = new Monde_selection(modele, controleur, this, jeu);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Sélection de niveaux");
        this.add(monde);
    }

    public void fermerFenetreSelection() {
        fenetrePrincipale.fermerFenetreSelection();
    }

    public void ouvrirFenetreJeu(int i) {
        this.fenetreJeu = new Fenetre_jeu(jeu, this, fenetrePrincipale, controleur, modele, i);
    }

    public void fermerFenetreJeu() {
        this.fenetreJeu.dispose();
    }

    public void finJeu() {
        fenetrePrincipale.finJeu();
    }
}
