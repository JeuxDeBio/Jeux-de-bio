/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import javax.swing.JFrame;

/**
 *
 * @author 1649904
 */
public class FenetreModificationIcone extends JFrame {

    private Controleur controleur;

    private FenetrePrincipale fenetre;

    private MondeModificationIcone monde;
   
    public FenetreModificationIcone(Controleur controleur, FenetrePrincipale fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    private void creerInterface() {
        this.setTitle("Modification de l'icône");
        this.setResizable(false);
        monde = new MondeModificationIcone(controleur, this);
        this.add(monde);
   }
    
    public void fermerFenetre(){
        fenetre.fermerFenetreModificationIcone();
    }
}
