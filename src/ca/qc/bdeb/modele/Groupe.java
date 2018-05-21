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
public class Groupe {

    private Modele modele;

    private String code;

    private Professeur professeur;

    private String information;

    private boolean informationsModifies = false;

    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();

    private String host = "jdbc:derby://localhost:1527/Jeux de bio DB";
    private String uName = "JeuxDeBio";
    private String uPass = "mot_de_passe0";

    public Groupe(String information, Modele modele, Professeur professeur) {
        this.information = information;
        this.modele = modele;
        this.professeur = professeur;
        lectureInformation();
    }

    private void lectureInformation() {
        System.out.println(information);
        try {
            String SQL = "SELECT * FROM GROUPE";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString("CODE").equals(information)) {
                    String[] splitDa = rs.getString("LISTEETUDIANTS").split(";");
                    for (int i = 0; i < modele.getListeEtudiants().size(); i++) {
                        for (int j = 0; j < splitDa.length; j++) {
                            if (splitDa[j].equals(modele.getListeEtudiants().get(i).getDa())) {
                                modele.getListeEtudiants().get(i).setGroupe(this);
                                modele.getListeEtudiants().get(i).setProfesseur(professeur);
                                listeEtudiants.add(modele.getListeEtudiants().get(i));
                            }
                        }
                    }
                }

            }
            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
//            String ligne = bufferedReader.readLine();
//            code = ligne;
//            ligne = bufferedReader.readLine();
//            while (ligne != null) {
//                for (int i = 0; i < modele.getListeEtudiants().size(); i++) {
//                    if (ligne.equals(modele.getListeEtudiants().get(i).getDa())) {
//                        modele.getListeEtudiants().get(i).setGroupe(this);
//                        modele.getListeEtudiants().get(i).setProfesseur(professeur);
//                        listeEtudiants.add(modele.getListeEtudiants().get(i));
//                    }
//                }
//                ligne = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Groupe.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Groupe.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public String getCode() {
        return code;
    }

    public String getInformation() {
        return information;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        listeEtudiants.add(etudiant);
        informationsModifies = true;
    }

    public void enleverEtudiant(Etudiant etudiant) {
        listeEtudiants.remove(etudiant);
        informationsModifies = true;
    }

    public boolean informationsModifies() {
        return informationsModifies;
    }

    public void updateFichierGroupe() {

        try {
            String SQL = "SELECT * FROM GROUPE";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(SQL);
            String etudiants = "";
            for (int i = 0; i < listeEtudiants.size(); i++) {
                etudiants += listeEtudiants.get(i);
                if (i < listeEtudiants.size() - 1) {
                    etudiants += ";";
                }
            }
            while (rs.next()) {
                if (rs.getString("CODE").equals(information)) {
                    rs.updateString("LISTEETUDIANTS", etudiants);
                }
            }
            stmt.close();
            rs.close();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

//        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(information));
//            bufferedWriter.write(code);
//            bufferedWriter.newLine();
//            for (int i = 0; i < listeEtudiants.size(); i++) {
//                bufferedWriter.write(listeEtudiants.get(i).getDa());
//                bufferedWriter.newLine();
//            }
//            bufferedWriter.close();
//
//        } catch (IOException ex) {
//            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public ArrayList<Etudiant> getListeEtudiants() {
        return listeEtudiants;
    }

}
