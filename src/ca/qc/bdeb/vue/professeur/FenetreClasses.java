//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Groupe;
import ca.qc.bdeb.vue.principale.FenetrePrincipale;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class FenetreClasses extends JFrame {

    private Controleur controleur;
    private FenetrePrincipale fenetre;
    private Groupe groupe;

    private MondeClasses monde;

    private JLabel errorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreClasses(Controleur controleur, FenetrePrincipale fenetre, Groupe groupe) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        monde = new MondeClasses(controleur, this, groupe);
        this.add(monde);
        this.add(errorLog, BorderLayout.SOUTH);

        this.setTitle("Liste d'étudiants du " + groupe.getCode());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void setErrorLog(String errorLog) {
        this.errorLog.setText(errorLog);
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreClasses();
    }

    public void updateFenetre() {
        fenetre.updateFenetre();
    }

}
