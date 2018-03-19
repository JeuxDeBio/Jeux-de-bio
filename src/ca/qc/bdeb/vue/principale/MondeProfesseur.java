/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.modele.Modele;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author 1651114
 */
public class MondeProfesseur extends JComponent{
   
    private Modele modele;

    private Image image;

    private FenetrePrincipale fenetre;
    
    public MondeProfesseur(Modele modele, FenetrePrincipale fenetre){
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);

        this.modele = modele;
        this.fenetre = fenetre;
        
    }

    
}
