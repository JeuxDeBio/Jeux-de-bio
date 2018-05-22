//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class FenetreModificationMDP extends JFrame {

    private Controleur controleur;

    private FenetrePrincipale fenetre;

    private MondeModificationMDP monde;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreModificationMDP(Controleur controleur, FenetrePrincipale fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        creerInterface();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Cree l'interface graphique
     */
    private void creerInterface() {
        monde = new MondeModificationMDP(controleur, this);
        this.setTitle("Modification de mot de passe");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        this.setResizable(false);
        this.add(monde);

        this.add(lblErrorLog, BorderLayout.SOUTH);
    }

    public void errorLogSetText(String log) {
        lblErrorLog.setText(log);
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreModificationMDP();
    }
}
