//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.coureur;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * La barre de progres
 *
 * @author Batikan
 */
public class ProgressBar extends JComponent {

    private ArrayList<Case> listeCases = new ArrayList<>();

    private int largeur, hauteur;
    private int nombreCases;
    private int progres = 0;

    public ProgressBar(ArrayList liste) {
        this.nombreCases = liste.size();

        for (int i = 0; i < nombreCases; i++) {
            Case caseProgres = new Case();
            caseProgres.setLocation(i * caseProgres.getWidth(), 0);
            this.add(caseProgres);
            listeCases.add(caseProgres);
            largeur = caseProgres.getWidth() * nombreCases;
            hauteur = caseProgres.getHeight();
        }

        this.setSize(largeur, hauteur);

    }

    public int getLargeur() {
        return largeur;
    }

    /**
     * Ajoute un case dans le progres
     */
    public void ajouterProgres() {
        listeCases.get(progres).affiche();
        progres++;
    }

    public int getProgres() {
        return progres;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, largeur - 1, hauteur - 1);

    }

}
