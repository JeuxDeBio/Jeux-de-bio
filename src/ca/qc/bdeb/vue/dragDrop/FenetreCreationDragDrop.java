/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
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
public class FenetreCreationDragDrop extends JFrame {
    
    private Controleur controleur;
    private FenetrePrincipale fenetrePrincipale;
    
    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuJeu = new JMenu("Jeu");
    private JMenuItem mnuCreer = new JMenuItem("Créer le niveau");

    public FenetreCreationDragDrop(FenetrePrincipale fenetrePrincipale, String titre, String lien1, String lien2, String largeur, String hauteur, Controleur conroleur) {
        this.fenetrePrincipale = fenetrePrincipale;
        this.controleur = conroleur;
        creerInterface(titre, lien1, lien2, largeur, hauteur);

        this.pack();
        this.setVisible(true);
    }

    public void creerInterface(String titre, String lien1, String lien2, String largeur, String hauteur) {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        MondeCreationJeuDragDrop monde = new MondeCreationJeuDragDrop(titre, lien1, lien2, largeur, hauteur, this, controleur);
        this.add(monde);
        mnuJeu.add(mnuCreer);
        mnuBar.add(mnuJeu);
        this.setJMenuBar(mnuBar);

        mnuCreer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                monde.creerNiveau(titre, lien1, lien2, largeur, hauteur);
            }
        });
    }

    public void fermerFenetre() {
        this.dispose();
        
    }
}
