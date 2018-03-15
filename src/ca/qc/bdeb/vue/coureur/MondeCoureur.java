/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Niopo
 */
public class MondeCoureur extends JComponent {

    private Modele modele;
    private Controleur controleur;
    private FenetreJeu fenetre;

    private JLabel lblQuestion;

    private final int largeur = 800, hauteur = 700;
    private int distanceEntreBoites;

    private int compteur = 0;

    private ArrayList<String> listeQuestions = new ArrayList();
    private ArrayList<String[]> listeChoix = new ArrayList();
    private ArrayList<Integer> listePositionsReponses = new ArrayList<>();
    private ArrayList<BoiteChoix> listeChoixEnCours = new ArrayList<>();

    private Joueur joueur;

    private Thread thread = new Thread() {

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.

            //randomize listeQuestions
            afficherQuestion();
            while (true) {
                joueur.requestFocus();
                choisirInitialChoix();
                bougerBoites();
                getChoixJoueur();
                verifierReponse();

                invalidate();
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    };

    public MondeCoureur(JLabel lblQuestion, FenetreJeu fenetre, Controleur controleur, Modele modele) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.fenetre = fenetre;
        this.controleur = controleur;
        this.modele = modele;

        this.lblQuestion = lblQuestion;

        this.creerInterface();
        this.creerEvenements();

        this.thread.start();
    }

    private void creerInterface() {
        listeQuestions = controleur.getQuestionsCoureur(fenetre.getNiveauID());
        listeChoix = controleur.getReponsesCoureur(fenetre.getNiveauID());
        listePositionsReponses = controleur.getPositionQuestions(fenetre.getNiveauID());

        joueur = new Joueur();
        joueur.setLocation((largeur - joueur.getWidth()) / 2, hauteur - joueur.getHeight() - 25);
        this.add(joueur);
    }

    private void creerEvenements() {
        joueur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (joueur.getX() + joueur.getChoix().getLargeur() + distanceEntreBoites < largeur) {
                            bougerDroite();
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (joueur.getX() - joueur.getChoix().getLargeur() - distanceEntreBoites > 0) {
                            bougerGauche();
                        }
                }
            }
        });
    }

    private void choisirInitialChoix() {
        if (listeChoix.get(compteur).length % 2 == 0) {
            joueur.setChoix(listeChoixEnCours.get((listeChoixEnCours.size() / 2) - 1));
        } else {
            joueur.setChoix(listeChoixEnCours.get((listeChoixEnCours.size() - 1) / 2));
        }
    }

    private void bougerGauche() {
        joueur.setLocation(joueur.getX() - joueur.getChoix().getLargeur() - distanceEntreBoites, joueur.getY());
    }

    private void bougerDroite() {
        joueur.setLocation(joueur.getX() + joueur.getChoix().getLargeur() + distanceEntreBoites, joueur.getY());
    }

    private void afficherQuestion() {
        lblQuestion.setSize(700, 100);
        lblQuestion.setLocation(25, 25);
        this.add(lblQuestion);

        lblQuestion.setText(listeQuestions.get(compteur));

        for (int i = 0; i < listeChoix.get(compteur).length; i++) {
            BoiteChoix boiteChoix = new BoiteChoix(listeChoix.get(compteur)[i], listePositionsReponses.get(compteur));
            distanceEntreBoites = (largeur - (listeChoix.get(compteur).length * boiteChoix.getLargeur())) / (listeChoix.get(compteur).length + 1);
            boiteChoix.setLocation((boiteChoix.getLargeur() + distanceEntreBoites) * i + distanceEntreBoites, 100);
            this.add(boiteChoix);
            listeChoixEnCours.add(boiteChoix);
        }
    }

    private void bougerBoites() {
        for (BoiteChoix boite : listeChoixEnCours) {
            boite.setLocation(boite.getX(), boite.getY() + 1);
        }
    }

    private void getChoixJoueur() {
        for (BoiteChoix boite : listeChoixEnCours) {
            if (boite.getBounds().intersects(joueur.getBounds())) {
                joueur.setChoix(boite);
                joueur.faitchoixTrue();
            }
        }
    }

    private void verifierReponse() {
        if (joueur.faitChoix()) {
            if (joueur.getChoix().getPosition() == listePositionsReponses.get(compteur)) {
                System.out.println("bonne reponse");
            }
        }
    }

}
