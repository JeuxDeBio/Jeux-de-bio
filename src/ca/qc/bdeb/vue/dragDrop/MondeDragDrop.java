/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author 1649904
 */
public class MondeDragDrop extends JComponent {

    private Modele modele;
    private Controleur controleur;

    private JLabel lblTimer;
    private int timer = 0, compteur = 0;
    private FenetreJeu fenetre;

    private Image imageQuestion;
    
    private ArrayList<RondeQuestion> listeQuestions = new ArrayList<>();
    private ArrayList<BoiteReponse> listeReponses = new ArrayList<>();

    private final int largeur = 800, hauteur = 700;
    private int decalement = 50; 

    private boolean finJeu = false;

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (!finJeu) {
                bougerQuestions();
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

    public MondeDragDrop(JLabel lblTimer, FenetreJeu fenetre, Controleur controleur, Modele modele) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
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
        imageQuestion = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveau(Jeu.DRAG_DROP, fenetre.getNiveauID()));
        
        ArrayList<int[]> coordonnees = controleur.getCoordonneesBoitesReponsesDragDrop(fenetre.getNiveauID());
        
        ArrayList<String> texte = controleur.getQuestionsDragDrop(fenetre.getNiveauID());

        int[] index = new int[coordonnees.size()];

        for (int i = 0; i < coordonnees.size(); i++) {
            index[i] = i;
        }

        Random random = new Random();

        int index1, index2, temporaire;

        for (int i = 0; i < 10; i++) {
            index1 = random.nextInt(coordonnees.size());
            index2 = random.nextInt(coordonnees.size());
            temporaire = index[index1];
            index[index1] = index[index2];
            index[index2] = temporaire;
        }

        for (int i = 0; i < coordonnees.size(); i++) {
            if (texte.size() == coordonnees.size()) {
                RondeQuestion question = new RondeQuestion(texte.get(i), ((hauteur / coordonnees.size()) * index[i]) + 20);
                question.setInitialX(largeur - question.getWidth() - 20);
                this.add(question);
                listeQuestions.add(question);
                question.setLocation(question.getInitialX(), question.getInitialY());
            }
        }

        for (int i = 0; i < coordonnees.size(); i++) {
            if (texte.size() == coordonnees.size()) {
                BoiteReponse reponse = new BoiteReponse(texte.get(i));
                this.add(reponse);
                listeReponses.add(reponse);
                reponse.setLocation(coordonnees.get(i)[0] + 20, coordonnees.get(i)[1] + decalement);
            }
        }

    }

    private void creerEvenements() {
        for (RondeQuestion question : listeQuestions) {
            for (BoiteReponse reponse : listeReponses) {
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
        for (RondeQuestion question : listeQuestions) {
            try {
                if (question.hold()) {
                    if (question.getX() < 5) {
                        question.holdFalse();
                        question.setLocation(question.getInitialX(), question.getInitialY());
                    } else if (question.getX() + question.getWidth() > largeur - 5) {
                        question.holdFalse();
                        question.setLocation(question.getInitialX(), question.getInitialY());
                    } else if (question.getY() < 5) {
                        question.holdFalse();
                        question.setLocation(question.getInitialX(), question.getInitialY());
                    } else if (question.getY() + question.getHeight() > hauteur - 5) {
                        question.holdFalse();
                        question.setLocation(question.getInitialX(), question.getInitialY());
                    } else {
                        question.setLocation((int) this.getMousePosition().getX() - 10, (int) this.getMousePosition().getY() - 25);
                    }
                }
            } catch (NullPointerException e) {
            }
        }

    }

    private void verification() {
        for (RondeQuestion question : listeQuestions) {
            for (BoiteReponse reponse : listeReponses) {
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
        ArrayList<RondeQuestion> listeQuestionsPasRepondus = new ArrayList<>();
        String motsClesManquants = "";
        String motsClesFausses = "";
        int nombreErreurs = 0;

        for (RondeQuestion question : listeQuestions) {
            if (!question.occupe()) {
                listeQuestionsPasRepondus.add(question);
                motsClesManquants += " - " + question.getBoite().getTexte() + "\n";
            }
        }

        if (!listeQuestionsPasRepondus.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas place toutes les mots cles\nVoici ceux qui manquent: \n\n" + motsClesManquants, "Jeu Incomplet", JOptionPane.WARNING_MESSAGE);
        } else {
            for (BoiteReponse reponse : listeReponses) {
                if (!reponse.bonneReponse()) {
                    motsClesFausses += " - " + reponse.getQuestion().getBoite().getTexte() + "\n";
                    nombreErreurs++;
                }
            }

            controleur.calculerScoreDragDrop(fenetre.getNiveauID(), nombreErreurs, compteur);
            this.finJeu = true;

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
        g.drawImage(imageQuestion, 20, decalement, this);
    }

    
}
