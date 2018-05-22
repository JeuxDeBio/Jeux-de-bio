//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.tutorial;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.coureur.BoiteChoix;
import ca.qc.bdeb.vue.coureur.Joueur;
import ca.qc.bdeb.vue.principale.FenetreJeu;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Niveau tutoriel du jeu Coureur
 *
 * @author Batikan
 */
public class MondeCoureurTutorial extends JComponent {

    private Controleur controleur;
    private FenetreJeu fenetre;

    private ImageTitre imageTitre;
    private ProgressBar progressBar;

    private Joueur joueur;

    private boolean debutTour = true;

    private JLabel lblDirections = new JLabel("", JLabel.CENTER);
    private JLabel lblQuestion = new JLabel("", JLabel.CENTER);
    private int distanceEntreBoites = 0;

    private int currentNiveau = 0;

    private ArrayList<String> listeQuestions = new ArrayList();
    private ArrayList<String[]> listeChoix = new ArrayList();
    private ArrayList<BoiteChoix> listeChoixEnCours = new ArrayList<>();

    private boolean finJeu = false;

    private Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.

            while (!finJeu) {
                joueur.requestFocus();
                progressBar.setCase(currentNiveau);

                if (debutTour) {
                    debutTour();
                }

                bougerBoites();
                collision();
                if (joueur.faitChoix()) {
                    if (joueur.getChoix().getTexte().equals("^__^")) {
                        JOptionPane.showMessageDialog(MondeCoureurTutorial.this, "Bravo! Vous avez completé le niveau tutorial du Coureur!");
                        fenetre.fermerFenetreTutorial();
                    } else {
                        JOptionPane.showMessageDialog(MondeCoureurTutorial.this, "Etait-ce la face du soulaigement!");
                        fenetre.fermerFenetreTutorial();
                        fenetre.ouvrirFenetreTutorial(Jeu.COUREUR);
                    }

                    finJeu = true;
                }

                invalidate();
                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
            }
        }

    };

    private final int largeur = 800, hauteur = 600;

    public MondeCoureurTutorial(Controleur controleur, FenetreJeu fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.creerInterface();
        this.creerEvenements();

        this.thread.start();
    }

    /**
     * Cree l'interface graphique
     */
    private void creerInterface() {
        imageTitre = new ImageTitre(controleur, Jeu.COUREUR);
        imageTitre.setLocation((largeur - imageTitre.getWidth()) / 2, 10);
        this.add(imageTitre);

        progressBar = new ProgressBar(2);
        progressBar.setLocation((largeur - progressBar.getWidth()) / 2, 70);
        this.add(progressBar);

        joueur = new Joueur(controleur);
        joueur.setLocation((largeur - joueur.getWidth()) / 2, hauteur - joueur.getHeight() - 25);
        this.add(joueur);
    }

    /**
     * Cree les evenements
     */
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

    /**
     * Place le joueur au debut du jeu
     */
    private void placerJoueur() {
        if (listeChoixEnCours.size() % 2 == 0) {
            joueur.setLocation((listeChoixEnCours.get((listeChoixEnCours.size() / 2) - 1).getX() + (listeChoixEnCours.get((listeChoixEnCours.size() / 2) - 1).getLargeur() / 2)) - (joueur.getWidth()) / 2, joueur.getY());
        } else {
            joueur.setLocation((listeChoixEnCours.get((listeChoixEnCours.size() - 1) / 2).getX() + (listeChoixEnCours.get((listeChoixEnCours.size() - 1) / 2).getLargeur()) / 2) - (joueur.getWidth()) / 2, joueur.getY());
        }
    }

    /**
     * Bouge le joueur a gauche
     */
    private void bougerGauche() {
        joueur.setLocation(joueur.getX() - listeChoixEnCours.get(0).getLargeur() - distanceEntreBoites, joueur.getY());
        if (currentNiveau == 0) {
            currentNiveau = 1;
            lblQuestion.setText("(2) Place-toi en dessous de la bonne reponse! (Hint: choisis la face du soulaigement!)");
        }
    }

    /**
     * Bouge le joueur a droite
     */
    private void bougerDroite() {
        joueur.setLocation(joueur.getX() + listeChoixEnCours.get(0).getLargeur() + distanceEntreBoites, joueur.getY());
        if (currentNiveau == 0) {
            currentNiveau = 1;
            lblQuestion.setText("(2) Place-toi en dessous de la bonne reponse! (Hint: choisis la face du soulaigement!)");
        }
    }

    /**
     * Debut du tour
     */
    private void debutTour() {
        joueur.faitChoixFalse();
        debutTour = false;
        afficherQuestionEtChoix();
        placerJoueur();
    }

    /**
     * Affiche la question et les choix
     */
    private void afficherQuestionEtChoix() {
        lblQuestion.setSize(largeur, 20);
        lblQuestion.setLocation(0, 100);
        this.add(lblQuestion);

        lblQuestion.setText("(1) Presse ← ou → pour bouger");

        String[] choix1 = {"^__^", "X__X", "º__º"};
        listeChoix.add(choix1);

        for (int i = 0; i < listeChoix.size(); i++) {
            for (int j = 0; j < listeChoix.get(i).length; j++) {
                BoiteChoix boiteChoix = new BoiteChoix(listeChoix.get(i)[j], i);
                distanceEntreBoites = (largeur - (listeChoix.get(i).length * boiteChoix.getLargeur())) / (listeChoix.get(i).length + 1);
                boiteChoix.setLocation((boiteChoix.getLargeur() + distanceEntreBoites) * j + distanceEntreBoites, 125);
                this.add(boiteChoix);
                listeChoixEnCours.add(boiteChoix);
            }
        }

    }

    /**
     * Bouger les boites question
     */
    private void bougerBoites() {
        if (currentNiveau > 0) {
            for (BoiteChoix boite : listeChoixEnCours) {
                boite.setLocation(boite.getX(), boite.getY() + 1);
            }
        }
    }

    /**
     * Detecte les collisions
     */
    private void collision() {
        for (BoiteChoix boite : listeChoixEnCours) {
            if (boite.getBounds().intersects(joueur.getBounds())) {
                joueur.setChoix(boite);
                joueur.faitChoixTrue();
            }
        }
    }
}
