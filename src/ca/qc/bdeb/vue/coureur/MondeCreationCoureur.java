//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.coureur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.professeur.FenetreCreation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 * Creation d'un niveau de Coureur
 *
 * @author Nicolas
 */
public class MondeCreationCoureur extends JComponent {

    private FenetreCreation fenetre;
    private Controleur controleur;
    private final int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20,
            hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;
    int decalementX = 0;
    int decalementY = 0;
    private DefaultListModel modeleList = new DefaultListModel();
    private JList<String> JListeQuestions = new JList(modeleList);
    private ArrayList<String> listeQuestions = new ArrayList<>();
    private ArrayList<Boite> listeBoites = new ArrayList<>();
    private JButton ajout = new JButton();
    private JButton poubelle = new JButton();
    private int compteurQuestions;
    private boolean questionSpecifique = false;

    public MondeCreationCoureur(Controleur controleur, FenetreCreation fenetre) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        this.creerInterface();
    }

    /**
     * Cree l'interface graphique
     */
    public void creerInterface() {
        /**
         * les réponses risques d'être trop longues ajouter un espace à droite
         * pour les écrire
         *
         *
         */
        if (!questionSpecifique) {

            JListeQuestions.setPreferredSize(new Dimension(largeur - 60, hauteur - 100));
            JListeQuestions.setLocation(50, 10);
            add(JListeQuestions);
            ajout.setPreferredSize(new Dimension(25, 25));
            ajout.setLocation(largeur - 10 - (ajout.getWidth() / 2), hauteur - 75);
            add(ajout);
            poubelle.setPreferredSize(new Dimension(25, 25));
            poubelle.setLocation(largeur + 10 + (ajout.getWidth() / 2), hauteur - 75);
            add(poubelle);
        }
        creerEvenements();
    }

    /**
     * Cree les evenements
     */
    public void creerEvenements() {
        ajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String question = JOptionPane.showInputDialog(MondeCreationCoureur.this, "Écrivez votre question", "Ajout d'une question", JOptionPane.INFORMATION_MESSAGE);

                if (question != null && !question.equals("")) {

                    Object[] options = {"3", "4", "5"};
                    int nbReponses = JOptionPane.showOptionDialog(MondeCreationCoureur.this, "Choisir le nombre de choix de réponse",
                            "nombre de choix de réponse", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                    String toutesReponses = "";
                    while (nbReponses > 0) {
                        String reponse = JOptionPane.showInputDialog(MondeCreationCoureur.this, "question", "Ajout d'une qréponse", JOptionPane.INFORMATION_MESSAGE);
                        if (reponse != null && !reponse.equals("")) {
                            toutesReponses += reponse;
                            nbReponses--;
                            if (nbReponses != 0) {
                                toutesReponses += ";";
                            }
                        } else if (JOptionPane.showConfirmDialog(MondeCreationCoureur.this, "Continuer annulera la question",
                                "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                            nbReponses = 0;
                        }
                    }

                    question += ":" + toutesReponses;
                    listeQuestions.add(question);
                    update();
                }

            }

        }
        );
        for (Boite boite : listeBoites) {

            boite.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {

                }
            });
        }
    }

    /**
     * Update l'interface graphique
     */
    public void update() {
        int compteur = 0;
        modeleList.clear();
        for (String question : listeQuestions) {
            compteur++;
            String split[] = question.split(":");
            modeleList.addElement(compteur + ". " + split[0]);
            creerInterface();
        }

    }
}
