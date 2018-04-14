/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
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

/**
 *
 * @author 1649904
 */
public class MondeModificationIcone extends JComponent {

    private Controleur controleur;

    private Image image;

    private FenetreModificationIcone fenetre;

    private Bouton boutonSauvegarder = new Bouton();
    private Icone iconePreview;

    private ArrayList<Icone> listeIcones = new ArrayList<>();
    private ArrayList<Bouton> listeBoutons = new ArrayList<>();

    private int indexIcone = 0;

    private JMenuBar mnuBar = new JMenuBar();

    private JMenu mnuVoirToutes = new JMenu("Voir tous les icônes!");

    private JMenuItem mnuItemVoirToutes = new JMenuItem("Voir toutes les icônes!");

    private final int largeur = 300;
    private int hauteur = 125;

    public MondeModificationIcone(Controleur controleur, FenetreModificationIcone fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        listeIcones = controleur.getListeIcones();

        hauteur += (listeIcones.size() * 30);

        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreModificationIcone());

        boutonSauvegarder.setSize(125, 27);
        boutonSauvegarder.setLocation(12, 86);
        this.add(boutonSauvegarder);

        iconePreview = new Icone(controleur);
        iconePreview.setLocation(202, 12);
        this.add(iconePreview);

        for (int i = 0; i < listeIcones.size(); i++) {
            if (iconePreview.getLocationIcone().equals(listeIcones.get(i).getLocationIcone())) {
                indexIcone = i;
            }
        }

        for (int i = 0; i < listeIcones.size(); i++) {
            JLabel lblIcone = new JLabel(listeIcones.get(i).getDescription(), JLabel.CENTER);
            lblIcone.setSize(largeur, 30);
            lblIcone.setLocation(0, 125 + (i * lblIcone.getHeight()));
            this.add(lblIcone);
            Bouton bouton = new Bouton();
            bouton.setSize(largeur, 30);
            bouton.setLocation(0, 125 + (i * bouton.getHeight()));
            this.add(bouton);
            listeBoutons.add(bouton);
            creerEvenements(i);
        }

        mnuVoirToutes.add(mnuItemVoirToutes);
        mnuBar.add(mnuVoirToutes);
        fenetre.setJMenuBar(mnuBar);

    }

    private void creerEvenements() {
        boutonSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                if (controleur.logInEtudiant()) {
                    controleur.getEtudiant().setLocationIcone(listeIcones.get(indexIcone).getLocationIcone());
                } else if (controleur.logInProfesseur()) {
                    controleur.getProfesseur().setLocationIcone(listeIcones.get(indexIcone).getLocationIcone());
                }
                fenetre.fermerFenetre();
            }
        }
        );

        mnuItemVoirToutes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.ajouterMondeVisualisation();
            }
        });
    }

    private void creerEvenements(int i) {
        listeBoutons.get(i).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                remove(iconePreview);
                iconePreview = new Icone(listeIcones.get(i).getLocationIcone(), "");
                add(iconePreview);
                iconePreview.setLocation(202, 12);
                indexIcone = i;
                invalidate();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
        for (int i = 0; i < listeIcones.size(); i++) {
            if (i % 2 == 0) {
                grphcs.setColor(new Color(242, 242, 242));
            } else {
                grphcs.setColor(Color.WHITE);
            }

            grphcs.fillRect(0, 125 + (30 * i), largeur, 30);
        }
    }
}
