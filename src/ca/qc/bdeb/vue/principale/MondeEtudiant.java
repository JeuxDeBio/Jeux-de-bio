/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
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

    private Controleur controleur;

    private Image image;

    private FenetrePrincipale fenetre;

    private JLabel lblNom = new JLabel();
    private JLabel lblProf = new JLabel();
    private JLabel lblDA = new JLabel();
    private JLabel lblGroupe = new JLabel();

    private Bouton boutonDragDrop = new Bouton();
    private Bouton boutonShooter = new Bouton();
    private Bouton boutonCoureur = new Bouton();
    private Bouton boutonSpeedRun = new Bouton();
    private Bouton boutonLogOut = new Bouton();

    private boolean enJeu = false;

    public MondeEtudiant(Controleur controleur, FenetrePrincipale fenetre) {
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();

    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreEtudiant());

        lblNom.setText(controleur.getEtudiant().getNom());
        lblNom.setLocation(262, 227);
        lblNom.setSize(190, 20);
        this.add(lblNom);
        
        lblDA.setText(controleur.getEtudiant().getDa());
        lblDA.setLocation(246, 254);
        lblDA.setSize(190, 20);
        this.add(lblDA);

        lblGroupe.setText(controleur.getEtudiant().getGroupe().getNom());
        lblGroupe.setLocation(293, 278);
        lblGroupe.setSize(190, 20);
        this.add(lblGroupe);
        
        lblProf.setText(controleur.getEtudiant().getProfesseur().getNom() + "");
        lblProf.setLocation(337, 303);
        lblProf.setSize(190, 20);
        this.add(lblProf);

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
                controleur.logOut();
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
