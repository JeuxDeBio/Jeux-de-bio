/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Professeur;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPasswordField;

/**
 *
 * @author 1649904
 */
public class MondeModificationIcone extends JComponent {

    private Controleur controleur;

    private Image image;

    private FenetreModificationIcone fenetre;

    private Bouton boutonSauvegarder = new Bouton();
    private Icone iconePreview = new Icone("");
    private ArrayList<Icone> listeIcones = new ArrayList<>();

    public MondeModificationIcone(Controleur controleur, FenetreModificationIcone fenetre, Etudiant etudiant) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setPreferredSize(new Dimension(350, 241));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    public MondeModificationIcone(Controleur controleur, FenetreModificationIcone fenetre, Professeur professeur) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setPreferredSize(new Dimension(300, 125));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreModificationIcone());

        boutonSauvegarder.setSize(125, 27);
        boutonSauvegarder.setLocation(12, 86);
        this.add(boutonSauvegarder);

        listeIcones = controleur.getListeIcones();

        iconePreview = listeIcones.get(0);
    }

    private void creerEvenements() {
        boutonSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.

            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
    }
}
