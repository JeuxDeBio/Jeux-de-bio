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

    public Professeur(String nu, String mdp, String nom, String session) {
        creerFichiers(nu, mdp, nom, session);
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
                type = TypeUtilisateur.ADMIN;
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
            bufferedReader.close();

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

            bufferedReader.close();
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

    public String getInformation() {
        return information;
    }

    public TypeUtilisateur getType() {
        return type;
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

    public void ajouterGroupe(String information) {
        Groupe groupe = new Groupe(information, modele, this);
        listeGroupes.add(groupe);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(locationDAPermis, true));
            bufferedWriter.newLine();

            for (int i = 0; i < groupe.getListeEtudiants().size(); i++) {
                bufferedWriter.write(groupe.getListeEtudiants().get(i).getDa() + ";" + groupe.getCode());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(Professeur.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            if (estAdmin) {
                bufferedWriter.write("admin");
            } else {
                bufferedWriter.write("professeur");
            }
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
        informationsModifies = true;
    }

    public void enleverAdmin() {
        this.estAdmin = false;
        this.type = TypeUtilisateur.PROFESSEUR;
        informationsModifies = true;
    }

    public boolean estAdmin() {
        return estAdmin;
    }

    public void creerFichiers(String nu, String mdp, String nom, String session) {
        locationDAPermis = "Utilisateurs\\Professeurs\\" + nu + "DAPermis.txt";
        information = "Utilisateurs\\Professeurs\\" + nu + ".txt";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(locationDAPermis));
            bufferedWriter.close();

            bufferedWriter = new BufferedWriter(new FileWriter(information));
            bufferedWriter.write(nu);
            bufferedWriter.newLine();
            bufferedWriter.write(mdp);
            bufferedWriter.newLine();
            bufferedWriter.write(nom);
            bufferedWriter.newLine();
            bufferedWriter.write("professeur");
            bufferedWriter.newLine();
            bufferedWriter.write("C:\\Users\\batik\\Desktop\\Jeux-de-bio\\Utilisateurs\\Icones\\iconeVierge.png");
            bufferedWriter.newLine();
            bufferedWriter.write(session);
            bufferedWriter.newLine();
            bufferedWriter.write(locationDAPermis);
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        for (int i = 0; i < listeGroupes.size(); i++) {
            listeGroupes.get(i).delete();
        }

        File fileDAPermis = new File(locationDAPermis);
        File fileInformations = new File(information);

        fileDAPermis.delete();
        fileInformations.delete();
    }
}
