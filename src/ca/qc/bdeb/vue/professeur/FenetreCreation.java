//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.vue.dragDrop.MondeCreationDragDrop;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.coureur.MondeCreationCoureur;
import ca.qc.bdeb.vue.dragDrop.FenetreCreationDragDrop;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Nicolas
 */
public class FenetreCreation extends JFrame {

    private FenetreCreationDragDrop fenetreCreationDragDrop;

    private Controleur controleur;
    private Modele modele;

    private FenetrePrincipale fenetrePrincipale;

    private Jeu jeu;

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuJeu = new JMenu("Jeu");
    private JMenuItem mnuCreer = new JMenuItem("Étape suivante");

    public FenetreCreation(Controleur controleur, Modele modele, FenetrePrincipale fenetrePrincipale, Jeu jeu, FenetreCreationDragDrop fenetreCreationDragDrop) {
        this.controleur = controleur;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.jeu = jeu;
        this.fenetreCreationDragDrop = fenetreCreationDragDrop;

        creerInterface();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Cree l'interface graphique
     */
    public void creerInterface() {

        this.setTitle("Creation");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        switch (jeu) {
            case DRAG_DROP:
                MondeCreationDragDrop mondeCreationDragDrop = new MondeCreationDragDrop(controleur, this);
                this.add(mondeCreationDragDrop);
                mnuJeu.add(mnuCreer);
                mnuBar.add(mnuJeu);
                this.setJMenuBar(mnuBar);

                mnuCreer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        mondeCreationDragDrop.sauvegarderInfo();
                    }
                });
                break;
            case COUREUR:
                MondeCreationCoureur mondeCreationCoureur = new MondeCreationCoureur(controleur, this);
                this.add(mondeCreationCoureur);
                mnuJeu.add(mnuCreer);
                mnuBar.add(mnuJeu);
                this.setJMenuBar(mnuBar);

                mnuCreer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                    }
                });
                break;
        }
    }

    public void ouvrirFenetre(String titre, String lien1, String lien2, String largeur, String hauteur) {
        this.fenetreCreationDragDrop = new FenetreCreationDragDrop(fenetrePrincipale, titre, lien1, lien2, largeur, hauteur, controleur);
        fenetreCreationDragDrop.setLocation(fenetrePrincipale.getX() + (fenetrePrincipale.getWidth() - fenetreCreationDragDrop.getWidth()) / 2, fenetrePrincipale.getY());
    }

}
