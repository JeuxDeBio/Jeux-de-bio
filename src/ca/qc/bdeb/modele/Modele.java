/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.modele;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author 1649904
 */
public class Modele extends Observable {

    private final String locationFenetrePrincipale = "Ecrans\\Fenetre_principale.png";
    private final String locationFenetrePrincipaleLogIn = "Ecrans\\Fenetre_principale_logIn.png";
    private final String locationFenetreSelection = "Ecrans\\Fenetre_selection.png";

    ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxDragDrop = new ArrayList<>();
    ArrayList<Niveau> listeNiveauxShooter = new ArrayList<>();

    Utilisateur utilisateur = new Utilisateur();

    private boolean logIn = false;

    public Modele() {
        listeUtilisateurs.add(new Utilisateur("123", "123", "Adam", "Adam"));
        listeUtilisateurs.add(new Utilisateur("456", "456", "Bob", "Bob"));
        listeUtilisateurs.add(new Utilisateur("789", "789", "Chris", "Chris"));

        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "niveau 1"));
        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "niveau 2"));
        listeNiveauxDragDrop.add(new Niveau(Jeu.DRAG_DROP, "niveau 3"));

        listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, "niveau 1"));
        listeNiveauxShooter.add(new Niveau(Jeu.SHOOTER, "niveau 2"));
    }

    public String getLocationFenetrePrincipale() {
        return locationFenetrePrincipale;
    }

    public String getLocationFenetrePrincipaleLogIn() {
        return locationFenetrePrincipaleLogIn;
    }

    public String getLocationFenetreSelection() {
        return locationFenetreSelection;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void validerUtilisateur(String da, char[] motdepasse) {
        for (int i = 0; i < listeUtilisateurs.size(); i++) {
            if (da.equals(listeUtilisateurs.get(i).getDa())) {
                String motDePasse = "";
                for (int j = 0; j < motdepasse.length; j++) {
                    motDePasse += motdepasse[j];
                }
                if (motDePasse.equals(listeUtilisateurs.get(i).getMotDePasse())) {
                    this.utilisateur = listeUtilisateurs.get(i);
                    this.logIn = true;
                    majObserver();
                } else {
                }
            } else {
            }
        }

    }

    public boolean logIn() {
        return logIn;
    }

    public void logOut() {
        this.logIn = false;
        this.utilisateur = new Utilisateur();
    }

    public String getNomNiveau(Jeu jeu, int i) {
        String nomNiveau = "À venir";

        switch (jeu) {
            case DRAG_DROP:
                if (i < listeNiveauxDragDrop.size()) {
                    nomNiveau = listeNiveauxDragDrop.get(i).getNom();
                }
                break;
            case SHOOTER:
                if (i < listeNiveauxShooter.size()) {
                    nomNiveau = listeNiveauxShooter.get(i).getNom();
                }
        }

        return nomNiveau;
    }

    public void majObserver() {
        setChanged();
        notifyObservers();
    }

}
