/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.Bouton;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JLabel lblNom = new JLabel();
    private JLabel lblNomUtilisateur = new JLabel();
    private JLabel lblSession = new JLabel();

    private Bouton boutonMdDragDrop = new Bouton();
    private Bouton boutonMdShooter = new Bouton();
    private Bouton boutonMdCoureur = new Bouton();
    private Bouton boutonMdSpeedRun = new Bouton();
    private Bouton boutonCrDragDrop = new Bouton();
    private Bouton boutonCrShooter = new Bouton();
    private Bouton boutonCrCoureur = new Bouton();
    private Bouton boutonCrSpeedRun = new Bouton();
    private Bouton boutonLogOut = new Bouton();

    private JMenuBar mnuBar = new JMenuBar();

    private JMenu mnuProfil = new JMenu("Profil");
    private JMenu mnuStatistiques = new JMenu("Statistiques");
    private JMenu mnuInformations = new JMenu("Informations");
    private JMenu mnuStatistiquesGroupe = new JMenu("Choisissez le groupe!");

    private JMenuItem mnuItemIcon = new JMenuItem("Modifiez votre icon!");
    private JMenuItem mnuItemMDP = new JMenuItem("Modifiez votre mot de passe!");
    private JMenuItem mnuItemDragDrop = new JMenuItem("Apprenez plus sur le jeu Drag & Drop!");
    private JMenuItem mnuItemShooter = new JMenuItem("Apprenez plus sur le jeu Shooter!");
    private JMenuItem mnuItemCoureur = new JMenuItem("Apprenez plus sur le jeu Coureur!");
    private JMenuItem mnuItemSpeedRun = new JMenuItem("Apprenez plus sur le jeu Speed Run!");
    private JMenuItem mnuItemRemerciements = new JMenuItem("Apprenez plus sur les personnes impliquées!");

    private boolean enJeu = false;

    private JMenuItem[] listeGroupes;

    private final int largeur = 800, hauteur = 600;

    public MondeProfesseur(Controleur controleur, FenetrePrincipale fenetre) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreProfesseur());

        lblNom.setText(controleur.getProfesseur().getNom());
        lblNom.setLocation(260, 220);
        lblNom.setSize(190, 20);
        this.add(lblNom);

        lblNomUtilisateur.setText(controleur.getProfesseur().getNomUtilisateur());
        lblNomUtilisateur.setLocation(372, 248);
        lblNomUtilisateur.setSize(190, 20);
        this.add(lblNomUtilisateur);

        lblSession.setText(controleur.getProfesseur().getSession());
        lblSession.setLocation(286, 273);
        lblSession.setSize(190, 20);
        this.add(lblSession);

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

        mnuProfil.add(mnuItemIcon);
        mnuProfil.add(new JSeparator());
        mnuProfil.add(mnuItemMDP);

        listeGroupes = new JMenuItem[controleur.getProfesseur().getListeGroupes().size()];

        for (int i = 0; i < listeGroupes.length; i++) {
            JMenuItem mnuItemGroupe = new JMenuItem(controleur.getProfesseur().getListeGroupes().get(i).getCode());
            listeGroupes[i] = mnuItemGroupe;
            mnuStatistiquesGroupe.add(mnuItemGroupe);
            evenementsPourLesStatistiquesGroupes(i);
        }

        mnuStatistiques.add(mnuStatistiquesGroupe);

        mnuInformations.add(mnuItemDragDrop);
        mnuInformations.add(mnuItemShooter);
        mnuInformations.add(mnuItemCoureur);
        mnuInformations.add(mnuItemSpeedRun);
        mnuInformations.add(new JSeparator());
        mnuInformations.add(mnuItemRemerciements);

        mnuBar.add(mnuProfil);
        mnuBar.add(mnuStatistiques);
        mnuBar.add(mnuInformations);

        fenetre.addMenuBar(mnuBar);
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

        mnuItemMDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fenetre.ouvrirFenetreModificationMDP();
            }
        });

        //evenements des menus!! autres menus a ajouter plus tard
    }

    private void evenementsPourLesStatistiquesGroupes(int i) {
        listeGroupes[i].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
