/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.vue.professeur.MondeInscriptionProfesseurs;
import ca.qc.bdeb.vue.etudiant.MondeInscriptionEtudiants;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.professeur.TypeUtilisateur;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class FenetreInscription extends JFrame {

    private MondeInscriptionEtudiants mondeEtudiant;
    private MondeInscriptionProfesseurs mondeProfesseur;

    private FenetrePrincipale fenetre;
    private Controleur controleur;
    private TypeUtilisateur type;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreInscription(FenetrePrincipale fenetre, Controleur controleur, TypeUtilisateur type) {
        this.fenetre = fenetre;
        this.controleur = controleur;
        this.type = type;

        creerInterface();

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    private void creerInterface() {
        switch (type) {
            case ETUDIANT:
                mondeEtudiant = new MondeInscriptionEtudiants(this, controleur);
                this.add(mondeEtudiant);
                break;
            case PROFESSEUR:
                mondeProfesseur = new MondeInscriptionProfesseurs(this, controleur);
                this.add(mondeProfesseur);
        }

        this.add(lblErrorLog, BorderLayout.SOUTH);
    }

    public void setErrorLog(String text) {
        this.lblErrorLog.setText(text);
    }

}
