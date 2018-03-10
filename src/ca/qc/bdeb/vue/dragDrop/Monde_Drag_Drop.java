/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.Fenetre_jeu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author 1649904
 */
public class Monde_Drag_Drop extends JComponent {

    private Modele modele;
    private Controleur controleur;

    private JLabel lblTimer;
    private int timer = 0, compteur = 0;
    private Fenetre_jeu fenetre;

    private Image image;

    private ArrayList<Ronde_question> listeQuestions = new ArrayList<>();
    private ArrayList<Boite_reponse> listeReponses = new ArrayList<>();

    private final int largeur = 800, hauteur = 600;

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (true) {

                bougerQuestions();
                //verification();
                timer();
                invalidate();
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    };

    public Monde_Drag_Drop(JLabel lblTimer, Fenetre_jeu fenetre, Controleur controleur, Modele modele) {
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);

        this.modele = modele;
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.lblTimer = lblTimer;

        this.creerInterface();
        this.creerEvenements();

        this.thread.start();

    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveau(Jeu.DRAG_DROP, fenetre.getNiveauID()));

        int[][] coordonnees = controleur.getCoordonneesBoitesReponsesDragDrop(fenetre.getNiveauID());
        ArrayList<String> texte = controleur.getQuestionsDragDrop(fenetre.getNiveauID());

        for (int i = 0; i < coordonnees.length; i++) {
            if (texte.size() == coordonnees.length) {
                Ronde_question question = new Ronde_question(texte.get(i), ((hauteur / coordonnees.length) * i) + 20);
                question.setInitialX(largeur - question.getWidth() - 20);
                this.add(question);
                listeQuestions.add(question);
                question.setLocation(question.getInitialX(), question.getInitialY());
            }
        }

        for (int i = 0; i < coordonnees.length; i++) {
            if (texte.size() == coordonnees.length) {
                Boite_reponse reponse = new Boite_reponse(texte.get(i));
                this.add(reponse);
                listeReponses.add(reponse);
                reponse.setLocation(coordonnees[i][0], coordonnees[i][1]);
            }
        }

    }

    private void creerEvenements() {
        for (Ronde_question question : listeQuestions) {
            for (Boite_reponse reponse : listeReponses) {
                question.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent me) {
                        super.mousePressed(me); //To change body of generated methods, choose Tools | Templates.
                        if (getMousePosition().getX() > question.getX() && getMousePosition().getX() < question.getX() + question.getLargeur() && getMousePosition().getY() > question.getY() + 15 && getMousePosition().getY() < question.getY() + question.getHauteur() + 15) {
                            question.holdTrue();
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                        super.mouseReleased(me); //To change body of generated methods, choose Tools | Templates.
                        verification();
                        if (!question.occupe()) {
                            question.holdFalse();
                            question.setLocation(question.getInitialX(), question.getInitialY());
                        }
                    }
                });

                reponse.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                        if (reponse.estOccupe()) {
                            reponse.getQuestion().setLocation(reponse.getQuestion().getInitialX(), reponse.getQuestion().getInitialY());
                            reponse.getQuestion().occupeFalse();
                            reponse.occupeFalse();
                        }
                    }
                });
            }
        }
    }

    private void timer() {
        if (timer % 100 == 0) {
            compteur++;
            if (compteur % 60 < 10) {
                lblTimer.setText(compteur / 60 + ":0" + compteur % 60);
            } else {
                lblTimer.setText(compteur / 60 + ":" + compteur % 60);
            }
        }
        timer++;
    }

    private void bougerQuestions() {
        try {
            for (Ronde_question question : listeQuestions) {
                if (question.hold()) {
                    if (question.getX() < 5) {
                        question.holdFalse();
                        question.setLocation(10, (int) this.getMousePosition().getY() - 25);
                    } else if (question.getX() + question.getWidth() > largeur - 5) {
                        question.holdFalse();
                        question.setLocation(largeur - question.getWidth() - 10, (int) this.getMousePosition().getY() - 25);
                    } else if (question.getY() < 5) {
                        question.holdFalse();
                        question.setLocation((int) this.getMousePosition().getX() - 10, 10);
                    } else if (question.getY() + question.getHeight() > hauteur - 5) {
                        question.holdFalse();
                        question.setLocation((int) this.getMousePosition().getX() - 10, hauteur - question.getHeight() - 10);
                    } else {
                        question.setLocation((int) this.getMousePosition().getX() - 10, (int) this.getMousePosition().getY() - 25);
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }

    private void verification() {
        for (Ronde_question question : listeQuestions) {
            for (Boite_reponse reponse : listeReponses) {
                if (reponse.getBounds().intersects(question.getBounds()) && !reponse.estOccupe()) {
                    question.holdFalse();
                    reponse.occupeTrue(question);
                    question.occupeTrue();
                    question.setLocation(1000, 1000);
                }
                reponse.verification();
            }
        }
    }

    public void validationPoints() {
        ArrayList<Ronde_question> listeQuestionsPasRepondus = new ArrayList<>();
        String motsClesManquants = "";
        String motsClesFausses = "";
        int nombreErreurs = 0;

        for (Ronde_question question : listeQuestions) {
            if (!question.occupe()) {
                listeQuestionsPasRepondus.add(question);
                motsClesManquants += " - " + question.getBoite().getTexte() + "\n";
            }
        }

        if (!listeQuestionsPasRepondus.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas place toutes les mots cles\nVoici ceux qui manquent: \n\n" + motsClesManquants, "Jeu Incomplet", JOptionPane.WARNING_MESSAGE);
        } else {
            for (Boite_reponse reponse : listeReponses) {
                if (!reponse.bonneReponse()) {
                    motsClesFausses += " - " + reponse.getQuestion().getBoite().getTexte() + "\n";
                    nombreErreurs++;
                }
            }

            controleur.calculerScoreDragDrop(fenetre.getNiveauID(), nombreErreurs, compteur);

            if (!motsClesFausses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Votre score est " + controleur.getScoreNiveau(Jeu.DRAG_DROP, fenetre.getNiveauID()) + " points.\nVotre temps est " + compteur + " secondes.\nVoici les mots cles que vous avez mal places: \n\n" + motsClesFausses, "Fin de jeu", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Votre score est " + controleur.getScoreNiveau(Jeu.DRAG_DROP, fenetre.getNiveauID()) + " points.\nVotre temps est " + compteur + " secondes.\nVous avez tout bon!", "Fin de jeu", JOptionPane.INFORMATION_MESSAGE);
            }

            fenetre.fermerFenetre();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
