/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
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
 * @author 1649904
 */
public class MondeEtudiant extends JComponent {

    private Modele modele;

    private Image image;

    private FenetrePrincipale fenetre;

    private JLabel lblUtilisateurNom = new JLabel();
    private JLabel lblUtilisateurPrenom = new JLabel();
    private JLabel lblUtilisateurDA = new JLabel();

    private Bouton boutonDragDrop = new Bouton();
    private Bouton boutonShooter = new Bouton();
    private Bouton boutonCoureur = new Bouton();
    private Bouton boutonSpeedRun = new Bouton();
    private Bouton boutonLogOut = new Bouton();

    private boolean enJeu = false;

    public MondeEtudiant(Modele modele, FenetrePrincipale fenetre) {
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);

        this.modele = modele;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();

    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(modele.getLocationFenetrePrincipaleLogIn());

        lblUtilisateurNom.setLocation(310, 230);
        lblUtilisateurNom.setSize(190, 20);
        this.add(lblUtilisateurNom);

        lblUtilisateurPrenom.setLocation(310, 255);
        lblUtilisateurPrenom.setSize(190, 20);
        this.add(lblUtilisateurPrenom);

        lblUtilisateurDA.setLocation(310, 280);
        lblUtilisateurDA.setSize(190, 20);
        this.add(lblUtilisateurDA);

        boutonDragDrop.setLocation(26, 194);
        boutonDragDrop.setSize(147, 74);
        this.add(boutonDragDrop);

        boutonShooter.setLocation(26, 290);
        boutonShooter.setSize(147, 74);
        this.add(boutonShooter);

        boutonCoureur.setLocation(26, 385);
        boutonCoureur.setSize(147, 74);
        this.add(boutonCoureur);

        boutonSpeedRun.setLocation(26, 479);
        boutonSpeedRun.setSize(147, 74);
        this.add(boutonSpeedRun);

        boutonLogOut.setLocation(731, 19);
        boutonLogOut.setSize(50, 50);
        this.add(boutonLogOut);
    }

    private void creerEvenements() {

        boutonDragDrop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.DRAG_DROP);
                    enJeu = true;
                }

            }
        });

        boutonShooter.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.SHOOTER);
                    enJeu = true;
                }

            }
        });

        boutonCoureur.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.COUREUR);
                    enJeu = true;
                }

            }
        });

        boutonSpeedRun.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.SPEED_RUN);
                    enJeu = true;
                }

            }
        });

        boutonLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                modele.logOut();
                fenetre.logOut();
            }

        });
    }

    protected void finJeu() {
        this.enJeu = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }
}
