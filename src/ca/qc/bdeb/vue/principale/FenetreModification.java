//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.modele.Modele;
import ca.qc.bdeb.vue.dragDrop.MondeModificationDragDrop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Nicolas
 */
public class FenetreModification extends JFrame {

    private Controleur controleur;
    private Modele modele;
    private FenetreJeu fenetreJeu;
    private FenetrePrincipale fenetrePrincipale;
    private FenetreSelection fenetreSelection;

    private Jeu jeu;

    private int niveauID;

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuJeu = new JMenu("Jeu");
    private JMenuItem mnuCreer = new JMenuItem("Modifier le niveau");

    FenetreModification(Jeu jeu, FenetreSelection fenetreSelection, FenetrePrincipale fenetrePrincipale, FenetreJeu fenetreJeu, Controleur controleur, Modele modele, int niveauID) {
        this.controleur = controleur;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.fenetreSelection = fenetreSelection;
        this.jeu = jeu;
        this.niveauID = niveauID;
        this.fenetreJeu = fenetreJeu;

        creerInterface();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Cree l'interface graphique
     */
    public void creerInterface() {

        this.setTitle("Modifier un niveau");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        switch (jeu) {
            case DRAG_DROP:
                MondeModificationDragDrop monde = new MondeModificationDragDrop(this, controleur, niveauID);
                this.add(monde);
                mnuJeu.add(mnuCreer);
                mnuBar.add(mnuJeu);
                this.setJMenuBar(mnuBar);

                mnuCreer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        monde.modifierNiveau();
                    }
                });
                break;
        }
    }

    public void fermerFenetre() {
        fenetreSelection.fermerFenetreModification();
        fenetrePrincipale.finJeu();
    }

}
