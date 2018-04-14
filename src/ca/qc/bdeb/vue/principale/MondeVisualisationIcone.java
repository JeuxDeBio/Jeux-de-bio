/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author 1649904
 */
public class MondeVisualisationIcone extends JComponent {

    private Controleur controleur;
    private FenetreModificationIcone fenetre;

    private JMenuBar mnuBar = new JMenuBar();

    private JMenu mnuModifier = new JMenu("Modifier votre icône");

    private JMenuItem mnuItemModifier = new JMenuItem("Modifier votre icône!");

    private int facteur = 4;
    private int largeur = 0, hauteur = 0;

    MondeVisualisationIcone(Controleur controleur, FenetreModificationIcone fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        largeur = ((10 + 82) * facteur) + 10;
        hauteur = (10 + 99) * (((controleur.getListeIcones().size() - 1) / facteur) + 1) + 10;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(largeur, hauteur));

        mnuModifier.add(mnuItemModifier);
        mnuBar.add(mnuModifier);
        fenetre.setJMenuBar(mnuBar);

        for (int i = 0; i < controleur.getListeIcones().size(); i++) {
            Icone icone = controleur.getListeIcones().get(i);
            icone.setLocation((i % facteur) * (controleur.getListeIcones().get(i).getWidth() + 10) + 10, (i / facteur) * (controleur.getListeIcones().get(i).getHeight() + 10) + 10);
            this.add(icone);
        }
    }

    private void creerEvenements() {
        mnuItemModifier.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ajouterMondeModification();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, largeur, hauteur);
    }

}
