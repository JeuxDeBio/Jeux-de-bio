/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.etudiant;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.principale.Bouton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class MondeStatistiquesJeu extends JComponent {

    private Controleur controleur;

    private FenetreStatistiqueEtudiant fenetre;

    private final int largeur = 350, hauteur = 250;

    private Image image;

    private Jeu jeu;

    private int indexJeu = 0;
    private String nomJeu = "";

    private Bouton boutonQuitter = new Bouton();

    private JLabel lblNomJeu = new JLabel("", JLabel.CENTER);

    public MondeStatistiquesJeu(Controleur controleur, FenetreStatistiqueEtudiant fenetre, Jeu jeu) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.jeu = jeu;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreStatistiquesJeu());

        boutonQuitter.setSize(20, 20);
        boutonQuitter.setLocation(319, 10);
        this.add(boutonQuitter);

        switch (jeu) {
            case DRAG_DROP:
                indexJeu = 0;
                nomJeu = "Drag & Drop";
                break;
            case SHOOTER:
                indexJeu = 1;
                nomJeu = "Shooter";
                break;
            case COUREUR:
                indexJeu = 2;
                nomJeu = "Coureur";
                break;
            case SPEED_RUN:
                indexJeu = 3;
                nomJeu = "Speed Run";
        }

        lblNomJeu.setText(nomJeu);
        lblNomJeu.setSize(330, 20);
        lblNomJeu.setLocation(10, 10);
        this.add(lblNomJeu);

        for (int i = 0; i < controleur.getEtudiant().getScores()[indexJeu].length; i++) {
            JLabel lblNiveau = new JLabel("Niveau " + (i + 1) + ": " + controleur.getEtudiant().getScores()[indexJeu][i] + " points", JLabel.CENTER);
            lblNiveau.setSize(175, 20);
            if (i % 2 == 0) {
                lblNiveau.setLocation(0, (lblNiveau.getHeight() + 2) * i + 37);
            } else {
                lblNiveau.setLocation(175, (lblNiveau.getHeight() + 2) * (i - 1) + 37);
            }
            this.add(lblNiveau);
        }
    }

    private void creerEvenements() {
        boutonQuitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                fenetre.fermerJeu();
            }

        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
        grphcs.setColor(new Color(240, 240, 240));
        grphcs.fillRect(0, 69, 350, 44);
        grphcs.fillRect(0, 157, 350, 44);
    }

}
