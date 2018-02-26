/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
class Monde_selection extends JComponent {

    private Modele modele;

    private Controleur controleur;

    private Image image;

    private Fenetre_selection fenetre;

    private Jeu jeu;

    private Bouton niveau1 = new Bouton();
    private Bouton niveau2 = new Bouton();
    private Bouton niveau3 = new Bouton();
    private Bouton niveau4 = new Bouton();
    private Bouton niveau5 = new Bouton();
    private Bouton niveau6 = new Bouton();
    private Bouton niveau7 = new Bouton();
    private Bouton niveau8 = new Bouton();
    private Bouton niveau9 = new Bouton();
    private Bouton niveau10 = new Bouton();

    public Monde_selection(Modele modele, Controleur controleur, Fenetre_selection fenetre, Jeu jeu) {
        this.setPreferredSize(new Dimension(300, 600));
        this.setLayout(null);

        this.modele = modele;
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.jeu = jeu;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreSelection());

        niveau1.setSize(133, 85);
        niveau1.setLocation(10, 128);
        this.add(niveau1);

        JLabel nom1 = new JLabel();
        nom1.setText(controleur.getNomNiveau(jeu, 0));
       // nom1.setSize();

    }

    private void creerEvenements() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
