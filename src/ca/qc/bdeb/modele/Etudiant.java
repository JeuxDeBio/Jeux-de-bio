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
    
    private ArrayList<Integer> listeMeilleursScoresSpeedRun = new ArrayList<>();

    private int currentScore = 0;

    public Etudiant(String da, String motDePasse, String nom, String prenom, String informations) {
        super(da, motDePasse, nom, prenom);
        this.informations = informations;

        initialize();
    }

    private void initialize() {
        //load les infos de letudiant plus tard
        for (int i = 0; i < 10; i++) {
            listeMeilleursScoresDragDrop.add(0);
            listeMeilleursTempsDragDrop.add(0);
            
            listeMeilleursScoresSpeedRun.add(0);
        }
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    public void setCurrentScore(Jeu jeu, int i, int currentScore, int currentTemps) {
        this.currentScore = currentScore;

        switch (jeu) {
            case DRAG_DROP:
                if (listeMeilleursScoresDragDrop.get(i) < currentScore) {
                    listeMeilleursScoresDragDrop.set(i, currentScore);
                }

                if (listeMeilleursTempsDragDrop.get(i) == 0 || listeMeilleursTempsDragDrop.get(i) > currentTemps) {
                    listeMeilleursTempsDragDrop.set(i, currentTemps);
                }
                break;

            case SPEED_RUN: 
                if (listeMeilleursScoresSpeedRun.get(i) < currentScore) {
                    listeMeilleursScoresSpeedRun.set(i, currentScore);
                }
                
        }

        System.out.println(listeMeilleursScoresDragDrop.get(i) + "  " + listeMeilleursTempsDragDrop.get(i));
    }

    public void updateMeilleursScoresDragDrop(int i, int nouveauScore) {
        listeMeilleursScoresDragDrop.set(i, nouveauScore);
    }

    public void updateMeilleursTempsDragDrop(int i, int nouveauTemps) {
        if (nouveauTemps > listeMeilleursTempsDragDrop.get(i)) {
            listeMeilleursScoresDragDrop.set(i, nouveauTemps);
        }
    }

}
