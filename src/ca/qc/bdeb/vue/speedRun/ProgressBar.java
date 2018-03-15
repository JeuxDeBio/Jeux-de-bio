/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.speedRun;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author 1649904
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
