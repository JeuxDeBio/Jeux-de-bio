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

/**
 *
 * @author Batikan
 */
public class MondeStatistiquesEtudiant extends JComponent {

    private Controleur controleur;

    private FenetreStatistiqueEtudiant fenetre;

    private final int largeur = 350, hauteur = 250;

    private Image image;

    private Bouton boutonDragDrop = new Bouton();
    private Bouton boutonShooter = new Bouton();
    private Bouton boutonCoureur = new Bouton();
    private Bouton boutonSpeedRun = new Bouton();

    public MondeStatistiquesEtudiant(Controleur controleur, FenetreStatistiqueEtudiant fenetre) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreStatistiquesEtudiant());

        boutonDragDrop.setSize(160, 110);
        boutonDragDrop.setLocation(10, 10);
        this.add(boutonDragDrop);

        boutonShooter.setSize(160, 110);
        boutonShooter.setLocation(180, 10);
        this.add(boutonShooter);

        boutonCoureur.setSize(160, 110);
        boutonCoureur.setLocation(10, 130);
        this.add(boutonCoureur);

        boutonSpeedRun.setSize(160, 110);
        boutonSpeedRun.setLocation(180, 130);
        this.add(boutonSpeedRun);

        IndicateurCouleur indicateurDragDrop = new IndicateurCouleur(getCouleur(Jeu.DRAG_DROP));
        indicateurDragDrop.setLocation(boutonDragDrop.getX() + (boutonDragDrop.getWidth() - indicateurDragDrop.getWidth()) / 2, boutonDragDrop.getY() + boutonDragDrop.getHeight() - indicateurDragDrop.getHeight() - 10);
        this.add(indicateurDragDrop);

        IndicateurCouleur indicateurShooter = new IndicateurCouleur(getCouleur(Jeu.SHOOTER));
        indicateurShooter.setLocation(boutonShooter.getX() + (boutonShooter.getWidth() - indicateurShooter.getWidth()) / 2, boutonShooter.getY() + boutonShooter.getHeight() - indicateurShooter.getHeight() - 10);
        this.add(indicateurShooter);

        IndicateurCouleur indicateurCoureur = new IndicateurCouleur(getCouleur(Jeu.COUREUR));
        indicateurCoureur.setLocation(boutonCoureur.getX() + (boutonCoureur.getWidth() - indicateurCoureur.getWidth()) / 2, boutonCoureur.getY() + boutonCoureur.getHeight() - indicateurCoureur.getHeight() - 10);
        this.add(indicateurCoureur);

        IndicateurCouleur indicateurSpeedRun = new IndicateurCouleur(getCouleur(Jeu.SPEED_RUN));
        indicateurSpeedRun.setLocation(boutonSpeedRun.getX() + (boutonSpeedRun.getWidth() - indicateurSpeedRun.getWidth()) / 2, boutonSpeedRun.getY() + boutonSpeedRun.getHeight() - indicateurSpeedRun.getHeight() - 10);
        this.add(indicateurSpeedRun);

    }

    private void creerEvenements() {
        boutonDragDrop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                fenetre.mettreJeu(Jeu.DRAG_DROP);
            }
        });

        boutonShooter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                fenetre.mettreJeu(Jeu.SHOOTER);
            }
        });

        boutonCoureur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                fenetre.mettreJeu(Jeu.COUREUR);
            }
        });

        boutonSpeedRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                fenetre.mettreJeu(Jeu.SPEED_RUN);
            }
        });
    }

    private Color getCouleur(Jeu jeu) {
        Color couleur = Color.WHITE;
        int index = 0;
        switch (jeu) {
            case DRAG_DROP:
                index = 0;
                break;
            case SHOOTER:
                index = 1;
                break;
            case COUREUR:
                index = 2;
                break;
            case SPEED_RUN:
                index = 3;
        }

        int nombreRouge = 0, nombreJaune = 0, nombreVert = 0;

        for (int i = 0; i < controleur.getEtudiant().getScores()[index].length; i++) {
            int note = controleur.getEtudiant().getScores()[index][i];
            if (note >= 70) {
                nombreVert++;
            } else if (note < 70 && note >= 60) {
                nombreJaune++;
            } else {
                nombreRouge++;
            }
        }

        if (nombreRouge >= nombreJaune && nombreRouge >= nombreVert) {
            couleur = Color.RED;
        } else if (nombreJaune >= nombreRouge && nombreJaune >= nombreVert) {
            couleur = Color.YELLOW;
        } else if (nombreVert >= nombreJaune && nombreVert >= nombreRouge) {
            couleur = Color.GREEN;
        }

        return couleur;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
    }

}
