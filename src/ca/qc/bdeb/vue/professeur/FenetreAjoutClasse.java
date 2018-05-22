//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class FenetreAjoutClasse extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetre;

    private MondeAjoutClasse monde;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreAjoutClasse(FenetrePrincipale fenetre, Controleur controleur) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.monde = new MondeAjoutClasse(controleur, this);
        this.add(monde);
        this.add(lblErrorLog, BorderLayout.SOUTH);
        this.setTitle("Ajouter nouveau groupe!");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void setErrorLog(String errorLog) {
        lblErrorLog.setText(errorLog);
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreAjoutClasses();
    }

    public void updateFenetre() {
        fenetre.updateFenetre();
    }
}
