/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Niopo
 */
public class MondeCoureur extends JComponent {

    FenetreJeu fenetre;
    Perso perso = new Perso(0);

    Question question1 = new Question(5, Question.Position.GAUCHE_EXT, "1", "2", "3", "0 ", " 8", "que fait 5-4");
    Question question2 = new Question(3, Question.Position.GAUCHE_EXT, "1", "2", "3", "gauche_ext");
    Question question3 = new Question(5, Question.Position.DROITE_EXT, "1", "2", "3", "A", "non", "droite ext");
    Question question4 = new Question(3, Question.Position.MILIEU, "1", "2", "3", "milieu");
    Question question5 = new Question(5, Question.Position.DROITE_INT, "1", "2", "3", "a", "y", "droite int");
    Question question6 = new Question(4, Question.Position.GAUCHE_INT, "1", "2", "3", "gauche int");

    private ArrayList<String> listePositionQuestions;
    private ArrayList<String> listeQuestions;
    private ArrayList<String[]> listeReponses;

    private JTextField textQuestion = new JTextField("la question a ajouter ici");

    Thread thread = new Thread() {
        boolean finPartie = false;
        int questionEnCours = -1;
        int compteurMouvement;
        int a = 0; ///bs

        @Override
        public void run() {
            super.run();
            while (!finPartie) {
                if (a % 500 == 0) {
                    questionEnCours = choixQuestion(questionEnCours);
                }
                perso.requestFocus();
                compteurMouvement = bougerPerso(questionEnCours, compteurMouvement);
                a++;

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

    public MondeCoureur(FenetreJeu fenetre, Controleur controleur, Modele modele) {
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(null);

        this.fenetre = fenetre;
        fenetre.setTitle("bou");
        this.creerInterface();

        listePositionQuestions = controleur.getPositionQuestions(fenetre.getNiveauID());
        listeReponses = controleur.getReponsesCoureur(fenetre.getNiveauID());
        listeQuestions = controleur.getQuestionsCoureur(fenetre.getNiveauID());

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

    public int choixQuestion(int choix) {
        int nbChoixPrecedent = 0;
        if (choix != -1) {
            nbChoixPrecedent = listeReponses.get(choix).length;
        }
        if (choix >= 12) {
            listePositionQuestions.remove(listePositionQuestions.get(choix));
            listeQuestions.remove(listeQuestions.get(choix));
            listeReponses.remove(listeReponses.get(choix));
        }
        int nbChoix;
        Random r = new Random();
        System.out.println(listePositionQuestions.size());
        choix = r.nextInt(listePositionQuestions.size());
        nbChoix = listeReponses.get(choix).length;
        System.out.println(choix);
        System.out.println(nbChoix);
        switch (nbChoix) {
            case 4:
                if (nbChoixPrecedent != 4) {
                    perso.setLocation(300 - perso.getWidth() / 2, perso.getY());
                    perso.setPosition(-1);
                }
                break;

            case 5:
                if (nbChoixPrecedent == 4) {
                    if (perso.getPosition() < 0) {
                        perso.setPosition(-1);
                        perso.setLocation(400 - (perso.getWidth() / 2) - (800 / 5), 600);
                    } else {
                        perso.setPosition(1);
                        perso.setLocation(400 - (perso.getWidth() / 2) + (800 / 5), 600);
                    }
                }
                break;

            case 3:

                break;
        }

        return choix;
    }

    public int bougerPerso(int question, int compteur) {
        if (!perso.isPeutBouger()) {
            compteur++;
        }
        if (compteur % 7 == 0) {
            perso.setPeutBouger(true);
        }
        perso.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == e.VK_RIGHT && perso.isPeutBouger() && perso.getPosition() < 2) {
                    switch (listeReponses.get(question).length) {
                        case 3:
                            perso.setLocation(perso.getX() + ((800 / 3)), perso.getY());
                            perso.setPosition(perso.getPosition() + 2);
                            break;
                        case 4:
                            perso.setLocation(perso.getX() + (200), perso.getY());
                            if (perso.getPosition() == -1) {
                                perso.setPosition(perso.getPosition() + 2);
                            } else {
                                perso.setPosition(perso.getPosition() + 1);
                            }
                            break;
                        case 5:
                            perso.setLocation(perso.getX() + ((800 / 5)), perso.getY());
                            perso.setPosition(perso.getPosition() + 1);
                            break;
                    }
                    System.out.println(perso.getPosition() + "   ok");
                    perso.setPeutBouger(false);
                } else if (e.getKeyCode() == e.VK_LEFT && perso.isPeutBouger() && perso.getPosition() > -2) {
                    switch (listeReponses.get(question).length) {
                        case 3:
                            perso.setLocation(perso.getX() - ((800 / 3)), perso.getY());
                            perso.setPosition(perso.getPosition() - 2);
                            break;
                        case 4:
                            perso.setLocation(perso.getX() - (200), perso.getY());
                            if (perso.getPosition() == 1) {
                                perso.setPosition(perso.getPosition() - 2);
                            } else {
                                perso.setPosition(perso.getPosition() - 1);
                            }
                            break;
                        case 5:
                            perso.setLocation(perso.getX() - ((800 / 5)), perso.getY());
                            perso.setPosition(perso.getPosition() - 1);
                            break;
                    }
                    System.out.println(perso.getPosition() + "   ok");
                    perso.setPeutBouger(false);
                }

            }

        });
        return compteur;
    }

    private static ArrayList chargerFichier() {
        ArrayList<Question> liste = new ArrayList();
        ObjectInputStream fichier;
        try {
            fichier = new ObjectInputStream(new FileInputStream("liste questions.bin"));
            for (int i = 0; i < liste.size(); i++) {
                liste.add((Question) fichier.readObject());
            }
            fichier.close();
        } catch (ClassNotFoundException e) {
            System.out.println("fichier non-existant");
        } catch (IOException e) {
            System.out.println("fichier non-accessible");
        }
        return liste;
    }

    private static void ajouterQuestions(ArrayList liste) {
        ObjectOutputStream fichier;
        try {
            fichier = new ObjectOutputStream(new FileOutputStream("liste questions.bin"));
            for (int i = 0; i < liste.size(); i++) {
                fichier.writeObject(liste.get(i));
            }
            fichier.close();
        } catch (FileNotFoundException e) {
            System.out.println("fichier non-existant");
        } catch (IOException e) {
            System.out.println("fichier non-accessible");
        }
    }
}
