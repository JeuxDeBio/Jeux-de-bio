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
 * @author 1649904
 */
public class Niveau {

    private String locationInformation;
    private String nomNiveau;
    private String locationImage;
    private String locationImageCorrige;
    private String IndexQuestion;
    private int ID;

    private ArrayList<int[]> listeCoordonneesDragDrop = new ArrayList<>();
    private ArrayList<String> listeQuestionsDragDrop = new ArrayList<>();
    private int[] sizeImageDragDrop = new int[2];

    private ArrayList<Integer> listePositionReponsesCoureur = new ArrayList<>();
    private ArrayList<String> listeQuestionsCoureur = new ArrayList<>();
    private ArrayList<String[]> listeReponseCoureur = new ArrayList<>();

    private ArrayList<String> listeQuestionsSpeedRun = new ArrayList<>();
    private ArrayList<String> listeReponsesSpeedRun = new ArrayList<>();

    private int score = 0;
    private String host = "jdbc:derby://localhost:1527/Jeux de bio DB";
    private String uName = "JeuxDeBio";
    private String uPass = "mot_de_passe0";

    public Niveau(Jeu jeu, String locationInformation) {
        this.locationInformation = locationInformation;

        switch (jeu) {
            case DRAG_DROP:
                lectureInformationDragDrop();
                break;
            case SHOOTER:

                break;
            case COUREUR:
                lectureInformationCoureur();
                break;
            case SPEED_RUN:
                lectureInformationSpeedRun();
        }

    }

    public Niveau(Jeu jeu, int ID) {
        this.ID = ID;

        switch (jeu) {
            case DRAG_DROP:
                lectureInformationDragDrop();
                break;
            case SHOOTER:

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

            String SQL = "SELECT * FROM NIVEAUDRAGDROP";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getInt("ID") == ID) {
                    nomNiveau = rs.getString("NOM");
                    
                    locationImage = rs.getString("LOCATIONIMAGE");
                    locationImageCorrige = rs.getString("LOCATIONIMAGECORRIGE");
                    String[] size = rs.getString("GRANDEURIMAGE").split(";");
                    for (int i = 0; i < size.length; i++) {
                        sizeImageDragDrop[i] = Integer.parseInt(size[i]);
                    }
                    IndexQuestion = rs.getString("INDEXQUESTION");
                }
            }

            stmt.close();
            rs.close();
            
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.createStatement();
            SQL = "SELECT * FROM QUESTIONDRAGDROP";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String a = rs.getString("INDEX");
                String split[] = a.split(";");
                if (Integer.parseInt(split[0]) == ID) {

                    listeQuestionsDragDrop.add(rs.getString("TEXTE"));
                    int[] coordonneesSplit = new int[2];
                    coordonneesSplit[0] = rs.getInt("X");
                    coordonneesSplit[1] = rs.getInt("Y");
                    listeCoordonneesDragDrop.add(coordonneesSplit);
                }

            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        
//        -----------------
//                ---------------------
//                ---------------------
//                -----------------------
//                -

//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(locationInformation));
//            String ligne = bufferedReader.readLine();
//            nomNiveau = ligne;
//            ligne = bufferedReader.readLine();
//            locationImage = ligne;
//            ligne = bufferedReader.readLine();
//            locationImageCorrige = ligne;
//            ligne = bufferedReader.readLine();
//            String[] size = ligne.split(";");
//
//            for (int i = 0; i < size.length; i++) {
//                sizeImageDragDrop[i] = Integer.parseInt(size[i]);
//            }
//            ligne = bufferedReader.readLine();
//            while (ligne != null) {
//                String split[] = ligne.split(":");
//                listeQuestionsDragDrop.add(split[1]);
//                split = split[0].split(";");
//
//                int[] coordonneesSplit = new int[split.length];
//                for (int i = 0; i < coordonneesSplit.length; i++) {
//                    coordonneesSplit[i] = Integer.parseInt(split[i]);
//                }
//
//                listeCoordonneesDragDrop.add(coordonneesSplit);
//
//                ligne = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
                String split[] = ligne.split(":");
                listeQuestionsCoureur.add(split[1]);
                listePositionReponsesCoureur.add(Integer.parseInt(split[2]));
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
            String ligne = bufferedReader.readLine();

            nomNiveau = ligne;

            ligne = bufferedReader.readLine();
            while (ligne != null) {
                String[] split = ligne.split(";");
                listeQuestionsSpeedRun.add(split[0]);
                listeReponsesSpeedRun.add(split[1]);
                ligne = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Niveau.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getNom() {
        return nomNiveau;
    }

    public int getID() {
        return ID;
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

    public String getLocationInformation() {
        return locationInformation;
    }

    public ArrayList getReponsesSpeedRun() {
        return listeReponsesSpeedRun;
    }
}
