/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    private ArrayList<JLabel> listeReponses = new ArrayList<>();

    private Joueur joueur;

    private int[] index;

    private boolean finJeu = false;
    private boolean debutTour = true;

    private ProgressBar progressBar;

    private Thread thread = new Thread() {

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.

            randomOrdre();

            while (!finJeu) {
                joueur.requestFocus();

                if (debutTour) {
                    debutTour();
                }

                bougerBoites();
                collision();

                if (joueur.faitChoix()) {
                    if (verifierReponse()) {
                        finTour();
                        finJeu = !(compteur < listeQuestions.size());
                    } else {
                        finJeu = true;
                    }
                }

                invalidate();
                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
            }

            finJeu();

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

        joueur = new Joueur(controleur);
        joueur.setLocation((largeur - joueur.getWidth()) / 2, hauteur - joueur.getHeight() - 25);
        this.add(joueur);

        progressBar = new ProgressBar(listeQuestions);
        progressBar.setLocation((this.largeur - progressBar.getLargeur()) / 2, 50);
        this.add(progressBar);
        
        JLabel lbl = new JLabel("Choix possibles", JLabel.CENTER);
        lbl.setSize(largeur, 20);
        lbl.setLocation(0, 100);
        this.add(lbl);
    }

    private void creerEvenements() {
        joueur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
                if (!joueur.faitChoix()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            if (joueur.getX() + listeChoixEnCours.get(0).getLargeur() + distanceEntreBoites < largeur) {
                                bougerDroite();
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (joueur.getX() - listeChoixEnCours.get(0).getLargeur() - distanceEntreBoites > 0) {
                                bougerGauche();
                            }
                    }
                }
            }
        });
    }

    private void randomOrdre() {
        index = new int[listeQuestions.size()];

        Random random = new Random();

        for (int i = 0; i < listeQuestions.size(); i++) {
            index[i] = i;
        }

        int index1, index2, temporaire;

        for (int i = 0; i < 10; i++) {
            index1 = random.nextInt(listeQuestions.size());
            index2 = random.nextInt(listeQuestions.size());
            temporaire = index[index1];
            index[index1] = index[index2];
            index[index2] = temporaire;
        }

    }

    private void debutTour() {
        joueur.faitChoixFalse();
        debutTour = false;
        afficherQuestionEtChoix();
        placerJoueur();
    }

    private void bougerGauche() {
        joueur.setLocation(joueur.getX() - listeChoixEnCours.get(0).getLargeur() - distanceEntreBoites, joueur.getY());
    }

    private void bougerDroite() {
        joueur.setLocation(joueur.getX() + listeChoixEnCours.get(0).getLargeur() + distanceEntreBoites, joueur.getY());
    }

    private void afficherQuestionEtChoix() {
        lblQuestion.setText(listeQuestions.get(index[compteur]));
        lblQuestion.setSize(largeur, 20);
        lblQuestion.setLocation(0, 80);
        this.add(lblQuestion);

        for (int i = 0; i < listeChoix.get(index[compteur]).length; i++) {
            System.out.println(listeChoix.get(index[compteur])[i]);
            JLabel lblReponse = new JLabel("(" + (char) (65 + i) + ") " + listeChoix.get(index[compteur])[i], JLabel.CENTER);
            lblReponse.setSize(largeur, 20);
            lblReponse.setLocation(0, 120 + (i * lblReponse.getHeight()));
            this.add(lblReponse);
            listeReponses.add(lblReponse);
            
            BoiteChoix boiteChoix = new BoiteChoix("(" + (char) (65 + i) + ")", i);
            distanceEntreBoites = (largeur - (listeChoix.get(index[compteur]).length * boiteChoix.getLargeur())) / (listeChoix.get(index[compteur]).length + 1);
            boiteChoix.setLocation((boiteChoix.getLargeur() + distanceEntreBoites) * i + distanceEntreBoites, 120 + (listeChoix.get(index[compteur]).length * lblReponse.getHeight()));
            this.add(boiteChoix);
            listeChoixEnCours.add(boiteChoix);
        }
    }

    private void bougerBoites() {
        for (BoiteChoix boite : listeChoixEnCours) {
            boite.setLocation(boite.getX(), boite.getY() + 1);
        }
    }

    private void placerJoueur() {
        if (listeChoixEnCours.size() % 2 == 0) {
            joueur.setLocation((listeChoixEnCours.get((listeChoixEnCours.size() / 2) - 1).getX() + (listeChoixEnCours.get((listeChoixEnCours.size() / 2) - 1).getLargeur() / 2)) - (joueur.getWidth()) / 2, joueur.getY());
        } else {
            joueur.setLocation((listeChoixEnCours.get((listeChoixEnCours.size() - 1) / 2).getX() + (listeChoixEnCours.get((listeChoixEnCours.size() - 1) / 2).getLargeur()) / 2) - (joueur.getWidth()) / 2, joueur.getY());
        }
    }

    private void collision() {
        for (BoiteChoix boite : listeChoixEnCours) {
            if (boite.getBounds().intersects(joueur.getBounds())) {
                joueur.setChoix(boite);
                joueur.faitChoixTrue();
            }
        }
    }

    private boolean verifierReponse() {
        progressBar.ajouterProgres();
        return joueur.getChoix().getPosition() == listePositionsReponses.get(index[compteur]);
    }

    private void finTour() {
        for (BoiteChoix boite : listeChoixEnCours) {
            boite.setLocation(1000, 1000);
        }
        
        for (JLabel lblReponse : listeReponses){
            lblReponse.setText("");
        }
        
        listeChoixEnCours.removeAll(listeChoixEnCours);
        listeReponses.removeAll(listeReponses);
        debutTour = true;
        compteur++;
        joueur.ajouterPoint();
    }

    private void finJeu() {
        controleur.calculerScoreCoureur(fenetre.getNiveauID(), compteur);
        if (joueur.getScore() == listeQuestions.size()) {
            JOptionPane.showMessageDialog(this, "Vous avez tout bon!");
        } else {
            JOptionPane.showMessageDialog(this, "Voici la bonne reponse: " + listeChoix.get(index[compteur])[listePositionsReponses.get(index[compteur])] + "\nVotre score est " + controleur.getScoreNiveau(Jeu.COUREUR, fenetre.getNiveauID()) + " points.");
        }
        fenetre.fermerFenetre();
    }
}
