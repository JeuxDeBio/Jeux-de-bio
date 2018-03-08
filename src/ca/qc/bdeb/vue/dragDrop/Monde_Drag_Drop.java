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
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

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

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (true) {

                if (timer % 100 == 0) {
                    compteur++;
                    if (compteur % 60 < 10) {
                        lblTimer.setText(compteur / 60 + ":0" + compteur % 60);
                    } else {
                        lblTimer.setText(compteur / 60 + ":" + compteur % 60);
                    }

                }
                timer++;

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

        this.thread.start();

    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveau(Jeu.DRAG_DROP, fenetre.getNiveauID()));
        ArrayList<Ronde_question> listeQuestions = new ArrayList<>();
        ArrayList<Boite_reponse> listeReponses = new ArrayList<>();
        int[][] coordonnees = controleur.getCoordonneesBoitesReponsesDragDrop(fenetre.getNiveauID());
        ArrayList<String> texte = controleur.getQuestionsDragDrop(fenetre.getNiveauID());

        //creation des objets
        for (int i = 0; i < coordonnees.length; i++) {
            if (texte.size() == coordonnees.length) {
                Ronde_question question = new Ronde_question(texte.get(i));
                Boite_reponse reponse = new Boite_reponse();
                this.add(question);
                this.add(reponse);
                listeQuestions.add(question);
                listeReponses.add(reponse);
                reponse.setLocation(coordonnees[i][0], coordonnees[i][1]);
                question.setLocation(800 - question.getWidth()- 10, (600/coordonnees.length) * i);
            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
