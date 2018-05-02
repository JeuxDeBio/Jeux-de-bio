/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.etudiant;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.principale.Icone;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 *
 * @author 1649904
 */
public class MondeEtudiant extends JComponent {

    private Controleur controleur;

    private Image image;

    private FenetrePrincipale fenetre;

    private Icone icone;

    private JLabel lblNom = new JLabel();
    private JLabel lblDA = new JLabel();
    private JLabel lblGroupe = new JLabel();
    private JLabel lblProf = new JLabel();

    private Bouton boutonDragDrop = new Bouton();
    private Bouton boutonShooter = new Bouton();
    private Bouton boutonCoureur = new Bouton();
    private Bouton boutonSpeedRun = new Bouton();
    private Bouton boutonLogOut = new Bouton();
    private Bouton boutonDragDropTutorial = new Bouton();
    private Bouton boutonShooterTutorial = new Bouton();
    private Bouton boutonCoureurTutorial = new Bouton();
    private Bouton boutonSpeedRunTutorial = new Bouton();
    private Bouton boutonActualiser = new Bouton();

    private JMenuBar mnuBar = new JMenuBar();

    private JMenu mnuProfil = new JMenu("Profil");
    private JMenu mnuStatistiques = new JMenu("Statistiques");
    private JMenu mnuInformations = new JMenu("Informations");

    private JMenuItem mnuItemIcon = new JMenuItem("Modifiez votre icon!");
    private JMenuItem mnuItemMDP = new JMenuItem("Modifiez votre mot de passe!");
    private JMenuItem mnuItemStatistiques = new JMenuItem("Visionnez vos statistiques!");
    private JMenuItem mnuItemDragDrop = new JMenuItem("Apprenez plus sur le jeu Drag & Drop!");
    private JMenuItem mnuItemShooter = new JMenuItem("Apprenez plus sur le jeu Shooter!");
    private JMenuItem mnuItemCoureur = new JMenuItem("Apprenez plus sur le jeu Coureur!");
    private JMenuItem mnuItemSpeedRun = new JMenuItem("Apprenez plus sur le jeu Speed Run!");
    private JMenuItem mnuItemRemerciements = new JMenuItem("Apprenez plus sur les personnes impliquées!");

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

        icone = new Icone(controleur.getEtudiant().getLocationIcone());
        icone.setLocation(505, 225);
        this.add(icone);

        lblNom.setText(controleur.getEtudiant().getNom());
        lblNom.setLocation(260, 224);
        lblNom.setSize(190, 20);
        this.add(lblNom);

        lblDA.setText(controleur.getEtudiant().getDa());
        lblDA.setLocation(244, 250);
        lblDA.setSize(190, 20);
        this.add(lblDA);

        //     lblGroupe.setText(controleur.getEtudiant().getGroupe().getCode());
        lblGroupe.setLocation(283, 277);
        lblGroupe.setSize(190, 20);
        this.add(lblGroupe);

//       lblProf.setText(controleur.getEtudiant().getProfesseur().getNom() + "");
        lblProf.setLocation(314, 301);
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

        boutonDragDropTutorial.setLocation(627, 194);
        boutonDragDropTutorial.setSize(147, 74);
        this.add(boutonDragDropTutorial);

        boutonShooterTutorial.setLocation(627, 290);
        boutonShooterTutorial.setSize(147, 74);
        this.add(boutonShooterTutorial);

        boutonCoureurTutorial.setLocation(627, 385);
        boutonCoureurTutorial.setSize(147, 74);
        this.add(boutonCoureurTutorial);

        boutonSpeedRunTutorial.setLocation(627, 479);
        boutonSpeedRunTutorial.setSize(147, 74);
        this.add(boutonSpeedRunTutorial);

        boutonLogOut.setLocation(731, 19);
        boutonLogOut.setSize(50, 50);
        this.add(boutonLogOut);

        boutonActualiser.setLocation(20, 20);
        boutonActualiser.setSize(50, 50);
        this.add(boutonActualiser);

        mnuProfil.add(mnuItemIcon);
        mnuProfil.add(new JSeparator());
        mnuProfil.add(mnuItemMDP);

        mnuStatistiques.add(mnuItemStatistiques);

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
        String action = "jouer";

        boutonDragDrop.addMouseListener(new MouseAdapter() {
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

        boutonLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    controleur.logOutEtudiant();
                    fenetre.logOutEtudiant();
                    enJeu = true;
                }
            }

        });

        boutonDragDropTutorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.DRAG_DROP);
                    enJeu = true;
                }
            }

        });

        boutonShooterTutorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.SHOOTER);
                    enJeu = true;
                }
            }
        });

        boutonCoureurTutorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.COUREUR);
                    enJeu = true;
                }
            }
        });

        boutonSpeedRunTutorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.SPEED_RUN);
                    enJeu = true;
                }
            }
        });

        mnuItemMDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreModificationMDP();
                    enJeu = true;
                }
            }
        });

        mnuItemStatistiques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreStatistiquesEtudiant();
                    enJeu = true;
                }
            }
        });

        mnuItemIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreModificationIcone();
                    enJeu = true;
                }
            }
        });

        mnuItemDragDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.DRAG_DROP);
                    enJeu = true;
                }
            }
        });

        mnuItemShooter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.SHOOTER);
                    enJeu = true;
                }
            }
        });

        mnuItemCoureur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.COUREUR);
                    enJeu = true;
                }
            }
        });

        mnuItemSpeedRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enJeu) {
                    fenetre.ouvrirFenetreTutorial(Jeu.SPEED_RUN);
                    enJeu = true;
                }
            }
        });

        mnuItemRemerciements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enJeu) {
                    JOptionPane.showMessageDialog(MondeEtudiant.this, "Les personnes sans qui ce logiciel n'aurait pas vu le jour:\n\nBatikan ISCAN - Codeur\nNicolas CHARRON - Codeur\nPatrick DROLET-SAVOIE - Pofesseur de Biologie\nRaouf BABARI - Professeur d'Informatique", "Personnes impliquées", JOptionPane.PLAIN_MESSAGE);
                    enJeu = true;
                }
            }
        });

        boutonActualiser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    controleur.refresh();
                    enJeu = true;
                }
            }

        });

        //shit ton d'autres evenements (pour les menuItems) pour PLUS TARD (comme la fin de semaine ou la semaine prochaine jsp)
        //autres menus a ajouter plus tard
    }

    public void finJeu() {
        this.enJeu = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }
}
