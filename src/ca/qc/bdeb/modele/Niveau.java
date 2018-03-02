/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

/**
 *
 * @author 1649904
 */
public class Niveau {

    Jeu jeu;
    String nom;

    public Niveau(Jeu jeu, String nom) {
        this.jeu = jeu;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

}
