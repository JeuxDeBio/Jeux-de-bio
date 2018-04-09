/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.vue.dragDrop.MondeCreationDragDrop;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.dragDrop.FenetreCreationDragDrop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Niopo
 */
public class FenetreCreation extends JFrame {
    
    
    private FenetreCreationDragDrop fenetreCreationDragDrop;
            
    private Controleur controleur;
    private Modele modele;

    private FenetrePrincipale fenetrePrincipale;

    private Jeu jeu;

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuJeu = new JMenu("Jeu");
    private JMenuItem mnuCreer = new JMenuItem("Étape suivante");

    public FenetreCreation(Controleur controleur, Modele modele, FenetrePrincipale fenetrePrincipale, Jeu jeu, FenetreCreationDragDrop fenetreCreationDragDrop) {
        this.controleur = controleur;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.jeu = jeu;
        this.fenetreCreationDragDrop = fenetreCreationDragDrop;

        creerInterface();

        this.pack();
        this.setVisible(true);
    }

    public void creerInterface() {

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        switch (jeu) {
            case DRAG_DROP:
                MondeCreationDragDrop monde = new MondeCreationDragDrop(controleur,this);
                this.add(monde);
                mnuJeu.add(mnuCreer);
                mnuBar.add(mnuJeu);
                this.setJMenuBar(mnuBar);

                mnuCreer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        monde.sauvegarderInfo();
                    }
                });
                break;
        }
    }
    
    public void ouvrirFenetre(String titre, String lien1, String lien2, String largeur, String hauteur){
        this.fenetreCreationDragDrop = new FenetreCreationDragDrop(fenetrePrincipale, titre, lien1, lien2, largeur, hauteur);
        fenetreCreationDragDrop.setLocation(fenetrePrincipale.getX() + (fenetrePrincipale.getWidth() - fenetreCreationDragDrop.getWidth()) / 2, fenetrePrincipale.getY());
    }

}
