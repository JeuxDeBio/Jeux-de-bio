//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Etudiant;
import ca.qc.bdeb.modele.Groupe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 *
 * @author Batikan
 */
public class MondeClasses extends JComponent {

    private Controleur controleur;
    private FenetreClasses fenetre;
    private Groupe groupe;

    private Image image;

    private ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
    private ArrayList<BoutonSelecteur> listeBoutons = new ArrayList<>();

    private JMenuBar mnuBar = new JMenuBar();

    private JLabel lblCode = new JLabel("", JLabel.CENTER);

    private JMenu mnuTous = new JMenu("Tous les étudiants");
    private JMenu mnuEnvelerEtudiants = new JMenu("Enlever");

    private JMenuItem mnuItemSelectionner = new JMenuItem("Sélectionner tous les étudiants!");
    private JMenuItem mnuItemDeselectionner = new JMenuItem("Désélectionner tous les étudiants!");
    private JMenuItem mnuItemEnleverEtudiants = new JMenuItem("Enlever les étudiants sélectionnées!");
    private JMenuItem mnuItemEnleverGroupe = new JMenuItem("Enlever ce groupe!");

    private final int largeur = 400;
    private int hauteur = 50;

    public MondeClasses(Controleur controleur, FenetreClasses fenetre, Groupe groupe) {
        this.hauteur += (30 * groupe.getListeEtudiants().size());
        this.setLayout(null);
        this.setPreferredSize(new Dimension(largeur, hauteur));

        this.controleur = controleur;
        this.fenetre = fenetre;
        this.groupe = groupe;

        this.listeEtudiants = groupe.getListeEtudiants();

        creerInterface();
        creerEvenements();
    }

    /**
     * Cree l'interface graphique
     */
    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreClasses());

        lblCode.setText(groupe.getCode());
        lblCode.setLocation(16, 16);
        lblCode.setSize(367, 20);
        this.add(lblCode);

        for (int i = 0; i < listeEtudiants.size(); i++) {
            JLabel lblEtudiant = new JLabel(listeEtudiants.get(i).getDa() + " | " + listeEtudiants.get(i).getNom(), JLabel.CENTER);
            lblEtudiant.setSize(largeur - 30, 30);
            lblEtudiant.setLocation(0, 50 + (lblEtudiant.getHeight() * i));
            this.add(lblEtudiant);

            BoutonSelecteur boutonSelecteur = new BoutonSelecteur();
            boutonSelecteur.setLocation(largeur - boutonSelecteur.getWidth() - 10, lblEtudiant.getY() + 5);
            this.add(boutonSelecteur);
            listeBoutons.add(boutonSelecteur);
            creerEvenementsBoutons(i);
        }

        mnuTous.add(mnuItemSelectionner);
        mnuTous.add(new JSeparator());
        mnuTous.add(mnuItemDeselectionner);

        mnuEnvelerEtudiants.add(mnuItemEnleverEtudiants);
        mnuEnvelerEtudiants.add(new JSeparator());
        mnuEnvelerEtudiants.add(mnuItemEnleverGroupe);

        mnuBar.add(mnuTous);
        mnuBar.add(mnuEnvelerEtudiants);

        fenetre.setJMenuBar(mnuBar);
    }

    /**
     * Cree les evenements
     *
     * @param i l'identifiant du niveau
     */
    private void creerEvenementsBoutons(int i) {
        listeBoutons.get(i).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                listeBoutons.get(i).press();
                MondeClasses.this.invalidate();
                MondeClasses.this.repaint();
            }
        });
    }

    /**
     * Cree les evenements
     */
    private void creerEvenements() {

        mnuItemSelectionner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeBoutons.size(); i++) {
                    if (!listeBoutons.get(i).estPresse()) {
                        listeBoutons.get(i).press();
                    }
                }
                MondeClasses.this.invalidate();
                MondeClasses.this.repaint();
            }
        });

        mnuItemDeselectionner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listeBoutons.size(); i++) {
                    if (listeBoutons.get(i).estPresse()) {
                        listeBoutons.get(i).press();
                    }
                }
                MondeClasses.this.invalidate();
                MondeClasses.this.repaint();
            }
        });

        mnuItemEnleverEtudiants.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int compteurBoutonsPresses = 0;
                for (int i = 0; i < listeBoutons.size(); i++) {
                    if (listeBoutons.get(i).estPresse()) {
                        compteurBoutonsPresses++;
                    }
                }
                if (compteurBoutonsPresses != 0) {
                    try {
                        if (JOptionPane.showInputDialog(MondeClasses.this, "Veuillez entrer votre mot de passe").equals(controleur.getProfesseur().getMotDePasse())) {
                            fenetre.setErrorLog(" ");
                            ArrayList<Integer> listeAEnlever = new ArrayList<>();
                            for (int i = 0; i < listeBoutons.size(); i++) {
                                if (listeBoutons.get(i).estPresse()) {
                                    listeAEnlever.add(listeEtudiants.indexOf(listeEtudiants.get(i)));
                                }
                            }
                            for (int i = listeEtudiants.size(); i >= 0; i--) {
                                if (listeAEnlever.contains(i)) {
                                    controleur.enleverEtudiant(groupe, groupe.getListeEtudiants().get(i));
                                }
                            }
                            fenetre.fermerFenetre();
                        } else {
                            fenetre.setErrorLog(controleur.getMessageErreur(2));
                        }
                    } catch (NullPointerException ex) {
                        fenetre.setErrorLog(controleur.getMessageErreur(16));
                    }
                } else {
                    fenetre.setErrorLog(controleur.getMessageErreur(10));
                }
            }
        });

        mnuItemEnleverGroupe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (JOptionPane.showInputDialog(MondeClasses.this, "Veuillez entrer votre mot de passe").equals(controleur.getProfesseur().getMotDePasse())) {
                        controleur.enleverGroupe(groupe);
                        controleur.refresh();
                        fenetre.updateFenetre();
                        fenetre.fermerFenetre();
                    } else {
                        fenetre.setErrorLog(controleur.getMessageErreur(2));
                    }
                } catch (NullPointerException e) {
                    fenetre.setErrorLog(controleur.getMessageErreur(16));
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
        for (int i = 0; i < listeEtudiants.size(); i++) {
            if (i % 2 == 0) {
                g.setColor(new Color(242, 242, 242));
            } else {
                g.setColor(Color.WHITE);
            }

            g.fillRect(0, 50 + (30 * i), largeur, 30);
            g.setColor(Color.BLACK);
            g.drawLine(0, hauteur - 1, largeur, hauteur - 1);
        }
    }

}
