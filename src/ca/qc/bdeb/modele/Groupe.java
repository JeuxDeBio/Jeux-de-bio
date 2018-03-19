/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1651114
 */
public class Groupe {

    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
    private ArrayList<String> listeEtudiantsAutorisés = new ArrayList<>();
    private String information;

    public Groupe(String information) {
        this.information = information;
    }

    public ArrayList<Etudiant> getListeEtudiants() {
        return listeEtudiants;
    }

    public String getInformation() {
        return information;
    }

    public void setListeEtudiants(ArrayList<Etudiant> listeEtudiants) {
        this.listeEtudiants = listeEtudiants;
    }

    private void creerEtudiant(String nom, String prenom, String DA) {
        listeEtudiantsAutorisés.add(DA + ";" + nom + ";" + prenom);
    }

    private void ajouterEtudiantsGroupe(Groupe groupe) {
        try {
            BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(groupe.getInformation()));
            for (int i = 0; i < listeEtudiantsAutorisés.size(); i++) {
                bufferedWritter.write(listeEtudiantsAutorisés.get(i));
            }
            bufferedWritter.close();
        } catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
