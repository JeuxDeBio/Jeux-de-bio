/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author 1649904
 */
public class FenetreInscription extends JFrame {

    private MondeInscription monde;
    private FenetrePrincipale fenetre;
    private Controleur controleur;

    public FenetreInscription(FenetrePrincipale fenetre, Controleur controleur) {
        this.fenetre = fenetre;
        this.controleur = controleur;
        this.monde = new MondeInscription(this, controleur);

        creerInterface();

        this.pack();
        this.setVisible(true);
    }

    private void creerInterface() {
        this.setResizable(false);
        this.add(monde);
    }
    
    public void fermerFenetre(){
       fenetre.fermerFenetreInscription();
    }

}
