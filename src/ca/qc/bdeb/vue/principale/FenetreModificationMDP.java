/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class FenetreModificationMDP extends JFrame {

    private Controleur controleur;

    private FenetrePrincipale fenetre;

    private MondeChangementMDP monde;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreModificationMDP(Controleur controleur, FenetrePrincipale fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();

        this.pack();
        this.setVisible(true);
    }

    private void creerInterface() {
        if (controleur.logInEtudiant()) {
            monde = new MondeChangementMDP(controleur, this, controleur.getEtudiant());
        } else if (controleur.logInProfesseur()) {
            monde = new MondeChangementMDP(controleur, this, controleur.getProfesseur());
        }
        this.setTitle("Modification de mot de passe");
        this.setResizable(false);
        this.add(monde);

        this.add(lblErrorLog, BorderLayout.SOUTH);
    }

    public void errorLogSetText(String log) {
        lblErrorLog.setText(log);
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreModificationMDP();
    }

}
