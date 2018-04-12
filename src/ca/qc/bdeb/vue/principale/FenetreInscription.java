/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.vue.professeur.MondeInscriptionProfesseurs;
import ca.qc.bdeb.vue.etudiant.MondeInscriptionEtudiants;
import ca.qc.bdeb.controleur.Controleur;
import javax.swing.JFrame;

/**
 *
 * @author 1649904
 */
public class FenetreInscription extends JFrame {

    private MondeInscriptionEtudiants mondeEtudiant;
    private MondeInscriptionProfesseurs mondeProf;

    private FenetrePrincipale fenetre;
    private Controleur controleur;

    public FenetreInscription(FenetrePrincipale fenetre, Controleur controleur, String type) {
        this.fenetre = fenetre;
        this.controleur = controleur;

        if (type.equals("prof")) {
            this.mondeProf = new MondeInscriptionProfesseurs(this, controleur);
        } else if (type.equals("etudiant")) {
            this.mondeEtudiant = new MondeInscriptionEtudiants(this, controleur);
        }
        creerInterface(type);

        this.pack();
        this.setVisible(true);
    }

    private void creerInterface(String type) {
        this.setResizable(false);
        if (type.equals("prof")) {
            this.add(mondeProf);
        } else if (type.equals("etudiant")) {
            this.add(mondeEtudiant);
        }
    }

}
