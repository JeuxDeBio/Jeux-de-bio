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

    private MondeModificationIcone mondeModification;
    private MondeVisualisationIcone mondeVisualisation;

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
        mondeModification = new MondeModificationIcone(controleur, this);
        this.add(mondeModification);
    }

    public void ajouterMondeVisualisation() {
        this.setTitle("Voir toutes les icônes");
        this.remove(mondeModification);
        mondeVisualisation = new MondeVisualisationIcone(controleur, this);
        this.add(mondeVisualisation);
        pack();
        invalidate();
        repaint();
    }

    public void ajouterMondeModification() {
        this.setTitle("Modification de l'icône");
        this.remove(mondeVisualisation);
        mondeModification = new MondeModificationIcone(controleur, this);
        this.add(mondeModification);
        pack();
        invalidate();
        repaint();
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreModificationIcone();
    }

    public void updateFenetre() {
        fenetre.updateFenetre();
    }
}
