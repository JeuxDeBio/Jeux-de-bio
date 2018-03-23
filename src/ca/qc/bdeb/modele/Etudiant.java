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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Batikan
 */
public class Etudiant {

    private String information;
    private Groupe groupe;
    private String nom;
    private String da;
    private String motDePasse;

    public Etudiant(String information) {
        this.information = information;
        lectureInformation();
    }

    private void lectureInformation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
            String ligne = bufferedReader.readLine();
            da = ligne;
            ligne = bufferedReader.readLine();
            motDePasse = ligne;
            ligne = bufferedReader.readLine();
            nom = ligne;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDa(){
        return da;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return da;
    }
    
    

}
