/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.tutorial;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.dragDrop.BoiteReponse;
import ca.qc.bdeb.vue.dragDrop.RondeQuestion;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class MondeDragDropTutorial extends JComponent {

    private Controleur controleur;
    private FenetreJeu fenetre;

    private ImageTitre imageTitre;
    private ProgressBar progressBar;

    private RondeQuestion rondeQuestion;
    private BoiteReponse boiteReponse;

    private JLabel lblDirections = new JLabel("", JLabel.CENTER);

    private int currentNiveau = 0;

    private Thread thread = new Thread() {

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (true) {
                progressBar.setCase(currentNiveau);
                bougerQuestions();

                invalidate();
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }

    };

    private final int largeur = 500, hauteur = 200;

    public MondeDragDropTutorial(Controleur controleur, FenetreJeu fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.creerInterface();
        this.creerEvenements();

        this.thread.start();
    }

    private void creerInterface() {
        imageTitre = new ImageTitre(controleur, Jeu.DRAG_DROP);
        imageTitre.setLocation(125, 10);
        this.add(imageTitre);

        progressBar = new ProgressBar(3);
        progressBar.setLocation((largeur - progressBar.getWidth()) / 2, 70);
        this.add(progressBar);

        rondeQuestion = new RondeQuestion();
        rondeQuestion.setInitialX(largeur - rondeQuestion.getWidth() - 10);
        rondeQuestion.setInitialY(150);
        rondeQuestion.setLocation(rondeQuestion.getInitialX(), rondeQuestion.getInitialY());
        this.add(rondeQuestion);

        boiteReponse = new BoiteReponse("^.^");
        boiteReponse.setLocation(10, 155);
        this.add(boiteReponse);

        lblDirections.setText(" (1) Presses sur le cerlce rouge et traîne-là!");
        lblDirections.setSize(largeur, 20);
        lblDirections.setLocation(0, 100);
        this.add(lblDirections);
    }

    private void creerEvenements() {
        rondeQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                super.mousePressed(me); //To change body of generated methods, choose Tools | Templates.
                if (getMousePosition().getX() > rondeQuestion.getX() && getMousePosition().getX() < rondeQuestion.getX() + rondeQuestion.getWidth() && getMousePosition().getY() > rondeQuestion.getY() + 5 && getMousePosition().getY() < rondeQuestion.getY() + rondeQuestion.getHeight() + 5) {
                    rondeQuestion.holdTrue();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                super.mouseReleased(me); //To change body of generated methods, choose Tools | Templates.
                verification();
                if (!rondeQuestion.occupe()) {
                    rondeQuestion.holdFalse();
                    rondeQuestion.setLocation(rondeQuestion.getInitialX(), rondeQuestion.getInitialY());
                }
            }
        });

        boiteReponse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                if (boiteReponse.estOccupe()) {
                    boiteReponse.getQuestion().setLocation(boiteReponse.getQuestion().getInitialX(), boiteReponse.getQuestion().getInitialY());
                    boiteReponse.getQuestion().occupeFalse();
                    boiteReponse.occupeFalse();
                }
            }
        });

    }

    private void bougerQuestions() {
        try {
            if (rondeQuestion.hold()) {
                if (rondeQuestion.getX() < 5) {
                    rondeQuestion.holdFalse();
                    rondeQuestion.setLocation(rondeQuestion.getInitialX(), rondeQuestion.getInitialY());
                } else if (rondeQuestion.getX() + rondeQuestion.getWidth() > largeur - 5) {
                    rondeQuestion.holdFalse();
                    rondeQuestion.setLocation(rondeQuestion.getInitialX(), rondeQuestion.getInitialY());
                } else if (rondeQuestion.getY() < 5) {
                    rondeQuestion.holdFalse();
                    rondeQuestion.setLocation(rondeQuestion.getInitialX(), rondeQuestion.getInitialY());
                } else if (rondeQuestion.getY() + rondeQuestion.getHeight() > hauteur - 5) {
                    rondeQuestion.holdFalse();
                    rondeQuestion.setLocation(rondeQuestion.getInitialX(), rondeQuestion.getInitialY());
                } else {
                    rondeQuestion.setLocation((int) this.getMousePosition().getX() - 10, (int) this.getMousePosition().getY() - 15);
                    if (rondeQuestion.getX() <= 400) {
                        lblDirections.setText(" (2) Traîne le cercle jusqu'à la boîte!");
                        currentNiveau = 1;
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }

    private void verification() {
        if (boiteReponse.getBounds().intersects(rondeQuestion.getBounds()) && !boiteReponse.estOccupe()) {
            rondeQuestion.holdFalse();
            boiteReponse.occupeTrue(rondeQuestion);
            rondeQuestion.occupeTrue();
            rondeQuestion.setLocation(1000, 1000);
            lblDirections.setText(" (3) Presse le menu pour terminer le niveau!");
            currentNiveau = 2;
        }
    }

    public boolean validation() {
        return currentNiveau == 2;   
    }

}
