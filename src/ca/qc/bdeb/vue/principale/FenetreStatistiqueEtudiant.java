/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import javax.swing.JFrame;

/**
 *
 * @author Batikan
 */
public class FenetreStatistiqueEtudiant extends JFrame {

    private Controleur controleur;

    private MondeStatistiquesEtudiant mondeEtudiant;
    private MondeStatistiquesJeu mondeJeu;

    private FenetrePrincipale fenetre;

    public FenetreStatistiqueEtudiant(Controleur controleur, FenetrePrincipale fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.mondeEtudiant = new MondeStatistiquesEtudiant(controleur, this);
        this.add(mondeEtudiant);

        this.setTitle("Statistiques de " + controleur.getEtudiant().getDa());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void mettreJeu(Jeu jeu) {
        this.remove(mondeEtudiant);
        this.mondeJeu = new MondeStatistiquesJeu(controleur, this, jeu);
        this.add(mondeJeu);
        this.validate();
        this.repaint();
    }

    public void fermerJeu() {
        this.remove(mondeJeu);
        this.add(mondeEtudiant);
        this.validate();
        this.repaint();
    }

}
