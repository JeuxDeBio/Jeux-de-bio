/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.vue.etudiant.FenetreStatistiqueEtudiant;
import ca.qc.bdeb.vue.principale.Bouton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;

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

    private JList lstEtudiants;
    private String[] listeEtudiants;
    private ArrayList<Bouton> listeBoutons = new ArrayList<>();
    
    private JLabel lblCode = new JLabel("");
    private JLabel lblNombreEtudiants = new JLabel("");

    private final int largeur = 300, hauteur = 720;

    MondeStatistiquesGroupe(Controleur controleur, FenetreStatistiquesGroupe fenetre, Groupe groupe) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        creerInterface();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreStatistiquesGroupe());

        listeEtudiants = new String[groupe.getListeEtudiants().size()];
        for (int i = 0; i < listeEtudiants.length; i++) {
            Bouton bouton = new Bouton();
            bouton.setSize(largeur - 10, 20);
            bouton.setLocation(5, (20 * i) + 100);
            listeBoutons.add(bouton);
            listeEtudiants[i] = groupe.getListeEtudiants().get(i).getDa() + " | " + groupe.getListeEtudiants().get(i).getNom();
            this.add(bouton);
            creerEvenements(i);
        }
        lstEtudiants = new JList(listeEtudiants);
        lstEtudiants.setSize(largeur - 10, (20 * listeEtudiants.length));
        lstEtudiants.setLocation(5, 100);
        this.add(lstEtudiants);

        lblCode.setText(groupe.getCode());
        lblCode.setSize(228, 20);
        lblCode.setLocation(56, 19);
        this.add(lblCode);
        
        lblNombreEtudiants.setText(listeEtudiants.length + " étudiants");
        lblNombreEtudiants.setSize(135, 20);
        lblNombreEtudiants.setLocation(146, 59);
        this.add(lblNombreEtudiants);
    }

    private void creerEvenements(int i) {
        listeBoutons.get(i).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                fenetreEtudiant = new FenetreStatistiqueEtudiant(controleur, null, groupe.getListeEtudiants().get(i));
                fenetreEtudiant.setLocation(fenetre.getX() + (fenetre.getWidth() - fenetreEtudiant.getWidth()) / 2, fenetre.getY() + (fenetre.getHeight()- fenetreEtudiant.getHeight()) / 2);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
