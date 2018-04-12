/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.vue.etudiant.FenetreStatistiqueEtudiant;
import ca.qc.bdeb.vue.principale.Bouton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class MondeStatistiquesGroupe extends JComponent {

    private Controleur controleur;
    private FenetreStatistiquesGroupe fenetre;
    private Groupe groupe;

    private FenetreStatistiqueEtudiant fenetreEtudiant;

    private Image image;

    private ArrayList<Etudiant> listeEtudiants;
    private ArrayList<Bouton> listeBoutons = new ArrayList<>();

    private JLabel lblCode = new JLabel("");
    private JLabel lblNombreEtudiants = new JLabel("");

    private final int largeur = 300;
    private int hauteur = 100;

    MondeStatistiquesGroupe(Controleur controleur, FenetreStatistiquesGroupe fenetre, Groupe groupe) {
        this.listeEtudiants = groupe.getListeEtudiants();
        this.hauteur += 30 * (listeEtudiants.size());
        
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        creerInterface();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreStatistiquesGroupe());

        for (int i = 0; i < listeEtudiants.size(); i++) {
            JLabel lblEtudiant = new JLabel(listeEtudiants.get(i).getDa() + " | " + listeEtudiants.get(i).getNom(), JLabel.CENTER);
            lblEtudiant.setSize(300, 30);
            lblEtudiant.setLocation(0, 100 + ((lblEtudiant.getHeight() * i)));
            this.add(lblEtudiant);
            Bouton bouton = new Bouton();
            bouton.setSize(lblEtudiant.getWidth(), lblEtudiant.getHeight());
            bouton.setLocation(lblEtudiant.getX(), lblEtudiant.getY());
            this.add(bouton);
            listeBoutons.add(bouton);
            creerEvenements(i);
        }

        lblCode.setText(groupe.getCode());
        lblCode.setSize(228, 20);
        lblCode.setLocation(56, 21);
        this.add(lblCode);

        lblNombreEtudiants.setText(listeEtudiants.size() + " étudiants");
        lblNombreEtudiants.setSize(135, 20);
        lblNombreEtudiants.setLocation(146, 62);
        this.add(lblNombreEtudiants);
    }

    private void creerEvenements(int i) {
        listeBoutons.get(i).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                fenetreEtudiant = new FenetreStatistiqueEtudiant(controleur, null, groupe.getListeEtudiants().get(i));
                fenetreEtudiant.setLocation(fenetre.getX() + (fenetre.getWidth() - fenetreEtudiant.getWidth()) / 2, 200);
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
            
            g.fillRect(0, 100 + (30 * i), 300, 30);
        }
    }

}
