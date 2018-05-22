//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
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
import javax.swing.JTextField;

/**
 *
 * @author Nicolas
 */
public class MondeInscriptionProfesseurs2 extends JComponent {

    private final int largeur = 350, hauteur = 168;

    private Controleur controleur;
    private FenetreInscription fenetre;

    private Image image;

    private JTextField txtNom = new JTextField();
    private JTextField txtSession = new JTextField();

    private Bouton boutonValidation = new Bouton();

    public MondeInscriptionProfesseurs2(FenetreInscription fenetre, Controleur controleur, String da, String motDePasse) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();
        creerEvenements(da, motDePasse);
    }

    /**
     * Cree l'interface
     */
    private void creerInterface() {

        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreInscriptionProfesseurs2());

        txtNom.setLocation(105, 11);
        txtNom.setSize(232, 20);
        this.add(txtNom);

        txtSession.setLocation(173, 36);
        txtSession.setSize(164, 20);
        this.add(txtSession);

        boutonValidation.setLocation(64, 133);
        boutonValidation.setSize(220, 47);
        this.add(boutonValidation);

    }

    /**
     * Cree les evenements
     *
     * @param nu le nom d'utilisateur fourni
     * @param motDePasse le mot de passe fourni
     */
    private void creerEvenements(String nu, String motDePasse) {
        boutonValidation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                controleur.creerProfesseur(nu, motDePasse, txtNom.getText(), txtSession.getText());
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
