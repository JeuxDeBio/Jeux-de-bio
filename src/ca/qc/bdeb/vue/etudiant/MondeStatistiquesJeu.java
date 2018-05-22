//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.etudiant;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Jeu;
import ca.qc.bdeb.vue.principale.Bouton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Batikan
 */
public class MondeStatistiquesJeu extends JComponent {

    private Controleur controleur;

    private FenetreStatistiqueEtudiant fenetre;

    private final int largeur = 350, hauteur = 250;

    private Image image;

    private Jeu jeu;

    private int indexJeu = 0;
    private String nomJeu = "";

    private Bouton boutonQuitter = new Bouton();

    private JLabel lblNomJeu = new JLabel("", JLabel.CENTER);

    public MondeStatistiquesJeu(Controleur controleur, FenetreStatistiqueEtudiant fenetre, Jeu jeu) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.jeu = jeu;

        creerInterface();
        creerInterfaceEtudiant();
        creerEvenements();
    }

    public MondeStatistiquesJeu(Controleur controleur, FenetreStatistiqueEtudiant fenetre, Jeu jeu, Etudiant etudiant) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.jeu = jeu;

        creerInterface();
        creerInterfaceProfesseur(etudiant);
        creerEvenements();
    }

    /**
     * Cree l'interface graphique generale
     */
    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreStatistiquesJeu());

        boutonQuitter.setSize(20, 20);
        boutonQuitter.setLocation(319, 10);
        this.add(boutonQuitter);

        switch (jeu) {
            case DRAG_DROP:
                indexJeu = 0;
                nomJeu = "Drag & Drop";
                break;
            case SHOOTER:
                indexJeu = 1;
                nomJeu = "Shooter";
                break;
            case COUREUR:
                indexJeu = 2;
                nomJeu = "Coureur";
                break;
            case SPEED_RUN:
                indexJeu = 3;
                nomJeu = "Speed Run";
        }

        lblNomJeu.setText(nomJeu);
        lblNomJeu.setSize(330, 20);
        lblNomJeu.setLocation(10, 10);
        this.add(lblNomJeu);
    }

    /**
     * Cree l'interface graphique pour l'etudiant
     */
    private void creerInterfaceEtudiant() {
        for (int i = 0; i < controleur.getEtudiant().getScores()[indexJeu].length; i++) {
            JLabel lblNiveau = new JLabel("Niveau " + (i + 1) + ": " + controleur.getEtudiant().getScores()[indexJeu][i] + " points", JLabel.CENTER);
            lblNiveau.setSize(175, 20);

            IndicateurCouleur indicateur = new IndicateurCouleur(getCouleur(controleur.getEtudiant().getScores()[indexJeu][i]));

            if (i % 2 == 0) {
                lblNiveau.setLocation(0, (lblNiveau.getHeight() + 2) * i + 37);
                indicateur.setLocation(6, (lblNiveau.getHeight() + 2) * i + 37);
            } else {
                lblNiveau.setLocation(175, (lblNiveau.getHeight() + 2) * (i - 1) + 37);
                indicateur.setLocation(largeur - indicateur.getWidth() - 6, (lblNiveau.getHeight() + 2) * (i - 1) + 37);
            }
            this.add(lblNiveau);
            this.add(indicateur);
        }
    }

    /**
     * Cree l'interface graphique pour le professeur
     *
     * @param etudiant l'etudiant fourni
     */
    private void creerInterfaceProfesseur(Etudiant etudiant) {
        for (int i = 0; i < etudiant.getScores()[indexJeu].length; i++) {
            JLabel lblNiveau = new JLabel("Niveau " + (i + 1) + ": " + etudiant.getScores()[indexJeu][i] + " points", JLabel.CENTER);
            lblNiveau.setSize(175, 20);

            IndicateurCouleur indicateur = new IndicateurCouleur(getCouleur(etudiant.getScores()[indexJeu][i]));

            if (i % 2 == 0) {
                lblNiveau.setLocation(0, (lblNiveau.getHeight() + 2) * i + 37);
                indicateur.setLocation(6, (lblNiveau.getHeight() + 2) * i + 37);
            } else {
                lblNiveau.setLocation(175, (lblNiveau.getHeight() + 2) * (i - 1) + 37);
                indicateur.setLocation(largeur - indicateur.getWidth() - 6, (lblNiveau.getHeight() + 2) * (i - 1) + 37);
            }
            this.add(lblNiveau);
            this.add(indicateur);
        }
    }

    /**
     * Cree les evenements
     */
    private void creerEvenements() {
        boutonQuitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
                fenetre.fermerJeu();
            }

        });
    }

    private Color getCouleur(int note) {
        Color color = Color.WHITE;
        if (note >= 70) {
            color = Color.GREEN;
        } else if (note < 70 && note >= 60) {
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
        return color;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(image, 0, 0, this);
        grphcs.setColor(new Color(240, 240, 240));
        grphcs.fillRect(0, 69, 350, 44);
        grphcs.fillRect(0, 157, 350, 44);
    }

}
