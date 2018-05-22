//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Batikan
 */
public class FenetreStatistiquesGroupe extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetre;

    private MondeStatistiquesGroupe monde;

    private Groupe groupe;

    public FenetreStatistiquesGroupe(Controleur controleur, FenetrePrincipale fenetre, Groupe groupe) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        this.setTitle("Statistiques du " + groupe.getCode());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));

        monde = new MondeStatistiquesGroupe(controleur, this, groupe);
        this.add(monde);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

}
