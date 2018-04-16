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
public class MondeInscriptionProfesseurs1 extends JComponent {

    private final int largeur = 350, hauteur = 168;

    private Controleur controleur;
    private FenetreInscription fenetre;

    private Image image;

    private JTextField txtNU = new JTextField("");
    private JPasswordField pssMotDePasse = new JPasswordField("");
    private JPasswordField pssMotDePasseValidation = new JPasswordField("");

    private Bouton boutonValidation = new Bouton();

    public MondeInscriptionProfesseurs1(FenetreInscription fenetre, Controleur controleur) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreInscriptionProfesseurs1());

        txtNU.setLocation(156, 12);
        txtNU.setSize(186, 20);
        this.add(txtNU);

        pssMotDePasse.setLocation(125, 33);
        pssMotDePasse.setSize(217, 20);
        this.add(pssMotDePasse);

        pssMotDePasseValidation.setLocation(125, 58);
        pssMotDePasseValidation.setSize(216, 20);
        this.add(pssMotDePasseValidation);

        boutonValidation.setLocation(64, 108);
        boutonValidation.setSize(220, 47);
        this.add(boutonValidation);

    }

    private void creerEvenements() {
        boutonValidation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.

                String motDePasse = "";
                for (int i = 0; i < pssMotDePasse.getPassword().length; i++) {
                    motDePasse += pssMotDePasse.getPassword()[i];
                }

                String motDePasseValidation = "";
                for (int i = 0; i < pssMotDePasseValidation.getPassword().length; i++) {
                    motDePasseValidation += pssMotDePasseValidation.getPassword()[i];
                }

                if (controleur.professeurPermis(txtNU.getText())) {
                    fenetre.setErrorLog("");
                    if (motDePasse.equals(motDePasseValidation)) {
                        fenetre.setErrorLog("");
                        if (motDePasse.length() >= 6) {
                            fenetre.etape2Professeurs(txtNU.getText(), motDePasse);
                        } else {
                            fenetre.setErrorLog("ERREUR! Mot de passe est trop court!");
                        }
                    } else {
                        fenetre.setErrorLog("ERREUR! Mot de passe ne concorde pas ...");
                        reset();
                    }
                } else {
                    fenetre.setErrorLog("ERREUR! Le nom d'utilisater n'est pas permis!");
                    txtNU.setText("");
                    reset();
                }
            }

        });
    }

    private void reset() {
        pssMotDePasse.setText("");
        pssMotDePasseValidation.setText("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        g.drawLine(0, hauteur - 1, largeur, hauteur - 1);
    }
}
