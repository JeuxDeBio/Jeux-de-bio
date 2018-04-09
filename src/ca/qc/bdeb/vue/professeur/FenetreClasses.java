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
public class FenetreClasses extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetre;
    private Groupe groupe;

    private MondeClasses monde;

    public FenetreClasses(Controleur controleur, FenetrePrincipale fenetre, Groupe groupe) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        monde = new MondeClasses(controleur, this, groupe);
        this.add(monde);

        this.setTitle("Liste d'étudiants du " + groupe.getCode());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

}
