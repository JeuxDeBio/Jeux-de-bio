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

    private ArrayList<String> listeCoordonneesDragDrop = new ArrayList<>();
    private ArrayList<String> listeQuestionsDragDrop = new ArrayList<>();
    
    private ArrayList<String> listePositionReponsesCoureur = new ArrayList<>();
    private ArrayList<String> listeQuestionsCoureur = new ArrayList<>();
    private ArrayList<String[]> listeReponseCoureur = new ArrayList<>();
    
    private ArrayList<String> listeQuestionsSpeedRun = new ArrayList<>();
    private ArrayList<String> listeReponsesSpeedRun = new ArrayList<>();
    
    private int nombreQuestionsDragDrop = 0;
    private int score = 0;

    public Niveau(Jeu jeu, String locationInformation) {
        this.locationInformation = locationInformation;

        switch (jeu) {
            case DRAG_DROP:
                lectureInformationDragDrop();
                break;
            case SHOOTER:
                lectureInformationShooter();
                break;
            case COUREUR:
                lectureInformationCoureur();
                break;
            case SPEED_RUN:
                lectureInformationSpeedRun();
        }

    }

    private void lectureInformationDragDrop() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));

            boolean renduQuestions = false;
            String ligne = bufferedReader.readLine();

            nomNiveau = ligne;
            ligne = bufferedReader.readLine();
            locationImage = ligne;
            ligne = bufferedReader.readLine();

            while (ligne != null) {
                if (!renduQuestions) {
                    if (ligne.equals("Questions")) {
                        renduQuestions = true;
                    }
                    listeCoordonneesDragDrop.add(ligne);
                    ligne = bufferedReader.readLine();
                } else {
                    listeQuestionsDragDrop.add(ligne);
                    ligne = bufferedReader.readLine();
                    nombreQuestionsDragDrop++;
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void lectureInformationShooter() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));

            String ligne = bufferedReader.readLine();

            nomNiveau = ligne;

            ligne = bufferedReader.readLine();
            while (ligne != null) {

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lectureInformationCoureur(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));
            
            String ligne = bufferedReader.readLine();

            nomNiveau = ligne;
            ligne = bufferedReader.readLine();
            //locationImage = ligne;
            //ligne = bufferedReader.readLine();
            while (ligne != null) {
                listePositionReponsesCoureur.add(ligne);
                ligne = bufferedReader.readLine();
                String split[] = ligne.split(":");
                listeQuestionsCoureur.add(split[1]);
                split = split[0].split(";");
                listeReponseCoureur.add(split);
                
                ligne = bufferedReader.readLine();
               
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lectureInformationSpeedRun() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));
            boolean renduReponses = false;
            String ligne = bufferedReader.readLine();

            nomNiveau = ligne;

            ligne = bufferedReader.readLine();
            while (ligne != null) {
                if (!renduReponses) {
                    if (ligne.equals("Reponses")) {
                        renduReponses = true;
                    }
                    listeQuestionsSpeedRun.add(ligne);
                    ligne = bufferedReader.readLine();
                } else {
                    listeReponsesSpeedRun.add(ligne);
                    ligne = bufferedReader.readLine();
                }
            }

            listeQuestionsSpeedRun.remove(listeQuestionsSpeedRun.size() - 1);
            
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
        int[][] coordonnees = new int[nombreQuestionsDragDrop][2];
        try {
            for (int i = 0; i < nombreQuestionsDragDrop; i++) {
                for (int j = 0; j < 2; j++) {
                    coordonnees[i][j] = Integer.parseInt(listeCoordonneesDragDrop.get((2 * i) + j));
                }
            }
        } catch (NumberFormatException e) {
        }
        return coordonnees;
    }

    public ArrayList getQuestions() {
        return listeQuestionsDragDrop;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public ArrayList getPositionReponsesCoureur(){
        return listePositionReponsesCoureur;
    }
    
    public ArrayList getReponsesCoureur(){
        return listeReponseCoureur;
    }
    
    public ArrayList getQuestionCoureur(){
        return listeQuestionsCoureur;
    }
}
