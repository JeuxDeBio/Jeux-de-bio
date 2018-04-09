/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Groupe;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class MondeClasses extends JComponent {

    private Controleur controleur;
    private FenetreClasses fenetre;
    private Groupe groupe;

    private Image image;

    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
    private ArrayList<BoutonSelecteur> listeBoutons = new ArrayList<>();

    private final int largeur = 300;
    private int hauteur = 50;

    public MondeClasses(Controleur controleur, FenetreClasses fenetre, Groupe groupe) {
        this.hauteur += (30 * groupe.getListeEtudiants().size());
        this.setLayout(null);
        this.setPreferredSize(new Dimension(largeur, hauteur));

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        this.listeEtudiants = groupe.getListeEtudiants();

        creerInterface();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreClasses());

        for (int i = 0; i < listeEtudiants.size(); i++) {
            JLabel lblEtudiant = new JLabel(listeEtudiants.get(i).getDa() + " | " + listeEtudiants.get(i).getNom());
            lblEtudiant.setSize(270, 30);
            lblEtudiant.setLocation(0, 50 + (lblEtudiant.getHeight() * i));
            this.add(lblEtudiant);

            BoutonSelecteur boutonSelecteur = new BoutonSelecteur();
            boutonSelecteur.setLocation(largeur - 10, lblEtudiant.getX() + 10);
            this.add(boutonSelecteur);
            listeBoutons.add(boutonSelecteur);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
