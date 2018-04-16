/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.modele.TypeUtilisateur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author 1649904
 */
public class MondePrincipale extends JComponent {

    private Modele modele;

    private Controleur controleur;

    private Image image;

    private FenetrePrincipale fenetre;

    private JTextField txtDAEtudiant = new JTextField();
    private JTextField txtNUProfesseur = new JTextField();

    private JPasswordField pssEtudiant = new JPasswordField();
    private JPasswordField pssProfesseur = new JPasswordField();

    private Bouton boutonInscriptionEtudiant = new Bouton();
    private Bouton boutonInscriptionProfesseur = new Bouton();
    private Bouton boutonIdentificationEtudiant = new Bouton();
    private Bouton boutonIdentificationProfesseur = new Bouton();
    private Bouton boutonDragDrop = new Bouton();
    private Bouton boutonShooter = new Bouton();
    private Bouton boutonCoureur = new Bouton();
    private Bouton boutonSpeedRun = new Bouton();

    private boolean enJeu = false;

    private final int largeur = 800, hauteur = 600;

    public MondePrincipale(Modele modele, Controleur controleur, FenetrePrincipale fenetre) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.modele = modele;
        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetrePrincipale());

        txtDAEtudiant.setLocation(337, 245);
        txtDAEtudiant.setSize(223, 25);
        this.add(txtDAEtudiant);

        pssEtudiant.setLocation(337, 280);
        pssEtudiant.setSize(223, 25);
        this.add(pssEtudiant);

        txtNUProfesseur.setLocation(370, 426);
        txtNUProfesseur.setSize(200, 25);
        this.add(txtNUProfesseur);

        pssProfesseur.setLocation(370, 461);
        pssProfesseur.setSize(200, 25);
        this.add(pssProfesseur);

        boutonIdentificationEtudiant.setLocation(208, 309);
        boutonIdentificationEtudiant.setSize(187, 38);
        this.add(boutonIdentificationEtudiant);

        boutonInscriptionEtudiant.setLocation(414, 309);
        boutonInscriptionEtudiant.setSize(187, 38);
        this.add(boutonInscriptionEtudiant);

        boutonIdentificationProfesseur.setLocation(208, 490);
        boutonIdentificationProfesseur.setSize(187, 38);
        this.add(boutonIdentificationProfesseur);

        boutonInscriptionProfesseur.setLocation(414, 490);
        boutonInscriptionProfesseur.setSize(187, 38);
        this.add(boutonInscriptionProfesseur);

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
    }

    private void creerEvenements() {

        boutonIdentificationEtudiant.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    controleur.validerEtudiant(txtDAEtudiant.getText(), pssEtudiant.getPassword());
                }
            }

        });

        boutonInscriptionEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreInscription(TypeUtilisateur.ETUDIANT);
                }
            }

        });

        boutonIdentificationProfesseur.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    controleur.validerProfesseur(txtNUProfesseur.getText(), pssProfesseur.getPassword());
                }
            }

        });

        boutonInscriptionProfesseur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreInscription(TypeUtilisateur.PROFESSEUR);
                }
            }

        });

        boutonDragDrop.addMouseListener(new MouseAdapter() {
            String action = "jouer";

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.DRAG_DROP, action);
                    enJeu = true;
                }

            }
        });

        boutonShooter.addMouseListener(new MouseAdapter() {
            String action = "jouer";

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.SHOOTER, action);
                    enJeu = true;
                }

            }
        });

        boutonCoureur.addMouseListener(new MouseAdapter() {
            String action = "jouer";

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.COUREUR, action);
                    enJeu = true;
                }

            }
        });

        boutonSpeedRun.addMouseListener(new MouseAdapter() {
            String action = "jouer";

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.SPEED_RUN, action);
                    enJeu = true;
                }

            }
        });
    }

    protected void finJeu() {
        this.enJeu = false;
    }

    public void reset() {
        this.txtDAEtudiant.setText("");
        this.txtNUProfesseur.setText("");
        this.pssEtudiant.setText("");
        this.pssProfesseur.setText("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        g.drawLine(0, hauteur - 1, largeur, hauteur - 1);
    }

}
