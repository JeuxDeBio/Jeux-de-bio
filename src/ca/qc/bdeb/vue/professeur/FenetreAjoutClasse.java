/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class FenetreAjoutClasse extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetre;

    private MondeAjoutClasse monde;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreAjoutClasse(FenetrePrincipale fenetre, Controleur controleur) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.monde = new MondeAjoutClasse(controleur, this);
        this.add(monde);
        this.add(lblErrorLog, BorderLayout.SOUTH);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void setErrorLog(String errorLog) {
        lblErrorLog.setText(errorLog);
    }

    public void fermerFenetre(){
        fenetre.fermerFenetreAjoutClasses();
    }
}
