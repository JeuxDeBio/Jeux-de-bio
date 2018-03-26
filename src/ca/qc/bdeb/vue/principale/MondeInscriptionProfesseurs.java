/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
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

    private JTextField txtDA = new JTextField("");
    private JPasswordField txtMotDePasse = new JPasswordField("");
    private JPasswordField txtMotDePasseValidation = new JPasswordField("");

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
        String personne = "prof";
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreInscription(personne));

        txtDA.setLocation(140, 10);
        txtDA.setSize(195, 25);
        this.add(txtDA);

        txtMotDePasse.setLocation(140, 35);
        txtMotDePasse.setSize(195, 25);
        this.add(txtMotDePasse);

        txtMotDePasseValidation.setLocation(140, 65);
        txtMotDePasseValidation.setSize(195, 25);
        this.add(txtMotDePasseValidation);

        boutonValidation.setLocation(64, 133);
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

                if (controleur.professeurExiste(txtDA.getText()) && motDePasse.equals(motDePasseValidation) && !motDePasse.equals(null)) {
                    controleur.creerProfesseur(txtDA.getText());
                    fenetre.fermerFenetre();
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

