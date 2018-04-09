/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
class MondeSelection extends JComponent {

    private Modele modele;

    private Controleur controleur;

    private Image image;

    private FenetreSelection fenetre;

    ArrayList<Bouton> listeNiveaux = new ArrayList();

    private Jeu jeu;

    public MondeSelection(Modele modele, Controleur controleur, FenetreSelection fenetre, Jeu jeu, String action) {
        this.setPreferredSize(new Dimension(300, 600));
        this.setLayout(null);

        this.modele = modele;
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.jeu = jeu;

        creerInterface(action);
    }

    private void creerInterface(String action) {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreSelection());

        for (int i = 0; i < 10; i++) {
            Bouton niveau = new Bouton(controleur, i, jeu);
            niveau.setSize(130, 84);
            switch (i) {
                case 0:
                    niveau.setLocation(10, 128);
                    break;
                case 1:
                    niveau.setLocation(153, 128);
                    break;
                case 2:
                    niveau.setLocation(10, 220);
                    break;
                case 3:
                    niveau.setLocation(153, 220);
                    break;
                case 4:
                    niveau.setLocation(10, 312);
                    break;
                case 5:
                    niveau.setLocation(153, 312);
                    break;
                case 6:
                    niveau.setLocation(10, 404);
                    break;
                case 7:
                    niveau.setLocation(153, 404);
                    break;
                case 8:
                    niveau.setLocation(10, 496);
                    break;
                case 9:
                    niveau.setLocation(153, 496);
            }
            this.add(niveau);
            listeNiveaux.add(niveau);
            creerEvenements(i, action);
        }

        String texte = "";
        switch (jeu) {
            case DRAG_DROP:
                texte = "Drag & Drop";
                break;
            case SHOOTER:
                texte = "Shooter";
                break;
            case COUREUR:
                texte = "Coureur";
                break;
            case SPEED_RUN:
                texte = "Speed Run";
        }
        Bouton bouton = new Bouton(texte);
        bouton.setLocation(10, 10);
        bouton.setSize(270, 70);
        this.add(bouton);

    }

    private void creerEvenements(int i, String action) {
        listeNiveaux.get(i).addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                if (listeNiveaux.get(i).existe(controleur, i, jeu)) {
                    fenetre.ouvrirFenetreJeu(i, action);
                } else {
                    fenetre.finJeu();
                }
                fermerFenetre();
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreSelection();
    }

}
