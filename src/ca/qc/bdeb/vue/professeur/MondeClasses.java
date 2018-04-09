/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Groupe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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

    private JMenuBar mnuBar = new JMenuBar();
    
    private JLabel lblCode = new JLabel();

    private JMenu mnuTous = new JMenu("Tous les étudiants");
    private JMenu mnuEnveler = new JMenu("Enlever");

    private JMenuItem mnuItemSelectionner = new JMenuItem("Sélectionner tous les étudiants!");
    private JMenuItem mnuItemDeselectionner = new JMenuItem("Désélectionner tous les étudiants!");
    private JMenuItem mnuItemEnlever = new JMenuItem("Enlever les étudiants sélectionnées!");

    private boolean enTraitement = false;

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
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreClasses());

        lblCode.setText(groupe.getCode());
        lblCode.setSize(226, 20);
        lblCode.setLocation(56, 16);
        this.add(lblCode);
        
        for (int i = 0; i < listeEtudiants.size(); i++) {
            JLabel lblEtudiant = new JLabel(listeEtudiants.get(i).getDa() + " | " + listeEtudiants.get(i).getNom(), JLabel.CENTER);
            lblEtudiant.setSize(270, 30);
            lblEtudiant.setLocation(0, 50 + (lblEtudiant.getHeight() * i));
            this.add(lblEtudiant);

            BoutonSelecteur boutonSelecteur = new BoutonSelecteur();
            boutonSelecteur.setLocation(largeur - boutonSelecteur.getWidth() - 10, lblEtudiant.getY() + 5);
            this.add(boutonSelecteur);
            listeBoutons.add(boutonSelecteur);
            creerEvenementsBoutons(i);
        }

        mnuTous.add(mnuItemSelectionner);
        mnuTous.add(new JSeparator());
        mnuTous.add(mnuItemDeselectionner);

        mnuEnveler.add(mnuItemEnlever);

        mnuBar.add(mnuTous);
        mnuBar.add(mnuEnveler);

        fenetre.setJMenuBar(mnuBar);
    }

    private void creerEvenementsBoutons(int i) {
        listeBoutons.get(i).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                listeBoutons.get(i).press();
                MondeClasses.this.invalidate();
                MondeClasses.this.repaint();
            }

        });
    }

    private void creerEvenements() {

        mnuItemSelectionner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeBoutons.size(); i++) {
                    if (!listeBoutons.get(i).estPresse()) {
                        listeBoutons.get(i).press();
                    }
                }
                MondeClasses.this.invalidate();
                MondeClasses.this.repaint();
            }
        });

        mnuItemDeselectionner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeBoutons.size(); i++) {
                    if (listeBoutons.get(i).estPresse()) {
                        listeBoutons.get(i).press();
                    }
                }
                MondeClasses.this.invalidate();
                MondeClasses.this.repaint();
            }
        });

        mnuItemEnlever.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> listeAEnlever = new ArrayList<>();

                for (int i = 0; i < listeBoutons.size(); i++) {
                    if (listeBoutons.get(i).estPresse()) {
                        listeAEnlever.add(listeEtudiants.indexOf(listeEtudiants.get(i)));
                    }
                }

                for (int i = listeEtudiants.size(); i >= 0; i--) {
                    if (listeAEnlever.contains(i)) {
                        controleur.enleverEtudiant(groupe, groupe.getListeEtudiants().get(i));
                    }
                }
                
                //fenetre.fermerFenetre();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
        for (int i = 0; i < listeEtudiants.size(); i++) {
            if (i % 2 == 0) {
                g.setColor(new Color(242, 242, 242));
            } else {
                g.setColor(Color.WHITE);
            }

            g.fillRect(0, 50 + (30 * i), 300, 30);
        }
    }

}
