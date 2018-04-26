/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.tutorial;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author 1649904
 */
public class MondeSpeedRunTutorial extends JComponent {

    private Modele modele;
    private Controleur controleur;
    private FenetreJeu fenetre;

    private JLabel lblQuestion;
    private JLabel lblTimer;
    private JTextField txtReponse;

    private Bot bot;

    private Joueur joueur;

    private ArrayList<String> listeQuestions = new ArrayList<>();
    private ArrayList<String> listeReponses = new ArrayList<>();

    private final int largeur = 800, hauteur = 600;

    private final int compteurReset = 16;
    private int timer = 0, compteur = compteurReset;

    private boolean finJeu = false;
    private boolean peutRepondre = false;

    private int nombreQuestionsRepondus = 0;

    private JLabel lblDirections = new JLabel("(1) Vous avez 3 secondes avant que la définition se montre", JLabel.CENTER);

    private ProgressBar progressBar;

    private int niveauActuel = 0;

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (!finJeu) {
                progressBar.setCase(niveauActuel);

                timer();
                finTour();

                invalidate();
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
            finJeu();
            fenetre.fermerFenetreTutorial();
        }
    };

    public MondeSpeedRunTutorial(JLabel lblQuestion, JTextField txtReponse, JLabel lblTimer, FenetreJeu fenetre, Controleur controleur, Modele modele) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.modele = modele;
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.lblQuestion = lblQuestion;
        this.lblTimer = lblTimer;
        this.txtReponse = txtReponse;

        this.creerInterface();
        this.creerEvenements();

        this.thread.start();
    }

    private void creerInterface() {
        listeQuestions.add("Que fait 1 + 1? (Vous pouvez l'écrire en chiffre)");
        listeQuestions.add("Quelle est la plus grande ville francophone en Amérique?");
        listeQuestions.add("As-tu hâte que ce niveau de tutoriel se termine?");

        listeReponses.add("2");
        listeReponses.add("montréal");
        listeReponses.add("");

        bot = new Bot(controleur);
        bot.setLocation(largeur - bot.getWidth() - 75, hauteur - bot.getHeight() - 50);
        this.add(bot);

        joueur = new Joueur(controleur);
        joueur.setLocation(75, hauteur - joueur.getHeight() - 50);
        this.add(joueur);

        lblTimer.setText(compteur + "");
        lblTimer.setSize(20, 20);
        lblTimer.setLocation((largeur - lblTimer.getWidth()) / 2, 100);
        this.add(lblTimer);

        lblQuestion.setSize(700, 100);
        lblQuestion.setLocation(50, 100);
        this.add(lblQuestion);

        txtReponse.setSize(700, 25);
        txtReponse.setLocation(50, 400);
        this.add(txtReponse);

        txtReponse.setEditable(peutRepondre);

        progressBar = new ProgressBar(4);
        progressBar.setLocation((this.largeur - progressBar.getLargeur()) / 2, 50);
        this.add(progressBar);

        lblDirections.setLocation(0, 75);
        lblDirections.setSize(largeur, 20);
        this.add(lblDirections);
    }

    private void creerEvenements() {
        txtReponse.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e); //To change body of generated methods, choose Tools | Templates. 
                switch (e.getKeyChar()) {
                    case '\n':
                        if (peutRepondre) {
                            peutRepondre = false;
                            txtReponse.setEditable(peutRepondre);
                            verifierReponse();
                            txtReponse.setText("");
                            lblQuestion.setText("");
                            lblTimer.setText("");
                            compteur = compteurReset;

                            if (niveauActuel == 1) {
                                lblDirections.setText("(3) Quand vous écrivez la bonne réponse, c'est le robot qui perd une vie. Ça marche inversement aussi...");
                                niveauActuel = 2;
                            } else if (niveauActuel == 2) {
                                lblDirections.setText("(4) 3 mauvaises réponses et c'est la fin du jeu!");
                                niveauActuel = 3;
                            }
                        }
                }
            }

        });
    }

    private void choixQuestion() {
        if (progressBar.getProgres() < 3) {
            if (peutRepondre) {
                lblQuestion.setText(listeQuestions.get(progressBar.getProgres()));
            }
        }
    }

    private void timer() {
        if (timer % 100 == 0) {
            switch (compteur) {
                case 16:
                case 15:
                    lblTimer.setText("");
                    break;
                case 14:
                case 13:
                case 12:
                    lblTimer.setText((compteur - 11) + "");
                    break;
                case 11:
                    lblTimer.setText("");
                    break;
                case 10:
                    if (niveauActuel == 0) {
                        lblDirections.setText("(2) Vous pouvez écrire en majuscules ou minuscules, mais il faut toutes les accents!");
                        niveauActuel = 1;
                    }
                    lblTimer.setText(compteur + "");
                    peutRepondre = true;
                    txtReponse.setEditable(peutRepondre);
                    choixQuestion();
                    break;
                case 0:
                    verifierReponse();
                    lblQuestion.setText("");
                    lblTimer.setText("0");
                    compteur = compteurReset;
                    peutRepondre = false;
                    txtReponse.setEditable(peutRepondre);
                    break;
                default:
                    lblTimer.setText(compteur + "");
                    break;

            }
            compteur--;
        }
        timer++;
    }

    private void verifierReponse() {
        if (txtReponse.getText().toLowerCase().equals(listeReponses.get(progressBar.getProgres()).toLowerCase())) {
            bot.enleverPointDeVie();
        } else {
            if (niveauActuel == 3) {
                bot.enleverPointDeVie();
            } else {
                joueur.mauvaiseReponse();
            }
        }

        progressBar.ajouterProgress();
    }

    private void finTour() {
        if (bot.botDetruit()) {
            this.remove(bot);
            bot = new Bot(controleur);
            bot.setLocation(largeur - bot.getWidth() - 75, hauteur - bot.getHeight() - 50);
            this.add(bot);
            joueur.botElimine();
        } else if (joueur.joueurDetruit() || !(progressBar.getProgres() < 3)) {
            finJeu = true;
        }
    }

    private void finJeu() {
        JOptionPane.showMessageDialog(MondeSpeedRunTutorial.this, "Bravo! Vous avez completé le niveau tutorial du Speed Run!");
    }

}
