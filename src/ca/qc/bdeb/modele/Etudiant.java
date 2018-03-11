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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1649904
 */
public class Etudiant extends Utilisateur {

    private String informations;

    private int[][] statistiquesDragDrop = new int[10][2];

    public Etudiant(String da, String motDePasse, String nom, String prenom, String informations) {
        super(da, motDePasse, nom, prenom);
        this.informations = informations;

        loadInformationsDragDrop();

        //afficher();
    }

    private void loadInformationsDragDrop() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(informations));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                if (ligne.equals("Statistiques Drag & Drop")) {
                    ligne = bufferedReader.readLine();
                    String[] splitNiveaux = ligne.split("_");
                    for (int i = 0; i < splitNiveaux.length; i++) {
                        String[] splitInformation = splitNiveaux[i].split(";");
                        for (int j = 0; j < 2; j++) {
                            statistiquesDragDrop[i][j] = Integer.parseInt(splitInformation[j]);
                        }
                    }
                }
                ligne = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMeilleursScoresDragDrop(int i, int nouveauScore) {
        if (nouveauScore > statistiquesDragDrop[i][0]) {
            statistiquesDragDrop[i][0] = nouveauScore;
        }
    }

    public void updateMeilleursTempsDragDrop(int i, int nouveauTemps) {
        if (nouveauTemps > statistiquesDragDrop[i][0]) {
            statistiquesDragDrop[i][0] = nouveauTemps;
        }
    }

    private void afficher() {
        for (int i = 0; i < statistiquesDragDrop.length; i++) {
            for (int j = 0; j < statistiquesDragDrop[i].length; j++) {
                System.out.print(statistiquesDragDrop[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private String saveInformationsDragDrop() {
        String aReturn = "";

        for (int i = 0; i < statistiquesDragDrop.length - 1; i++) {
            aReturn += statistiquesDragDrop[i][0] + ";" + statistiquesDragDrop[i][1] + "_";
        }

        aReturn += statistiquesDragDrop[statistiquesDragDrop.length - 1][0] + ";" + statistiquesDragDrop[statistiquesDragDrop.length - 1][1];

        System.out.println(aReturn);
        return aReturn;
    }

    public void saveInformations() {
        String infoDragDrop = saveInformationsDragDrop();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(informations));
            bufferedWriter.write(super.getDa());
            bufferedWriter.newLine();
            bufferedWriter.write(super.getMotDePasse());
            bufferedWriter.newLine();
            bufferedWriter.write(super.getNom());
            bufferedWriter.newLine();
            bufferedWriter.write(super.getPrenom());
            bufferedWriter.newLine();
            bufferedWriter.write("Statistiques Drag & Drop");
            bufferedWriter.newLine();
            bufferedWriter.write(infoDragDrop);
            bufferedWriter.newLine();
            bufferedWriter.write("Statistiques Shooter");
            bufferedWriter.newLine();
            bufferedWriter.write("0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0");
            bufferedWriter.newLine();
            bufferedWriter.write("Statistiques Coureur");
            bufferedWriter.newLine();
            bufferedWriter.write("0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0");
            bufferedWriter.newLine();
            bufferedWriter.write("Statistiques Speed Run");
            bufferedWriter.newLine();
            bufferedWriter.write("0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0_0;0");
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
