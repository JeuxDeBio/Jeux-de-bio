/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.util.ArrayList;

/**
 *
 * @author 1649904
 */
public class Etudiant extends Utilisateur {

    private String informations;

    private ArrayList<Integer> listeMeilleursScoresDragDrop = new ArrayList<>();
    private ArrayList<Integer> listeMeilleursTempsDragDrop = new ArrayList<>();

    public Etudiant(String da, String motDePasse, String nom, String prenom, String informations) {
        super(da, motDePasse, nom, prenom);
        this.informations = informations;
        
        initialize();
    }

    private void loadInformationsDragDrop() {
        
    }

    public void updateMeilleursScoresDragDrop(int i, int nouveauScore) {

    }

    public void updateMeilleursTempsDragDrop(int i, int nouveauScore) {

    }

}
