/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import javax.swing.JFrame;

/**
 *
 * @author 1649904
 */
public class FenetreStatistiquesGroupe extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetre;

    private MondeStatistiquesGroupe monde;

    private Groupe groupe;

    public FenetreStatistiquesGroupe(Controleur controleur, FenetrePrincipale fenetre, Groupe groupe) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        monde = new MondeStatistiquesGroupe(controleur, this, groupe);
        this.add(monde);

        this.pack();
        this.setTitle("Statistiques du " + groupe.getCode());
        this.setResizable(false);
        this.setVisible(true);
    }

}
