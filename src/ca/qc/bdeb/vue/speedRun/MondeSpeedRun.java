/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.speedRun;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.dragDrop.BoiteReponse;
import ca.qc.bdeb.vue.dragDrop.RondeQuestion;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
    private JTextField txtReponse;

    private Image image;

    private ArrayList<RondeQuestion> listeQuestions = new ArrayList<>();
    private ArrayList<BoiteReponse> listeReponses = new ArrayList<>();

    private final int largeur = 800, hauteur = 600;

    private boolean finJeu = false;

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (!finJeu) {

                invalidate();
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    };

    public MondeSpeedRun(JLabel lblQuestion, JTextField txtReponse, FenetreJeu fenetre, Controleur controleur, Modele modele) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.modele = modele;
        this.controleur = controleur;
        this.fenetre = fenetre;
        
        this.lblQuestion = lblQuestion;
        this.txtReponse = txtReponse;

        this.creerInterface();
        this.creerEvenements();

        this.thread.start();
    }

    private void creerInterface() {
        //image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveau(Jeu.SPEED_RUN, fenetre.getNiveauID()));

        lblQuestion.setSize(700, 100);
        lblQuestion.setLocation(50, 100);
        this.add(lblQuestion);
        
        txtReponse.setSize(700, 25);
        txtReponse.setLocation(50, 400);
        this.add(txtReponse);
    }

    private void creerEvenements() {

    }

}
