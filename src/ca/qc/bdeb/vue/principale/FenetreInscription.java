//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc 
package ca.qc.bdeb.vue.principale;

import ca.qc.bdeb.vue.professeur.MondeInscriptionProfesseurs1;
import ca.qc.bdeb.vue.etudiant.MondeInscriptionEtudiants1;
import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.etudiant.MondeInscriptionEtudiants2;
import ca.qc.bdeb.modele.TypeUtilisateur;
import ca.qc.bdeb.vue.professeur.MondeInscriptionProfesseurs2;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class FenetreInscription extends JFrame {

    private MondeInscriptionEtudiants1 mondeEtudiant1;
    private MondeInscriptionEtudiants2 mondeEtudiant2;
    private MondeInscriptionProfesseurs1 mondeProfesseur1;
    private MondeInscriptionProfesseurs2 mondeProfesseur2;

    private FenetrePrincipale fenetre;
    private Controleur controleur;
    private TypeUtilisateur type;

    private JLabel lblErrorLog = new JLabel(" ", JLabel.CENTER);

    public FenetreInscription(FenetrePrincipale fenetre, Controleur controleur, TypeUtilisateur type) {
        this.fenetre = fenetre;
        this.controleur = controleur;
        this.type = type;

        creerInterface();

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Cree l'interface graphique
     */
    private void creerInterface() {
        this.setTitle("Inscription");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(controleur.getLocationIconeApplication()));
        switch (type) {
            case ETUDIANT:
                mondeEtudiant1 = new MondeInscriptionEtudiants1(this, controleur);
                this.add(mondeEtudiant1);
                break;
            case PROFESSEUR:
                mondeProfesseur1 = new MondeInscriptionProfesseurs1(this, controleur);
                this.add(mondeProfesseur1);
        }

        this.add(lblErrorLog, BorderLayout.SOUTH);
    }

    public void setErrorLog(String text) {
        this.lblErrorLog.setText(text);
        pack();
        invalidate();
        repaint();
    }

    /**
     * Met la 2e etape de l'inscription des etudiants
     *
     * @param da le DA fourni
     * @param motDePasse le mot de passe fourni
     */
    public void etape2Etudiants(String da, String motDePasse) {
        mondeEtudiant2 = new MondeInscriptionEtudiants2(this, controleur, da, motDePasse);
        this.remove(mondeEtudiant1);
        this.add(mondeEtudiant2);
        pack();
        invalidate();
        repaint();
    }

    /**
     * Le 2e etape de l'inscription des professeurs
     *
     * @param nu le nom d'utilisateur fourni
     * @param motDePasse le mot de passe fourni
     */
    public void etape2Professeurs(String nu, String motDePasse) {
        mondeProfesseur2 = new MondeInscriptionProfesseurs2(this, controleur, nu, motDePasse);
        this.remove(mondeProfesseur1);
        this.add(mondeProfesseur2);
        pack();
        invalidate();
        repaint();
    }

    public void fermerFenetre() {
        fenetre.fermerFenetreInscription();
    }

}
