/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author 1651114
 */
public class MondeProfesseur extends JComponent {

    private Controleur controleur;

    private Image image;

    private FenetrePrincipale fenetre;

    private Bouton boutonMdDragDrop = new Bouton();
    private Bouton boutonMdShooter = new Bouton();
    private Bouton boutonMdCoureur = new Bouton();
    private Bouton boutonMdSpeedRun = new Bouton();
    private Bouton boutonCrDragDrop = new Bouton();
    private Bouton boutonCrShooter = new Bouton();
    private Bouton boutonCrCoureur = new Bouton();
    private Bouton boutonCrSpeedRun = new Bouton();
    private Bouton boutonLogOut = new Bouton();

    private boolean enJeu = false;

    public MondeProfesseur(Controleur controleur, FenetrePrincipale fenetre) {
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreProfesseur());

        boutonMdDragDrop.setLocation(26, 194);
        boutonMdDragDrop.setSize(147, 74);
        this.add(boutonMdDragDrop);

        boutonMdShooter.setLocation(26, 290);
        boutonMdShooter.setSize(147, 74);
        this.add(boutonMdShooter);

        boutonMdCoureur.setLocation(26, 385);
        boutonMdCoureur.setSize(147, 74);
        this.add(boutonMdCoureur);

        boutonMdSpeedRun.setLocation(26, 479);
        boutonMdSpeedRun.setSize(147, 74);
        this.add(boutonMdSpeedRun);

        boutonCrDragDrop.setLocation(627, 194);
        boutonMdDragDrop.setSize(147, 74);
        this.add(boutonMdDragDrop);

        boutonCrShooter.setLocation(627, 290);
        boutonMdShooter.setSize(147, 74);
        this.add(boutonMdShooter);

        boutonCrCoureur.setLocation(627, 385);
        boutonMdCoureur.setSize(147, 74);
        this.add(boutonMdCoureur);

        boutonCrSpeedRun.setLocation(627, 479);
        boutonMdSpeedRun.setSize(147, 74);
        this.add(boutonMdSpeedRun);

        boutonLogOut.setLocation(731, 19);
        boutonLogOut.setSize(50, 50);
        this.add(boutonLogOut);
    }

    private void creerEvenements() {

        boutonMdDragDrop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonMdShooter.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonMdCoureur.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonMdSpeedRun.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonCrDragDrop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonCrShooter.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonCrCoureur.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonCrSpeedRun.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    //ouverture de la fenetre
                    enJeu = true;
                }

            }
        });

        boutonLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                controleur.logOutProfesseur();
                fenetre.logOutProfesseur();
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
