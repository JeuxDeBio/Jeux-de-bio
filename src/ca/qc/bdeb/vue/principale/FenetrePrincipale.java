/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.vue.etudiant.FenetreStatistiqueEtudiant;
import ca.qc.bdeb.vue.professeur.MondeProfesseur;
import ca.qc.bdeb.vue.etudiant.MondeEtudiant;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.dragDrop.FenetreCreationDragDrop;
import ca.qc.bdeb.vue.professeur.FenetreCreation;
import ca.qc.bdeb.modele.TypeUtilisateur;
import ca.qc.bdeb.vue.professeur.FenetreAjoutClasse;
import ca.qc.bdeb.vue.professeur.FenetreClasses;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 *
 * @author 1649904
 */
public class FenetrePrincipale extends JFrame implements Observer {

    private Controleur controleur;
    private Modele modele;

    private MondePrincipale mondePrincipale;
    private MondeEtudiant mondeEtudiant;
    private MondeProfesseur mondeProfesseur;

    private FenetreSelection fenetreSelection;
    private FenetreJeu fenetreJeu;
    private FenetreModificationMDP fenetreModificationMDP;
    private FenetreModificationIcone fenetreModificationIcone;
    private FenetreModification fenetreModification;
    private FenetreStatistiqueEtudiant fenetreStatistiquesEtudiant;
    private FenetreCreation fenetreCreation;
    private FenetreCreationDragDrop fenetreCreationDragDrop;
    private FenetreInscription fenetreInscription;
    private FenetreAjoutClasse fenetreAjoutClasse;
    private FenetreClasses fenetreClasses;

    private boolean logIn = false;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetrePrincipale(Controleur controleur, Observable observable) {
        modele = (Modele) observable;
        modele.addObserver(this);
        this.controleur = controleur;

        creerInterface();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
                fermerApp();
            }

        });

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (controleur.logInEtudiant()) {
            logInEtudiant();
        } else if (controleur.logInProfesseur()) {
            logInProfesseur();
        } else {
            lblErrorLog.setText(controleur.getLogInErrorLog());
            mondePrincipale.reset();
        }
    }

    private void creerInterface() {
        this.mondePrincipale = new MondePrincipale(modele, controleur, this);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Jeux de bio!");
        this.setResizable(false);
        this.add(mondePrincipale);

        this.add(lblErrorLog, BorderLayout.SOUTH);
    }

    public void ouvrirFenetreSelectionJeu(Jeu jeu, String action) {
        this.fenetreSelection = new FenetreSelection(modele, controleur, this, jeu, fenetreJeu, action, fenetreModification);
        fenetreSelection.setLocation(this.getX() + (this.getWidth() - fenetreSelection.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreSelection.getHeight()) / 2);
    }

    public void ouvrirFenetreCreation(Jeu jeu) {
        this.fenetreCreation = new FenetreCreation(controleur, modele, this, jeu, fenetreCreationDragDrop);
        fenetreCreation.setLocation(this.getX() + (this.getWidth() - fenetreCreation.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreCreation.getHeight()) / 2);
    }

    public void ouvrirFenetreStatistiquesEtudiant() {
        this.fenetreStatistiquesEtudiant = new FenetreStatistiqueEtudiant(controleur, this);
        fenetreStatistiquesEtudiant.setLocation(this.getX() + (this.getWidth() - fenetreStatistiquesEtudiant.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreStatistiquesEtudiant.getHeight()) / 2);
    }

    public void ouvrirFenetreModificationMDP() {
        fenetreModificationMDP = new FenetreModificationMDP(controleur, this);
        fenetreModificationMDP.setLocation(this.getX() + (this.getWidth() - fenetreModificationMDP.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreModificationMDP.getHeight()) / 2);
    }

    public void ouvrirFenetreModificationIcone() {
        fenetreModificationIcone = new FenetreModificationIcone(controleur, this);
        fenetreModificationIcone.setLocation(this.getX() + (this.getWidth() - fenetreModificationIcone.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreModificationIcone.getHeight()) / 2);
    }

    public void ouvrirFenetreInscription(TypeUtilisateur type) {
        fenetreInscription = new FenetreInscription(this, controleur, type);
        fenetreInscription.setLocation(this.getX() + (this.getWidth() - fenetreInscription.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreInscription.getHeight()) / 2);
    }

    public void ouvrirFenetreAjoutClasses() {
        fenetreAjoutClasse = new FenetreAjoutClasse(this, controleur);
        fenetreAjoutClasse.setLocation(this.getX() + (this.getWidth() - fenetreAjoutClasse.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreAjoutClasse.getHeight()) / 2);
    }

    public void ouvrirFenetreClasses(Groupe groupe) {
        fenetreClasses = new FenetreClasses(controleur, this, groupe);
        fenetreClasses.setLocation(this.getX() + (this.getWidth() - fenetreClasses.getWidth()) / 2, this.getY() + (this.getHeight() - fenetreClasses.getHeight()) / 2);
    }

    public void fermerFenetreSelection() {
        this.fenetreSelection.dispose();
    }

    public void fermerFenetreModificationMDP() {
        fenetreModificationMDP.dispose();
    }

    public void fermerFenetreInscription() {
        fenetreInscription.dispose();
    }

    public void fermerFenetreAjoutClasses() {
        fenetreAjoutClasse.dispose();
    }

    public void fermerFenetreModificationIcone() {
        fenetreModificationIcone.dispose();
        updateFenetre();
    }

    public void fermerFenetreClasses() {
        fenetreClasses.dispose();
    }

    public void logInEtudiant() {
        this.mondePrincipale.reset();
        this.remove(mondePrincipale);
        this.mondeEtudiant = new MondeEtudiant(controleur, this);
        this.add(mondeEtudiant);
        this.lblErrorLog.setText("");
        this.pack();
        this.validate();
        this.repaint();
        this.logIn = true;
    }

    public void logInProfesseur() {
        this.mondePrincipale.reset();
        this.remove(mondePrincipale);
        this.mondeProfesseur = new MondeProfesseur(controleur, this);
        this.add(mondeProfesseur);
        this.lblErrorLog.setText("");
        this.pack();
        this.validate();
        this.repaint();
        this.logIn = true;
    }

    public void logOutEtudiant() {
        this.setJMenuBar(null);
        this.remove(mondeEtudiant);
        this.add(mondePrincipale);
        this.lblErrorLog.setText(" ");
        this.pack();
        this.validate();
        this.repaint();
        this.logIn = false;
    }

    public void logOutProfesseur() {
        this.setJMenuBar(null);
        this.remove(mondeProfesseur);
        this.add(mondePrincipale);
        this.lblErrorLog.setText(" ");
        this.pack();
        this.validate();
        this.repaint();
        this.logIn = false;
    }

    public void finJeu() {
        if (logIn) {
            mondeEtudiant.finJeu();
        } else {
            mondePrincipale.finJeu();
        }
    }

    public void addMenuBar(JMenuBar mnuBar) {
        this.setJMenuBar(mnuBar);
    }

    private void fermerApp() {
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter?", "Fermer l'application?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            controleur.fermerApp();
            System.exit(0);
        }
    }

    public void updateFenetre() {
        if (controleur.logInEtudiant()) {
            logOutEtudiant();
            logInEtudiant();
        } else if (controleur.logInProfesseur()) {
            logOutProfesseur();
            logInProfesseur();
        }
    }
}
