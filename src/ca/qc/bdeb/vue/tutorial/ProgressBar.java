/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.tutorial;

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

    public ProgressBar(int nombreCases) {
        this.nombreCases = nombreCases;

        for (int i = 0; i < nombreCases; i++) {
            Case caseProgress = new Case();
            caseProgress.setLocation(i * caseProgress.getWidth(), 0);
            this.add(caseProgress);
            listeCases.add(caseProgress);
            largeur = caseProgress.getWidth() * nombreCases;
            hauteur = caseProgress.getHeight();
        }

        this.setSize(largeur, hauteur);
    }

    public void setCase(int indice) {
        for (int i = 0; i < listeCases.size(); i++) {
            listeCases.get(i).pasAffiche();
        }

        listeCases.get(indice).affiche();
    }

    public int getLargeur() {
        return largeur;
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
