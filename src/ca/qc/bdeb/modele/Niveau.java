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
    private String locationImageCorrige;

    private ArrayList<int[]> listeCoordonneesDragDrop = new ArrayList<>();
    private ArrayList<String> listeQuestionsDragDrop = new ArrayList<>();
    private int[] sizeImageDragDrop = new int[2];

    private ArrayList<Integer> listePositionReponsesCoureur = new ArrayList<>();
    private ArrayList<String> listeQuestionsCoureur = new ArrayList<>();
    private ArrayList<String[]> listeReponseCoureur = new ArrayList<>();

    private ArrayList<String> listeQuestionsSpeedRun = new ArrayList<>();
    private ArrayList<String> listeReponsesSpeedRun = new ArrayList<>();

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
            String ligne = bufferedReader.readLine();
            nomNiveau = ligne;
            ligne = bufferedReader.readLine();
            locationImage = ligne;
            ligne = bufferedReader.readLine();
            locationImageCorrige = ligne;
            ligne = bufferedReader.readLine();
            String[] size = ligne.split(";");

            for (int i = 0; i < size.length; i++) {
                sizeImageDragDrop[i] = Integer.parseInt(size[i]);
            }
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                String split[] = ligne.split(":");
                listeQuestionsDragDrop.add(split[1]);
                split = split[0].split(";");

                int[] coordonneesSplit = new int[split.length];
                for (int i = 0; i < coordonneesSplit.length; i++) {
                    coordonneesSplit[i] = Integer.parseInt(split[i]);
                }

                listeCoordonneesDragDrop.add(coordonneesSplit);

                ligne = bufferedReader.readLine();
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

    private void lectureInformationCoureur() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));

            String ligne = bufferedReader.readLine();

            nomNiveau = ligne;
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                listePositionReponsesCoureur.add(Integer.parseInt(ligne));
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

    public String getLocationCorrige() {
        return locationImageCorrige;
    }

    public int[] getSizeImageDragDrop() {
        return sizeImageDragDrop;
    }

    public ArrayList<int[]> getCoordonneesDragDrop() {
        return listeCoordonneesDragDrop;
    }

    public ArrayList getQuestionsDragDrop() {
        return listeQuestionsDragDrop;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList getPositionReponsesCoureur() {
        return listePositionReponsesCoureur;
    }

    public ArrayList getReponsesCoureur() {
        return listeReponseCoureur;
    }

    public ArrayList getQuestionCoureur() {
        return listeQuestionsCoureur;
    }

    public ArrayList getQuestionsSpeedRun() {
        return listeQuestionsSpeedRun;
    }

    public ArrayList getReponsesSpeedRun() {
        return listeReponsesSpeedRun;
    }
}
