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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Batikan
 */
public class Professeur {

    private Modele modele;

    private String information;
    private String nom;
    private String nomUtilisateur;
    private String motDePasse;
    private String locationIcone;
    private String session;

    private String locationDAPermis;

    private ArrayList<String> listeDAPermis = new ArrayList<>();
    private ArrayList<Groupe> listeDAPermisGroupe = new ArrayList<>();
    private ArrayList<Groupe> listeGroupes = new ArrayList<>();

    private boolean informationsModifies = false;

    private TypeUtilisateur type = TypeUtilisateur.PROFESSEUR;

    private boolean estAdmin = false;

    public Professeur(String information, Modele modele) {
        this.information = information;
        this.modele = modele;
        lectureInformation();
    }

    private void lectureInformation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
            String ligne = bufferedReader.readLine();
            nomUtilisateur = ligne;
            ligne = bufferedReader.readLine();
            motDePasse = ligne;
            ligne = bufferedReader.readLine();
            nom = ligne;
            ligne = bufferedReader.readLine();
            if (ligne.equals("admin")) {
                estAdmin = true;
            }
            ligne = bufferedReader.readLine();
            locationIcone = ligne;
            ligne = bufferedReader.readLine();
            session = ligne;
            ligne = bufferedReader.readLine();
            locationDAPermis = ligne;
            ligne = bufferedReader.readLine();
            while (ligne != null) {
                listeGroupes.add(new Groupe(ligne, modele, this));
                ligne = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }

        lectureDAPermis(locationDAPermis);
    }

    private void lectureDAPermis(String information) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(information));
            String ligne = bufferedReader.readLine();
            while (ligne != null) {
                String[] split = ligne.split(";");
                listeDAPermis.add(split[0]);

                for (int i = 0; i < listeGroupes.size(); i++) {
                    if (listeGroupes.get(i).getCode().equals(split[1])) {
                        listeDAPermisGroupe.add(listeGroupes.get(i));
                    }
                }

                ligne = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getLocationIcone() {
        return locationIcone;
    }

    public String getSession() {
        return session;
    }

    public ArrayList<Groupe> getListeGroupes() {
        return listeGroupes;
    }

    public boolean etudiantPermis(String da) {
        boolean etudiantPermis = false;
        for (int i = 0; i < listeDAPermis.size(); i++) {
            if (da.equals(listeDAPermis.get(i))) {
                etudiantPermis = true;
            }
        }
        return etudiantPermis;
    }

    public Groupe getGroupeNouveauEtudiant(String da) {
        return listeDAPermisGroupe.get(listeDAPermis.indexOf(da));
    }

    public void setMotDePasse(String nouveauMDP) {
        motDePasse = nouveauMDP;
        informationsModifies = true;
    }

    public void setLocationIcone(String locationIcone) {
        this.locationIcone = locationIcone;
        informationsModifies = true;
    }

    public void updateFichierProfesseur() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(information));
            bufferedWriter.write(nomUtilisateur);
            bufferedWriter.newLine();
            bufferedWriter.write(motDePasse);
            bufferedWriter.newLine();
            bufferedWriter.write(nom);
            bufferedWriter.newLine();
            bufferedWriter.write(locationIcone);
            bufferedWriter.newLine();
            bufferedWriter.write(session);
            bufferedWriter.newLine();
            bufferedWriter.write(locationDAPermis);
            bufferedWriter.newLine();

            for (Groupe groupe : listeGroupes) {
                bufferedWriter.write(groupe.getInformation());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean informationsModifies() {
        return informationsModifies;
    }

    public void setAdmin() {
        this.estAdmin = true;
        this.type = TypeUtilisateur.ADMIN;
    }

    public void enleverAdmin() {
        this.estAdmin = false;
        this.type = TypeUtilisateur.PROFESSEUR;
    }

    public boolean estAdmin() {
        return estAdmin;
    }
}
