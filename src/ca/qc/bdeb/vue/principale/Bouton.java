//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc 
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class Bouton extends JComponent {

    public Bouton() {
    }

    public Bouton(Controleur controleur, int index, Jeu jeu) {
        String[] split = controleur.getNomNiveau(jeu, index).split("");
        for (int i = 0; i < split.length; i++) {
            JLabel lblTexteBouton = new JLabel(split[i], JLabel.CENTER);
            lblTexteBouton.setSize(130, 20);
            lblTexteBouton.setLocation(0, ((84 - (lblTexteBouton.getHeight() * split.length)) * i) + 10);
            this.add(lblTexteBouton);
        }
    }

    public Bouton(String texte) {
        JLabel lblTexteBouton = new JLabel(texte, JLabel.CENTER);
        lblTexteBouton.setSize(258, 20);
        lblTexteBouton.setLocation(10, 25);
        this.add(lblTexteBouton);
    }

    public boolean existe(Controleur controleur, int i, Jeu jeu) {
        return !(controleur.getNomNiveau(jeu, i).equals("À venir"));
    }
}
