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
 * @author 1649904
 */
public class Niveau {

    private String locationInformation;
    private String nomNiveau;
    private String locationImage;
    private ArrayList<String> texteLue = new ArrayList<>();
    private ArrayList<String> listeQuestions = new ArrayList<>();
    private int nombreQuestions = 0;

    public Niveau(Jeu jeu, String locationInformation) {
        this.locationInformation = locationInformation;

        lectureInformation();

        getCoordonnees();
    }

    private void lectureInformation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));

            boolean renduQuestions = false;
            String ligne = bufferedReader.readLine();

            while (ligne != null) {
                if (!renduQuestions) {
                    if (ligne.equals("Questions")){
                        renduQuestions = true;
                    }
                    texteLue.add(ligne);
                    ligne = bufferedReader.readLine();
                    nombreQuestions++;
                } else {
                    listeQuestions.add(ligne);
                    ligne = bufferedReader.readLine();
                }
            }
            nombreQuestions -= 2;
            nombreQuestions /= 2;

            nomNiveau = texteLue.get(0);
            texteLue.remove(0);

            locationImage = texteLue.get(0);
            texteLue.remove(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getNom() {
        return nomNiveau;
    }

    public String getLocation() {
        return locationImage;
    }

    public int[][] getCoordonnees() {
        int[][] coordonnees = new int[nombreQuestions][2];
        try {
            for (int i = 0; i < nombreQuestions; i++) {
                for (int j = 0; j < 2; j++) {
                    coordonnees[i][j] = Integer.parseInt(texteLue.get((2 * i) + j));
                }
            }
        } catch (NumberFormatException e) {
        }
        return coordonnees;
    }
    
    public ArrayList getQuestions(){
        return listeQuestions;
    }

}
