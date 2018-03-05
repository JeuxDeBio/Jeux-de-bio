/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.vue.principale.Fenetre_jeu;
import java.awt.Dimension;
import javax.swing.JComponent;

/**
 *
 * @author Niopo
 */
public class Monde_Coureur extends JComponent {

    Fenetre_jeu fenetre;
    Perso perso = new Perso(Perso.Position.MILIEU);
    Question question = new Question(0, Question.Position.MILIEU);
    
    Thread thread = new Thread() {
        boolean finPartie = false;

        @Override
        public void run() {
            super.run();
            while (!finPartie) {
                perso.requestFocus();
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
        this.creerEvenements();
        if(perso.getPosition().equals(question.getPosition())){ // je veux vois si il me laisse comparer les 2 ou pas  (sauverait quelques problèmes de logique)
            System.out.println("ab");
        }
        this.thread.start();
    }
    
    public void creerInterface(){
        add(perso);
        perso.setVisible(true);
        perso.setLocation(400+perso.getWidth()/2 , 700);
    }
    
    public void creerEvenements(){
        
    }
}
