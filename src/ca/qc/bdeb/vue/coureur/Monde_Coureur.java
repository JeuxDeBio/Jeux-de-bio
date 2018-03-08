/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.vue.principale.Fenetre_jeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Niopo
 */
public class Monde_Coureur extends JComponent {

    Fenetre_jeu fenetre;
    Perso perso = new Perso(Perso.Position.MILIEU);

    Question question1 = new Question(5, Question.Position.GAUCHE_EXT, "1", "2", "3", "que fait 5-4");
    private ArrayList<Question> listeQuestions = new ArrayList<>();
    private JTextField textQuestion = new JTextField("la question a ajouter ici");


    Thread thread = new Thread() {
        boolean finPartie = false;
        Question questionEnCours;
        int compteurMouvement;
        int a =0; ///bs
        @Override
        public void run() {
            super.run();
            while (!finPartie) {
                perso.requestFocus();

                questionEnCours = choixQuestion(a);
                compteurMouvement = bougerPerso(questionEnCours, compteurMouvement);
                a=1;
                try {
                    Thread.sleep(20);

                } catch (InterruptedException e) {
                    invalidate();
                    repaint();
                }
            }
            fenetre.fermerFenetre();
        }
    };

    public Monde_Coureur(Fenetre_jeu fenetre) {
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(null);

        this.fenetre = fenetre;
        fenetre.setTitle("bou");
        this.creerInterface();

        listeQuestions.add(question1);

        this.thread.start();
    }

    public void creerInterface() {
        add(perso);
        perso.setVisible(true);

        perso.setLocation(400 - (perso.getWidth() / 2), 600);
        textQuestion.setSize(800, 35);
        textQuestion.setLocation(0, 0);
        textQuestion.setFont(new Font("Arial", Font.PLAIN, 18));
        textQuestion.setBackground(Color.LIGHT_GRAY);
        textQuestion.setEditable(false);
        add(textQuestion);
    }

    public Question choixQuestion(int a) {
        Question question;
        Random r = new Random();
        int choix = r.nextInt(listeQuestions.size());
        question = listeQuestions.get(choix);
        //listeQuestions.remove(listeQuestions.get(choix)); remettre le fait d'enlever
        if (question.getNbChoix() == 4 && a ==0) {
            perso.setLocation(300 - perso.getWidth()/2, perso.getY()); // fuck a
        }
        return question;
    }

    public int bougerPerso(Question question, int compteur) {
        if (!perso.isPeutBouger()) {
            compteur++;
        }
        if (compteur % 7 == 0) {
            perso.setPeutBouger(true);
        }
        perso.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_RIGHT && perso.isPeutBouger()) {
                    switch (question.getNbChoix()) {
                        case 3:
                            perso.setLocation(perso.getX() + ((800 / 3)), perso.getY());
                            break;
                        case 4:
                            perso.setLocation(perso.getX() + (200), perso.getY());
                            break;
                        case 5:
                            perso.setLocation(perso.getX() + ((800 / 5)), perso.getY());
                            break;
                    }
                    perso.setPeutBouger(false);
                } else if (e.getKeyCode() == e.VK_LEFT && perso.isPeutBouger()) {
                    switch (question.getNbChoix()) {
                        case 3:
                            perso.setLocation(perso.getX() - ((800 / 3)), perso.getY());
                            break;
                        case 4:
                            perso.setLocation(perso.getX() - (200), perso.getY());
                            break;
                        case 5:
                            perso.setLocation(perso.getX() - ((800 / 5)), perso.getY());
                            break;
                    }
                    perso.setPeutBouger(false);
                }

            }

        });
        return compteur;
   }
}
