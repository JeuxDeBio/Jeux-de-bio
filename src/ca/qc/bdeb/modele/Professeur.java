/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Batikan
 */
public class Professeur {

    private Modele modele;

    private String information;
    private String nom;
    private String nomUtilisateur;
    private String motDePasse;
    private String session;

    private ArrayList<String> listeDAPermis = new ArrayList<>();
    private ArrayList<Groupe> listeGroupes = new ArrayList<>();

    public Professeur(String information, Modele modele) {
        this.information = information;
        this.modele = modele;
        lectureInformation();
    }

    private void lectureInformation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
            String ligne = bufferedReader.readLine();
            nomUtilisateur = ligne;
            ligne = bufferedReader.readLine();
            motDePasse = ligne;
            ligne = bufferedReader.readLine();
            nom = ligne;
            ligne = bufferedReader.readLine();
            session = ligne;
            ligne = bufferedReader.readLine();
            lectureDAPermis(ligne);
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeGroupes.add(new Groupe(ligne, modele, this));
                ligne = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lectureDAPermis(String information) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeDAPermis.add(ligne);
                ligne = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getSession() {
        return session;
    }
    
    public ArrayList<Groupe> getListeGroupes() {
        return listeGroupes;
    }
    
    public boolean etudiantPermis(String da) {
        boolean etudiantPermis = false;
        for (int i = 0; i < listeDAPermis.size(); i++) {
            if (da.equals(listeDAPermis.get(i))) {
                etudiantPermis = true;
            }
        }
        return etudiantPermis;
    }
}
