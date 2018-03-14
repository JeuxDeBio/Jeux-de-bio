/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.speedRun;

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
import javax.swing.JTextField;

/**
 *
 * @author 1649904
 */
public class MondeSpeedRun extends JComponent {

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

    private final int compteurReset = 15;
    private int timer = 0, compteur = compteurReset;

    private boolean finJeu = false;
    private boolean peutRepondre = false;

    private int[] index;

    private int nombreQuestionsRepondus = 0;
    private int nombreBotsDetruits = 0;

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            randomOrdre();
            while (!finJeu) {
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
            fenetre.fermerFenetre();
        }
    };

    public MondeSpeedRun(JLabel lblQuestion, JTextField txtReponse, JLabel lblTimer, FenetreJeu fenetre, Controleur controleur, Modele modele) {
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
        listeQuestions = controleur.getQuestionsSpeedRun(fenetre.getNiveauID());
        listeReponses = controleur.getReponsesSpeedRun(fenetre.getNiveauID());

        bot = new Bot(controleur);
        bot.setLocation(largeur - bot.getWidth() - 75, hauteur - bot.getHeight() - 50);
        this.add(bot);

        joueur = new Joueur(controleur);
        joueur.setLocation(75, hauteur - joueur.getHeight() - 50);
        this.add(joueur);

        lblTimer.setText(compteur + "");
        lblTimer.setSize(20, 20);
        lblTimer.setLocation((largeur - lblTimer.getWidth()) / 2, 50);
        this.add(lblTimer);

        lblQuestion.setSize(700, 100);
        lblQuestion.setLocation(50, 100);
        this.add(lblQuestion);

        txtReponse.setSize(700, 25);
        txtReponse.setLocation(50, 400);
        this.add(txtReponse);
    }

    private void creerEvenements() {
        txtReponse.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e); //To change body of generated methods, choose Tools | Templates.
                switch (e.getKeyChar()) {
                    case '\n':
                        if (peutRepondre) {
                            verifierReponse();
                            txtReponse.setText("");
                            lblQuestion.setText("");
                            compteur = compteurReset;
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

    private void choixQuestion() {
        if (nombreQuestionsRepondus < index.length) {
            if (peutRepondre) {
                lblQuestion.setText(listeQuestions.get(index[nombreQuestionsRepondus]));
            }
        } else {
            finJeu = true;
        }
    }

    private void timer() {
        if (timer % 100 == 0) {
            switch (compteur) {
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
                    lblTimer.setText(compteur + "");
                    peutRepondre = true;
                    choixQuestion();
                    break;
                case 0:
                    lblTimer.setText("0");
                    compteur = compteurReset;
                    peutRepondre = false;
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
        if (txtReponse.getText().equals(listeReponses.get(index[nombreQuestionsRepondus]))) {
            bot.enleverPointDeVie();
        } else {
            joueur.mauvaiseReponse();
        }

        nombreQuestionsRepondus++;
    }

    private void finTour() {
        if (bot.botDetruit()) {
            this.remove(bot);
            bot = new Bot(controleur);
            bot.setLocation(largeur - bot.getWidth() - 75, hauteur - bot.getHeight() - 50);
            this.add(bot);
            joueur.botElimine();
            nombreBotsDetruits++;
        } else if (joueur.joueurDetruit()) {
            finJeu = true;
        }
    }

    private void finJeu() {
        controleur.calculerScoreSpeedRun(fenetre.getNiveauID(), joueur.getScore(), nombreBotsDetruits);
        JOptionPane.showMessageDialog(this, "Votre score est " + controleur.getScoreNiveau(Jeu.SPEED_RUN, fenetre.getNiveauID()));
    }

}
