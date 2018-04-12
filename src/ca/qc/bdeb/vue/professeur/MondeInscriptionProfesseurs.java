/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author 1651114
 */
public class MondeInscriptionProfesseurs extends JComponent {

    private final int largeur = 350, hauteur = 200;

    private Controleur controleur;
    private FenetreInscription fenetre;

    private Image image;

    private JTextField txtNU = new JTextField("");
    private JPasswordField txtMotDePasse = new JPasswordField("");
    private JPasswordField txtMotDePasseValidation = new JPasswordField("");
    private JTextField txtCodeValidation = new JTextField("");

    private Bouton boutonValidation = new Bouton();

    public MondeInscriptionProfesseurs(FenetreInscription fenetre, Controleur controleur) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreInscriptionProfesseurs());

        txtNU.setLocation(156, 12);
        txtNU.setSize(186, 20);
        this.add(txtNU);

        txtMotDePasse.setLocation(125, 33);
        txtMotDePasse.setSize(217, 20);
        this.add(txtMotDePasse);

        txtMotDePasseValidation.setLocation(125, 58);
        txtMotDePasseValidation.setSize(216, 20);
        this.add(txtMotDePasseValidation);

        txtCodeValidation.setLocation(166, 111);
        txtCodeValidation.setSize(175, 20);
        this.add(txtCodeValidation);

        boutonValidation.setLocation(64, 138);
        boutonValidation.setSize(220, 47);
        this.add(boutonValidation);

    }

    private void creerEvenements() {
        boutonValidation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.

                String motDePasse = "";
                for (int i = 0; i < txtMotDePasse.getPassword().length; i++) {
                    motDePasse += txtMotDePasse.getPassword()[i];
                }

                String motDePasseValidation = "";
                for (int i = 0; i < txtMotDePasseValidation.getPassword().length; i++) {
                    motDePasseValidation += txtMotDePasseValidation.getPassword()[i];
                }

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
