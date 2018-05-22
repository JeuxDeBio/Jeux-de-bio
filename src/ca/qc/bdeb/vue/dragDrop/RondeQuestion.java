//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 *
 * @author Batikan
 */
public class RondeQuestion extends JComponent {

    private BoiteQuestion boite;

    private int largeur = 20, hauteur = 20;
    private int initialX, initialY;

    private boolean hold = false;

    public RondeQuestion(String texte) {
        boite = new BoiteQuestion(texte);
        this.setSize(boite.getWidth(), boite.getHeight());
        this.add(boite);
        boite.setLocation(0, 0);
    }

    public RondeQuestion() {
        boite = new BoiteQuestion();
        this.setSize(boite.getWidth(), boite.getHeight());
        this.add(boite);
        boite.setLocation(0, 0);
    }

    public void occupeTrue() {
        boite.occupeTrue();
    }

    public void occupeFalse() {
        boite.occupeFalse();
    }

    /**
     * Si le ronde occupe une boite reponse
     *
     * @return si le ronde occupe une boite reponse
     */
    public boolean occupe() {
        return boite.occupe();
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    /**
     * Set le coordonnee X initiale de la boite
     *
     * @param initialX le coordonnee X de la boite
     */
    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    /**
     * Set le coordonnee Y initiale de la boite
     *
     * @param initialY le coordonnee Y de la boite
     */
    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public void holdTrue() {
        this.hold = true;
    }

    public void holdFalse() {
        this.hold = false;
    }

    /**
     * Si le curseur est sur le ronde
     * @return si le curseur est sur le ronde
     */
    public boolean hold() {
        return hold;
    }

    public BoiteQuestion getBoite() {
        return boite;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY() + 15, 20, 20);
    }
}
