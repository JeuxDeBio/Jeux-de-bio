/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.vue.professeur.MondeInscriptionProfesseurs1;
import ca.qc.bdeb.vue.etudiant.MondeInscriptionEtudiants1;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.etudiant.MondeInscriptionEtudiants2;
import ca.qc.bdeb.modele.TypeUtilisateur;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class FenetreInscription extends JFrame {

    private MondeInscriptionEtudiants1 mondeEtudiant1;
    private MondeInscriptionEtudiants2 mondeEtudiant2;
    private MondeInscriptionProfesseurs1 mondeProfesseur;

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
                mondeEtudiant1 = new MondeInscriptionEtudiants1(this, controleur);
                this.add(mondeEtudiant1);
                break;
            case PROFESSEUR:
                mondeProfesseur = new MondeInscriptionProfesseurs1(this, controleur);
                this.add(mondeProfesseur);
        }

        this.add(lblErrorLog, BorderLayout.SOUTH);
    }

    public void setErrorLog(String text) {
        this.lblErrorLog.setText(text);
    }

    public void etape2Etudiants(String da, String motDePasse) {
        mondeEtudiant2 = new MondeInscriptionEtudiants2(this, controleur, da, motDePasse);
        this.remove(mondeEtudiant1);
        this.add(mondeEtudiant2);
        pack();
        invalidate();
        repaint();
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreInscription();
    }

}
