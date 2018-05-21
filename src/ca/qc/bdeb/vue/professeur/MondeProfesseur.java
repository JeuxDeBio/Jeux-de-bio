/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.TypeUtilisateur;
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
 * @author 1651114
 */
public class MondeProfesseur extends JComponent {

    private Controleur controleur;

    private Image image;

    private FenetrePrincipale fenetre;
    private FenetreStatistiquesGroupe fenetreStatistiques;

    private JLabel lblNom = new JLabel();
    private JLabel lblNomUtilisateur = new JLabel();
    private JLabel lblSession = new JLabel();
    private JLabel lblType = new JLabel();

    private Icone icone;

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
    private JMenu mnuInformations = new JMenu("Informations");
    private JMenu mnuStatistiques = new JMenu("Statistiques");
    private JMenu mnuStatistiquesGroupe = new JMenu("Choisissez le groupe!");
    private JMenu mnuClasses = new JMenu("Liste des groupes");
    private JMenu mnuClassesGroupe = new JMenu("Choissez le groupe!");
    private JMenu mnuAdminOptions = new JMenu("Options d'admin");
    private JMenu mnuEnleverProfesseur = new JMenu("Enlever un professeur!");
    private JMenu mnuSetAdmin = new JMenu("Ceder vos droits d'admin!");

    private JMenuItem mnuItemIcon = new JMenuItem("Modifiez votre icon!");
    private JMenuItem mnuItemMDP = new JMenuItem("Modifiez votre mot de passe!");
    private JMenuItem mnuItemDragDrop = new JMenuItem("Apprenez plus sur le jeu Drag & Drop!");
    private JMenuItem mnuItemShooter = new JMenuItem("Apprenez plus sur le jeu Shooter!");
    private JMenuItem mnuItemCoureur = new JMenuItem("Apprenez plus sur le jeu Coureur!");
    private JMenuItem mnuItemSpeedRun = new JMenuItem("Apprenez plus sur le jeu Speed Run!");
    private JMenuItem mnuItemRemerciements = new JMenuItem("Apprenez plus sur les personnes impliquées!");
    private JMenuItem mnuItemAuthentifierProfesseur = new JMenuItem("Authentifier un professeur!");
    private JMenuItem mnuItemCreerGroupe = new JMenuItem("Créer nouveau groupe!");

    private Bouton boutonActualiser = new Bouton();

    private boolean enJeu = false;

    private JMenuItem[] listeGroupesStatistiques;
    private JMenuItem[] listeGroupes;
    private JMenuItem[] listeProfesseursEnlever;
    private JMenuItem[] listeProfesseursCederAdmin;

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

        icone = new Icone(controleur.getProfesseur().getLocationIcone());
        icone.setLocation(505, 225);
        this.add(icone);

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

        String type = "Professeur";
        if (controleur.getProfesseur().getType() == TypeUtilisateur.ADMIN) {
            type = "Administrateur";
        }
        lblType.setText(type);
        lblType.setLocation(356, 295);
        lblType.setSize(233, 20);
        this.add(lblType);

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
        boutonCrDragDrop.setSize(147, 74);
        this.add(boutonCrDragDrop);

        boutonCrShooter.setLocation(627, 290);
        boutonCrShooter.setSize(147, 74);
        this.add(boutonCrShooter);

        boutonCrCoureur.setLocation(627, 385);
        boutonCrCoureur.setSize(147, 74);
        this.add(boutonCrCoureur);

        boutonCrSpeedRun.setLocation(627, 479);
        boutonCrSpeedRun.setSize(147, 74);
        this.add(boutonCrSpeedRun);

        boutonLogOut.setLocation(731, 19);
        boutonLogOut.setSize(50, 50);
        this.add(boutonLogOut);

        mnuProfil.add(mnuItemIcon);
        mnuProfil.add(new JSeparator());
        mnuProfil.add(mnuItemMDP);

        boutonActualiser.setLocation(20, 20);
        boutonActualiser.setSize(50, 50);
        this.add(boutonActualiser);

        listeGroupesStatistiques = new JMenuItem[controleur.getProfesseur().getListeGroupes().size()];

        for (int i = 0; i < listeGroupesStatistiques.length; i++) {
            JMenuItem mnuItemGroupe = new JMenuItem(controleur.getProfesseur().getListeGroupes().get(i).getCode());
            listeGroupesStatistiques[i] = mnuItemGroupe;
            mnuStatistiquesGroupe.add(mnuItemGroupe);
            evenementsGroupesStatistiques(i);
        }

        mnuStatistiques.add(mnuStatistiquesGroupe);

        listeGroupes = new JMenuItem[controleur.getProfesseur().getListeGroupes().size()];

        for (int i = 0; i < listeGroupes.length; i++) {
            JMenuItem mnuItemGroupe = new JMenuItem(controleur.getProfesseur().getListeGroupes().get(i).getCode());
            listeGroupes[i] = mnuItemGroupe;
            mnuClassesGroupe.add(mnuItemGroupe);
            evenementsGroupesClasses(i);
        }
        mnuClasses.add(mnuClassesGroupe);
        mnuClasses.add(new JSeparator());
        mnuClasses.add(mnuItemCreerGroupe);

        mnuInformations.add(mnuItemDragDrop);
        mnuInformations.add(mnuItemShooter);
        mnuInformations.add(mnuItemCoureur);
        mnuInformations.add(mnuItemSpeedRun);
        mnuInformations.add(new JSeparator());
        mnuInformations.add(mnuItemRemerciements);

        listeProfesseursEnlever = new JMenuItem[controleur.getListeProfesseurs().size()];

        for (int i = 0; i < listeProfesseursEnlever.length; i++) {
            type = "";
            if (controleur.getListeProfesseurs().get(i).getType() == TypeUtilisateur.ADMIN) {
                type = " (A)";
            }
            JMenuItem mnuItemProfesseur = new JMenuItem(controleur.getListeProfesseurs().get(i).getNom() + type);
            listeProfesseursEnlever[i] = mnuItemProfesseur;
            mnuEnleverProfesseur.add(mnuItemProfesseur);
            evenementsEnleverProfesseurs(i);
        }

        listeProfesseursCederAdmin = new JMenuItem[controleur.getListeProfesseurs().size()];

        for (int i = 0; i < listeProfesseursCederAdmin.length; i++) {
            type = "";
            if (controleur.getListeProfesseurs().get(i).getType() == TypeUtilisateur.ADMIN) {
                type = " (A)";
            }
            JMenuItem mnuItemProfesseur = new JMenuItem(controleur.getListeProfesseurs().get(i).getNom() + type);
            listeProfesseursCederAdmin[i] = mnuItemProfesseur;
            mnuSetAdmin.add(mnuItemProfesseur);
            evenementsCederAdmin(i);
        }

        if (controleur.getProfesseur().estAdmin()) {
            mnuAdminOptions.add(mnuItemAuthentifierProfesseur);
            mnuAdminOptions.add(mnuEnleverProfesseur);
            mnuAdminOptions.add(mnuSetAdmin);
            mnuBar.add(mnuAdminOptions);
        }

        mnuBar.add(mnuProfil);
        mnuBar.add(mnuStatistiques);
        mnuBar.add(mnuClasses);
        mnuBar.add(mnuInformations);

        fenetre.addMenuBar(mnuBar);
    }

    private void creerEvenements() {

        boutonMdDragDrop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;

                    fenetre.ouvrirFenetreSelectionJeu(Jeu.DRAG_DROP, "modifier");
                }

            }
        });

        boutonMdShooter.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.SHOOTER, "modifier");
                }
            }
        });

        boutonMdCoureur.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.COUREUR, "modifier");
                }

            }
        });

        boutonMdSpeedRun.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                    fenetre.ouvrirFenetreSelectionJeu(Jeu.SPEED_RUN, "modifier");
                }
            }
        });

        boutonCrDragDrop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                    fenetre.ouvrirFenetreCreation(Jeu.DRAG_DROP);
                }
            }
        });

        boutonCrShooter.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                     fenetre.ouvrirFenetreCreation(Jeu.DRAG_DROP);
                }
            }
        });

        boutonCrCoureur.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                    fenetre.ouvrirFenetreCreation(Jeu.COUREUR);
                }
            }
        });

        boutonCrSpeedRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (!enJeu) {
                    enJeu = true;
                    fenetre.ouvrirFenetreCreation(Jeu.SPEED_RUN);
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

        boutonActualiser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                controleur.refresh();
            }

        });

        mnuItemMDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fenetre.ouvrirFenetreModificationMDP();
            }
        });

        mnuItemIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ouvrirFenetreModificationIcone();
            }
        });

        mnuItemAuthentifierProfesseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (JOptionPane.showInputDialog(MondeProfesseur.this, "Veuillez entrer votre mot de passe").equals(controleur.getProfesseur().getMotDePasse())) {
                        try {
                            String nuAdmis = JOptionPane.showInputDialog(MondeProfesseur.this, "Veuillez réserver un nom d'utilisateur");
                            if (nuAdmis.length() >= 6) {
                                controleur.refresh();
                                if (!controleur.professeurExisteDeja(nuAdmis)) {
                                    if (!controleur.professeurDejaAuthentifie(nuAdmis)) {
                                        controleur.ajouterProfesseurNUAdmis(nuAdmis);
                                        JOptionPane.showMessageDialog(MondeProfesseur.this, "Le nom d'utilisateur\n<" + nuAdmis + ">\na été réservé!");
                                        controleur.refresh();
                                    } else {
                                        JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(11), "ERREUR", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(12), "ERREUR", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(13), "ERREUR", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(16), "Opération annulé", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(2), "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(16), "Opération annulé", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        mnuItemCreerGroupe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ouvrirFenetreAjoutClasses();
            }
        });

        mnuItemDragDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ouvrirFenetreTutorial(Jeu.DRAG_DROP);
            }
        });

        mnuItemShooter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ouvrirFenetreTutorial(Jeu.SHOOTER);
            }
        });

        mnuItemCoureur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fenetre.ouvrirFenetreTutorial(Jeu.COUREUR);
            }
        });

        mnuItemSpeedRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ouvrirFenetreTutorial(Jeu.SPEED_RUN);
            }
        });

        mnuItemRemerciements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MondeProfesseur.this, "Les personnes sans qui ce logiciel n'aurait pas vu le jour:\n\nBatikan ISCAN - Codeur\nNicolas CHARRON - Codeur\nPatrick DROLET-SAVOIE - Pofesseur de Biologie\nRaouf BABARI - Professeur d'Informatique", "Personnes impliquées", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private void evenementsGroupesStatistiques(int i) {
        listeGroupesStatistiques[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetreStatistiques = new FenetreStatistiquesGroupe(controleur, fenetre, controleur.getProfesseur().getListeGroupes().get(i));
                fenetreStatistiques.setLocation(fenetre.getX() + (fenetre.getWidth() - fenetreStatistiques.getWidth()) / 2, fenetre.getY() + (fenetre.getHeight() - fenetreStatistiques.getHeight()) / 2);
            }
        });
    }

    private void evenementsGroupesClasses(int i) {
        listeGroupes[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ouvrirFenetreClasses(controleur.getProfesseur().getListeGroupes().get(i));
            }
        });
    }

    private void evenementsEnleverProfesseurs(int i) {
        listeProfesseursEnlever[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (controleur.getListeProfesseurs().get(i).getType() != TypeUtilisateur.ADMIN) {
                        if (JOptionPane.showInputDialog(MondeProfesseur.this, "Veuillez entrez votre mot de passe").equals(controleur.getProfesseur().getMotDePasse())) {
                            JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getListeProfesseurs().get(i).getNom() + " a ete enleve avec succes!", "Operation reussi", JOptionPane.INFORMATION_MESSAGE);
                            controleur.enleverProfesseur(controleur.getListeProfesseurs().get(i));
                            controleur.refresh();
                        } else {
                            JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(2), "ERREUR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(14), "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(16), "Operation annule", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    
     public void finJeu(){
        this.enJeu = false;
    }

    private void evenementsCederAdmin(int i) {
        listeProfesseursCederAdmin[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (controleur.getListeProfesseurs().get(i).getType() != TypeUtilisateur.ADMIN) {
                        if (JOptionPane.showInputDialog(MondeProfesseur.this, "Veuillez entrez votre mot de passe").equals(controleur.getProfesseur().getMotDePasse())) {
                            JOptionPane.showMessageDialog(MondeProfesseur.this, "Cedé les droits d'administration a " + controleur.getListeProfesseurs().get(i).getNom() + " avec succes!", "Operation reussi", JOptionPane.INFORMATION_MESSAGE);
                            controleur.cederAdmin(controleur.getListeProfesseurs().get(i));
                            controleur.refresh();
                            fenetre.logOutProfesseur();
                            fenetre.logInProfesseur();
                        } else {
                            JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(2), "ERREUR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(MondeProfesseur.this, "ERREUR! " + controleur.getListeProfesseurs().get(i).getNom() + controleur.getMessageErreur(15), "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(MondeProfesseur.this, controleur.getMessageErreur(16), "Operation annule", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }
}
