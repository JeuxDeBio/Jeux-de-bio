/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.vue.principale.Fenetre_jeu;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author 1649904
 */
public class Monde_Drag_Drop extends JComponent {

    JLabel lblTimer;
    int timer = 0, compteur = 0;
    Fenetre_jeu fenetre;

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

    public Monde_Drag_Drop(JLabel lblTimer, Fenetre_jeu fenetre) {
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);

        this.fenetre = fenetre;
        this.lblTimer = lblTimer;

        this.creerInterface();

        this.thread.start();

    }

    private void creerInterface() {

    }

}
