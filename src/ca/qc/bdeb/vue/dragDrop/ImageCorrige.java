/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class ImageCorrige extends JComponent {

    private Controleur controleur;
    private FenetreJeu fenetre;
    private FenetreCorrige fenetreCorrige;
    private Image image;

    private int timer = 0, compteur = 0;

    public ImageCorrige(Controleur controleur, FenetreJeu fenetre, FenetreCorrige fenetreCorrige) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.fenetreCorrige = fenetreCorrige;
        this.image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveauCorrige(Jeu.DRAG_DROP, fenetre.getNiveauID()));
        this.setPreferredSize(new Dimension(controleur.getSizeImageDragDrop(fenetre.getNiveauID())[0], controleur.getSizeImageDragDrop(fenetre.getNiveauID())[1]));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
    }

}
