/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Professeur;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JPasswordField;

/**
 *
 * @author Batikan
 */
public class MondeModificationMDP extends JComponent {

    private Controleur controleur;

    private Image image;

    private FenetreModificationMDP fenetre;

    private Etudiant etudiant;
    private Professeur professeur;

    private JPasswordField pssMDPActuel = new JPasswordField();
    private JPasswordField pssMDP = new JPasswordField();
    private JPasswordField pssMDPVerification = new JPasswordField();

    private Bouton boutonSauvegarder = new Bouton();

    public MondeModificationMDP(Controleur controleur, FenetreModificationMDP fenetre, Etudiant etudiant) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.etudiant = etudiant;

        this.setPreferredSize(new Dimension(350, 241));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    public MondeModificationMDP(Controleur controleur, FenetreModificationMDP fenetre, Professeur professeur) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.professeur = professeur;

        this.setPreferredSize(new Dimension(350, 241));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreModificationMDP());

        pssMDPActuel.setLocation(140, 38);
        pssMDPActuel.setSize(200, 20);
        this.add(pssMDPActuel);

        pssMDP.setLocation(140, 90);
        pssMDP.setSize(200, 20);
        this.add(pssMDP);

        pssMDPVerification.setLocation(140, 143);
        pssMDPVerification.setSize(200, 20);
        this.add(pssMDPVerification);

        boutonSauvegarder.setLocation(50, 195);
        boutonSauvegarder.setSize(249, 40);
        this.add(boutonSauvegarder);
    }

    private void creerEvenements() {
        boutonSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.

                String mdpActuel = "";
                String mdp = "";
                String mdpVerification = "";

                for (int i = 0; i < pssMDPActuel.getPassword().length; i++) {
                    mdpActuel += pssMDPActuel.getPassword()[i];
                }

                for (int i = 0; i < pssMDP.getPassword().length; i++) {
                    mdp += pssMDP.getPassword()[i];
                }

                for (int i = 0; i < pssMDPVerification.getPassword().length; i++) {
                    mdpVerification += pssMDPVerification.getPassword()[i];
                }

                if (controleur.logInEtudiant()) {
                    if (mdpActuel.equals(controleur.getEtudiant().getMotDePasse())) {
                        if (!mdp.isEmpty()) {
                            if (!mdp.equals(mdpActuel)) {
                                if (mdp.equals(mdpVerification)) {
                                    controleur.etudiantModificationMDP(mdp);
                                    fenetre.fermerFenetre();
                                } else {
                                    fenetre.errorLogSetText("ERREUR! Mot de passe ne concorde pas avec la verification!");
                                    pssMDP.setText("");
                                    pssMDPVerification.setText("");
                                }
                            } else {
                                fenetre.errorLogSetText("ERREUR! Nouveau mot de passe identique a l'actuel!");
                                pssMDP.setText("");
                                pssMDPVerification.setText("");
                            }
                        } else {
                            fenetre.errorLogSetText("ERREUR! Nouveau mot de passe vide!");
                        }
                    } else {
                        fenetre.errorLogSetText("ERREUR! Mot de passe incorrecte!");
                        pssMDPActuel.setText("");
                        pssMDP.setText("");
                        pssMDPVerification.setText("");
                    }
                } else if (controleur.logInProfesseur()) {
                    if (mdpActuel.equals(controleur.getProfesseur().getMotDePasse())) {
                        if (!mdp.isEmpty()) {
                            if (!mdp.equals(mdpActuel)) {
                                if (mdp.equals(mdpVerification)) {
                                    controleur.professeurModificationMDP(mdp);
                                    fenetre.fermerFenetre();
                                } else {
                                    fenetre.errorLogSetText("ERREUR! Mot de passe ne concorde pas avec la verification!");
                                    pssMDP.setText("");
                                    pssMDPVerification.setText("");
                                }
                            } else {
                                fenetre.errorLogSetText("ERREUR! Nouveau mot de passe identique a l'actuel!");
                                pssMDP.setText("");
                                pssMDPVerification.setText("");
                            }
                        } else {
                            fenetre.errorLogSetText("ERREUR! Nouveau mot de passe vide!");
                        }
                    } else {
                        fenetre.errorLogSetText("ERREUR! Mot de passe incorrecte!");
                        pssMDPActuel.setText("");
                        pssMDP.setText("");
                        pssMDPVerification.setText("");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
    }

}
