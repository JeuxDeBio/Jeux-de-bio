//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class Icone extends JComponent {

    private Image image;
    private String locationIcone;
    private String description = "";

    private final int largeur = 82, hauteur = 99;

    public Icone(String location, String information) {
        this.locationIcone = location;

        for (int i = 0; i < information.length() - 4; i++) {
            this.description += information.charAt(i);
        }
        creerInterface();
    }

    public Icone(Controleur controleur) {
        if (controleur.logInEtudiant()) {
            this.locationIcone = controleur.getEtudiant().getLocationIcone();
        } else if (controleur.logInProfesseur()) {
            this.locationIcone = controleur.getProfesseur().getLocationIcone();
        }
        creerInterface();
    }

    public Icone(String locationIcone) {
        this.locationIcone = locationIcone;
        creerInterface();
    }

    /**
     * Cree l'icone
     */
    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(locationIcone);
        this.setSize(largeur, hauteur);
    }

    public String getLocationIcone() {
        return locationIcone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
