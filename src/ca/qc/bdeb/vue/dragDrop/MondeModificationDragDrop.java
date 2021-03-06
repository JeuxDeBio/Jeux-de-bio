//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import ca.qc.bdeb.vue.principale.FenetreModification;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nicolas
 */
public class MondeModificationDragDrop extends JComponent {

    private FenetreModification fenetre;
    private Controleur controleur;
    private Image imageQuestion;
    private final int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20, hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;
    int decalementX = 0;
    int decalementY = 0;
    int id = 0;

    private JButton poubelle = new JButton();
    private JButton plus = new JButton();

    private String nomNiveau;
    private String locationImage;
    private String locationImageCorrige;
    private int[] tailleImage = new int[2];
    private boolean peutCreerBoite = true;

    private ArrayList<BoiteReponseConstruction> listeBoites = new ArrayList<>();

    Thread thread = new Thread() {
        int compteur = 0;

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (true) {
                if (!peutCreerBoite) {
                    compteur++;
                    if (compteur % 150 == 0) {
                        peutCreerBoite = true;
                    }
                }
                bougerBoite();
                invalidate();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    invalidate();
                    repaint();
                }
            }
        }
    };

    public MondeModificationDragDrop(FenetreModification fenetre, Controleur controleur, int i) {
        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        this.controleur = controleur;
        this.fenetre = fenetre;

        this.creerInterface(i);
        this.creerEvenements();

        this.thread.start();
    }

    /**
     * Cree l'interface graphique
     *
     * @param a l'identifiant du niveau a modifier
     */
    public void creerInterface(int a) {

        poubelle.setLocation(largeur - 100, 20);
        poubelle.setSize(50, 50);
        this.add(poubelle);
        plus.setLocation(largeur - 100, 70);
        plus.setSize(50, 50);
        this.add(plus);

        nomNiveau = controleur.getNomNiveau(Jeu.DRAG_DROP, a);
        id = controleur.getIdNiveau(Jeu.DRAG_DROP, a);
        locationImage = controleur.getLocationNiveau(Jeu.DRAG_DROP, a);
        locationImageCorrige = controleur.getLocationNiveauCorrige(Jeu.DRAG_DROP, a);
        tailleImage = controleur.getSizeImageDragDrop(a);
        //locationNiveau = modificationInformation(a);

        decalementX = (largeur - 20 - tailleImage[0]) / 2;
        decalementY = (hauteur - tailleImage[1]) / 2;

        ArrayList<int[]> coordonnees = controleur.getCoordonneesBoitesReponsesDragDrop(a);
        ArrayList<String> reponses = controleur.getQuestionsDragDrop(a);
        imageQuestion = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveau(Jeu.DRAG_DROP, a));

        for (int i = 0; i < coordonnees.size(); i++) {
            if (reponses.size() == coordonnees.size()) {
                BoiteReponseConstruction boite = new BoiteReponseConstruction(reponses.get(i));
                boite.setPositionX(coordonnees.get(i)[0]);
                boite.setPositionY(coordonnees.get(i)[1]);
                this.add(boite);
                listeBoites.add(boite);
                boite.setLocation(boite.getPositionX(), boite.getPositionY());
            }
        }
    }

    /**
     * Cree les evenements
     */
    public void creerEvenements() {
        for (BoiteReponseConstruction boite : listeBoites) {
            boite.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    super.mousePressed(me); //To change body of generated methods, choose Tools | Templates.
                    if (getMousePosition().getX() > boite.getX() && getMousePosition().getX() < boite.getX() + boite.getWidth()
                            && getMousePosition().getY() > boite.getY() + 5 && getMousePosition().getY() < boite.getY() + boite.getHeight() + 5) {
                        boite.holdTrue();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    super.mouseReleased(me); //To change body of generated methods, choose Tools | Templates.
                    if (boite.isHold()) {
                        boite.holdFalse();
                        if (boite.getBounds().intersects(poubelle.getBounds())) {
                            supprimerBoite(boite);
                        } else if (verification(boite)) {
                            boite.setLocation(boite.getPositionX(), boite.getPositionY());
                        } else if (!boite.getReponse().equals("")) {
                            String message = JOptionPane.showInputDialog(MondeModificationDragDrop.this, "Voici la réponse actuelle : "
                                    + boite.getReponse() + "\nLaisser la réponse vide conservera la réponse actuelle", "modification de la réponse", JOptionPane.INFORMATION_MESSAGE);
                            if (message == null) {
                                boite.setLocation(boite.getPositionX(), boite.getPositionY());
                                message = boite.getReponse();
                            } else if (message.equals("")) {
                                message = boite.getReponse();
                            }
                            valider(boite, message);
                        } else {
                            String message = JOptionPane.showInputDialog(MondeModificationDragDrop.this, "réponse associée à la boîte", "Ajout de la réponse", JOptionPane.INFORMATION_MESSAGE);

                            if (message != null && !message.equals("")) {
                                valider(boite, message);
                            } else {
                                listeBoites.remove(boite);
                                remove(boite);
                            }
                        }
                    }
                }
            });
        }
        plus.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (peutCreerBoite) {
                    nouvelleBoite();
                }
            }
        }
        );
    }

    /**
     * Verifie si la boite fournie n'est pas en collision avec d'autres boites
     *
     * @param boite la boite
     * @return si la boite fournie n'est pas en collision avec d'autres boites
     */
    public boolean verification(BoiteReponseConstruction boite) {
        boolean mauvaiseBoite = false;
        ArrayList<BoiteReponseConstruction> liste = new ArrayList<>();
        liste.addAll(listeBoites);
        liste.remove(boite);
        for (int i = 0; i < liste.size(); i++) {
            if (boite.getBounds().intersects(liste.get(i).getBounds())) {
                mauvaiseBoite = true;
            }
        }
        if (boite.getX() > (tailleImage[0] + decalementX + boite.getWidth()) || boite.getX() < decalementX || boite.getY() > (tailleImage[1] + decalementY + boite.getHeight()) || boite.getY() < decalementY) {
            mauvaiseBoite = true;
        }
        return mauvaiseBoite;
    }

    /**
     * Valide que la reponse fournie est correcte
     *
     * @param boite de la reponse
     * @param reponse la reponse fournie
     */
    public void valider(BoiteReponseConstruction boite, String reponse) {
        boite.setPositionX(boite.getX());
        boite.setPositionY(boite.getY());
        boite.setReponse(reponse);;
    }

    /**
     * Cree une nouvelle boite apres l'emplacement de l'ancien
     */
    public void nouvelleBoite() {
        BoiteReponseConstruction boite = new BoiteReponseConstruction("");
        boite.setLocation(this.largeur - 50, this.hauteur / 2);
        add(boite);
        listeBoites.add(boite);
        for (int i = 0; i < listeBoites.size() - 1; i++) {
            if (listeBoites.get(i).getBounds().intersects(boite.getBounds())) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        remove(boite);
                    }
                });
                listeBoites.remove(boite);

            }
        }
        peutCreerBoite = false;
        creerEvenements();
    }

    /**
     * Modifie les informations du niveau
     */
    public void modifierNiveau() {
        controleur.modifierNiveauDragDrop(nomNiveau, locationImage, locationImageCorrige, tailleImage, listeBoites, id);

        controleur.refresh();
        fermerFenetre();
    }

    /**
     * Supprime la boite fournie
     *
     * @param boite la boite a supprimer
     */
    public void supprimerBoite(BoiteReponseConstruction boite) {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(MondeModificationDragDrop.this, "Voulez-vous vraiment supprimer cette boîte?", "Supprimer une boîte", 0)) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    remove(boite);
                }
            });
            listeBoites.remove(boite);
        } else {
            boite.setLocation(boite.getPositionX(), boite.getPositionY());
        }
    }

    /**
     * Update l'emplacements des boites
     */
    public void bougerBoite() {
        try {
            for (BoiteReponseConstruction boite : listeBoites) {
                if (boite.isHold()) {
                    if (boite.getX() < 5) {
                        boite.holdFalse();
                        boite.setLocation(boite.getPositionX(), boite.getPositionY());
                    } else if (boite.getX() + boite.getWidth() > largeur - 5) {
                        boite.holdFalse();
                        boite.setLocation(boite.getPositionX(), boite.getPositionY());
                    } else if (boite.getY() < 5) {
                        boite.holdFalse();
                        boite.setLocation(boite.getPositionX(), boite.getPositionY());
                    } else if (boite.getY() + boite.getHeight() > hauteur - 5) {
                        boite.holdFalse();
                        boite.setLocation(boite.getPositionX(), boite.getPositionY());
                    } else {
                        boite.setLocation((int) this.getMousePosition().getX() - 10, (int) this.getMousePosition().getY() - 15);
                    }
                }
            }
        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
            fermerFenetre();
        }

    }

    public void fermerFenetre() {
        fenetre.fermerFenetre();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(imageQuestion, decalementX, decalementY, this);
    }

}
