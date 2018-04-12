/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
 */
public class Icone extends JComponent {

    private Image image;
    private String locationIcone;

    private final int largeur = 82, hauteur = 99;

    public Icone(String location) {
        this.locationIcone = location;
        creerInterface();
    }

    public Icone(Controleur controleur) {
        if (controleur.logInEtudiant()) {
            locationIcone = controleur.getEtudiant().getLocationIcone();
        } else if (controleur.logInProfesseur()) {
            locationIcone = controleur.getProfesseur().getLocationIcone();
        }
        creerInterface();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(locationIcone);
        this.setSize(largeur, hauteur);
    }

    public String getLocationIcone() {
        return locationIcone;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
