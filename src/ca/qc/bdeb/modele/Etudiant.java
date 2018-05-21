/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Batikan
 */
public class Etudiant {

    private String information;
    private Groupe groupe;
    private Professeur professeur;
    private String nom;
    private String da;
    private String motDePasse;
    private int[][] scores = new int[4][10];
    private int scoreNiveau = 0;
    private boolean informationsModifies = false;
    private String locationIcone;
    private final TypeUtilisateur type = TypeUtilisateur.ETUDIANT;
    
    private String host = "jdbc:derby://localhost:1527/Jeux de bio DB";
    private String uName = "JeuxDeBio";
    private String uPass = "mot_de_passe0";


    //    public Etudiant(String information) {
//        this.information = information;
//        lectureInformation();
//    }

    public Etudiant(String da, String mdp, String nom, String locationIcone, String scoresDragDrop, String scoresCoureur, String scoresSpeedRun) {
        this.da = da;
        this.locationIcone = locationIcone;
        this.nom = nom;
        this.motDePasse = mdp;

        traitementInfos(scoresDragDrop, scoresCoureur, scoresSpeedRun);

    }

    private void traitementInfos(String scoresDragDrop, String scoresCoureur, String scoresSpeedRun) {
        String[] split = scoresDragDrop.split(";");
        for (int j = 0; j < split.length; j++) {
            scores[0][j] = Integer.parseInt(split[j]);   
        }
        split = scoresCoureur.split(";");
        for (int j = 0; j < split.length; j++) {
            scores[1][j] = Integer.parseInt(split[j]);   
        }
        split = scoresSpeedRun.split(";");
        for (int j = 0; j < split.length; j++) {
            scores[2][j] = Integer.parseInt(split[j]);   
        }
    }

    private void lectureInformation() {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
//            String ligne = bufferedReader.readLine();
//            da = ligne;
//            ligne = bufferedReader.readLine();
//            motDePasse = ligne;
//            ligne = bufferedReader.readLine();
//            nom = ligne;
//            ligne = bufferedReader.readLine();
//            locationIcone = ligne;
//            ligne = bufferedReader.readLine();
//            int i = 0;
//            while (ligne != null) {
//                String[] split = ligne.split(";");
//                for (int j = 0; j < split.length; j++) {
//                    scores[i][j] = Integer.parseInt(split[j]);
//                }
//                i++;
//                ligne = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public String getDa() {
        return da;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public String getLocationIcone() {
        return locationIcone;
    }

    public int getScoreNiveau() {
        return scoreNiveau;
    }

    public boolean informationsModifies() {
        return informationsModifies;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public void setScore(Jeu jeu, int i, int score) {
        scoreNiveau = score;
        int jeuIndex = 0;
        switch (jeu) {
            case DRAG_DROP:
                jeuIndex = 0;
                break;
            case SHOOTER:
                jeuIndex = 1;
                break;
            case COUREUR:
                jeuIndex = 2;
                break;
            case SPEED_RUN:
                jeuIndex = 3;
        }

        if (score > scores[jeuIndex][i]) {
            scores[jeuIndex][i] = score;
            informationsModifies = true;
        }

    }

    public void setMotDePasse(String nouveauMotDePasse) {
        motDePasse = nouveauMotDePasse;
        informationsModifies = true;
    }

    public void setLocationIcone(String locationIcone) {
        this.locationIcone = locationIcone;
        informationsModifies = true;
    }

    public int[][] getScores() {
        return scores;
    }

    public void updateFichierEtudiant() {
        ArrayList<String> liste = new ArrayList<>();
        String ligne = "";
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores[i].length - 1; j++) {
                ligne += scores[i][j] + ";";
            }
            ligne += scores[i][scores[i].length - 1];
            liste.add(ligne);
            ligne = "";
        }
        
        try {
            String SQL = "SELECT * FROM ETUDIANT";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString("DA").equals(da)) {
                    rs.updateString("MOTDEPASSE", motDePasse);
                    rs.updateString("NOM", nom);
                    rs.updateString("LOCATIONICONE", locationIcone);
                    rs.updateString("SCORESDRAGDROP", liste.get(0));
                    rs.updateString("SCORESCOUREUR", liste.get(1));
                    rs.updateString("SCORESSPEEDRUN", liste.get(2));
                }

            }
            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        
        
//        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(information));
//            bufferedWriter.write(da);
//            bufferedWriter.newLine();
//            bufferedWriter.write(motDePasse);
//            bufferedWriter.newLine();
//            bufferedWriter.write(nom);
//            bufferedWriter.newLine();
//            bufferedWriter.write(locationIcone);
//            bufferedWriter.newLine();
//
//            for (String line : liste) {
//                bufferedWriter.write(line);
//                bufferedWriter.newLine();
//            }
//
//            bufferedWriter.close();
//
//        } catch (IOException ex) {
//            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    

}
