//Toutes les methodes QUI NE SONT PAS DE SIMPLE GETTER ont une javadoc
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
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
public class FenetreCreationDragDrop extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetrePrincipale;

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuJeu = new JMenu("Jeu");
    private JMenuItem mnuCreer = new JMenuItem("Créer le niveau");

    private MondeCreationJeuDragDrop monde;

    public FenetreCreationDragDrop(FenetrePrincipale fenetrePrincipale, String titre, String lien1, String lien2, String largeur, String hauteur, Controleur conroleur) {
        this.fenetrePrincipale = fenetrePrincipale;
        this.controleur = conroleur;
        creerInterface(titre, lien1, lien2, largeur, hauteur);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Cree l'interface graphique
     *
     * @param titre le titre du niveau
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrige le location del'imae du corrige
     * @param largeur le largeur de l'image
     * @param hauteur la hauteur de l'image
     */
    public void creerInterface(String titre, String locationImage, String locationImageCorrige, String largeur, String hauteur) {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        monde = new MondeCreationJeuDragDrop(titre, locationImage, locationImageCorrige, largeur, hauteur, this, controleur);
        this.add(monde);
        mnuJeu.add(mnuCreer);
        mnuBar.add(mnuJeu);
        this.setJMenuBar(mnuBar);

        mnuCreer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                monde.creerNiveau(titre, locationImage, locationImageCorrige, largeur, hauteur);
                controleur.refresh();
                fermerFenetre();
            }
        });
    }

    public void fermerFenetre() {
        this.dispose();
    }
}
