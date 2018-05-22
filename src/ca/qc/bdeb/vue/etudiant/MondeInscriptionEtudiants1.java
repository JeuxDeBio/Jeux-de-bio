//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Etape 1 de l'inscription des etudiants
 *
 * @author Batikan
 */
public class MondeInscriptionEtudiants1 extends JComponent {

    private final int largeur = 350, hauteur = 200;

    private Controleur controleur;
    private FenetreInscription fenetre;

    private Image image;

    private JTextField txtDA = new JTextField("");
    private JPasswordField pssMotDePasse = new JPasswordField("");
    private JPasswordField pssMotDePasseValidation = new JPasswordField("");

    private Bouton boutonValidation = new Bouton();

    public MondeInscriptionEtudiants1(FenetreInscription fenetre, Controleur controleur) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements();
    }

    /**
     * Cree l'interface graphique
     */
    private void creerInterface() {

        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreInscriptionEtudiants1());

        txtDA.setLocation(140, 10);
        txtDA.setSize(195, 25);
        this.add(txtDA);

        pssMotDePasse.setLocation(140, 35);
        pssMotDePasse.setSize(195, 25);
        this.add(pssMotDePasse);

        pssMotDePasseValidation.setLocation(140, 65);
        pssMotDePasseValidation.setSize(195, 25);
        this.add(pssMotDePasseValidation);

        boutonValidation.setLocation(64, 133);
        boutonValidation.setSize(220, 47);
        this.add(boutonValidation);

    }

    /**
     * Cree les evenements
     */
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

                controleur.refresh();
                if (controleur.etudiantPermis(txtDA.getText())) {
                    fenetre.setErrorLog("");

                    fenetre.setErrorLog("");
                    if (motDePasse.equals(motDePasseValidation)) {
                        fenetre.setErrorLog("");
                        if (motDePasse.length() >= 6) {
                            fenetre.setErrorLog("DA authorise!");
                            fenetre.etape2Etudiants(txtDA.getText(), motDePasse);
                        } else {
                            fenetre.setErrorLog(controleur.getMessageErreur(3));
                            reset();
                        }
                    } else {
                        fenetre.setErrorLog(controleur.getMessageErreur(4));
                        reset();
                    }
                } else {
                    fenetre.setErrorLog(txtDA.getText() + controleur.getMessageErreur(5));
                    reset();
                    txtDA.setText("");
                }
            }

        }
        );
    }

    /**
     * Reset les text fields
     */
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
