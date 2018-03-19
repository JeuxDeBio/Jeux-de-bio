/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1651114
 */
public class Professeur extends Utilisateur {
    private String session;
    
    
    public Professeur(String session, String da, String motDePasse, String nom, String prenom) {
        super(da, motDePasse, nom, prenom);
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
    
    private Groupe creerGroupe(String nom){
        Groupe groupe = new Groupe("Utilisateurs\\Professeurs\\"+nom +".txt");
        try{
        BufferedWriter bufferedWritter = new BufferedWriter (new FileWriter(groupe.getInformation()));
        }
        catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupe;
    }
    
    
    
}
