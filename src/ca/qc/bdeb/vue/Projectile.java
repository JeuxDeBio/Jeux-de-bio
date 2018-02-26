/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import java.awt.*;

/**
 *
 * @author Niopo
 */
public class Projectile extends Composant {

    public Projectile(Couleur couleur, int vitesse, Direction direction) {
        super(couleur, vitesse, direction);
        this.setSize(13, 13);
    }

    public void paintComponent(Graphics g) {
        switch (couleur) {
            case BLEU:
                g.setColor(Color.BLUE);
                break;
            case ROUGE:
                g.setColor(Color.RED);
                break;
        }
        g.fillOval(0, 0, 12, 12);
    }
}
