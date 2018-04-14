/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.etudiant;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.Bouton;
import ca.qc.bdeb.vue.principale.FenetreInscription;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author 1649904
 */
public class MondeInscriptionEtudiants2 extends JComponent {

    private final int largeur = 350, hauteur = 200;

    private Controleur controleur;
    private FenetreInscription fenetre;

    private Image image;

    private JTextField txtNom = new JTextField("");
    private JLabel lblProfesseur = new JLabel();
    private JLabel lblGroupe = new JLabel();

    private Bouton boutonValidation = new Bouton();

    public MondeInscriptionEtudiants2(FenetreInscription fenetre, Controleur controleur, String da, String motDePasse) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements(da, motDePasse);
    }

    private void creerInterface() {

        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreInscriptionEtudiants2());

        txtNom.setLocation(107, 14);
        txtNom.setSize(233, 20);
        this.add(txtNom);

        lblProfesseur.setText(controleur.getNomProfesseurNouveauEtudiant());
        lblProfesseur.setLocation(154, 83);
        lblProfesseur.setSize(186, 20);
        this.add(lblProfesseur);

        lblGroupe.setText(controleur.getCodeGroupeNouveauEtudiant());
        lblGroupe.setLocation(123, 105);
        lblGroupe.setSize(217, 20);
        this.add(lblGroupe);

        boutonValidation.setLocation(64, 133);
        boutonValidation.setSize(220, 47);
        this.add(boutonValidation);

    }

    private void creerEvenements(String da, String motDePasse) {
        boutonValidation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                controleur.creerEtudiant(da, motDePasse, txtNom.getText());
                fenetre.fermerFenetre();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        g.drawLine(0, hauteur - 1, largeur, hauteur - 1);
    }

}
