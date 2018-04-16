/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.modele.Jeu;
import static ca.qc.bdeb.vue.dragDrop.MondeCreationJeuDragDrop.decalementX;
import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import ca.qc.bdeb.vue.principale.Bouton;
import ca.qc.bdeb.vue.principale.FenetreModification;
import java.awt.Graphics;
import javax.swing.SwingUtilities;

/**
 *
 * @author 1649904
 */
public class MondeModificationDragDrop extends JComponent {

    private FenetreModification fenetre;
    private Controleur controleur;
    private Image imageQuestion;
    private final int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20, hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;
    static int decalementX = 20;
    static int decalementY = 20;

    private Bouton poubelle = new Bouton();
    private Bouton plus = new Bouton();

    private ArrayList<BoiteReponseConstruction> listeBoites = new ArrayList<>();

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            while (true) {
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

    public void creerInterface(int a) {

        poubelle.setLocation(largeur - 100, 20);
        poubelle.setSize(50, 50);
        this.add(poubelle);
        plus.setLocation(largeur - 100, 70);
        plus.setSize(50, 50);
        this.add(plus);

        ArrayList<int[]> coordonnees = controleur.getCoordonneesBoitesReponsesDragDrop(a);
        ArrayList<String> reponses = controleur.getQuestionsDragDrop(a);
        imageQuestion = Toolkit.getDefaultToolkit().getImage(controleur.getLocationNiveau(Jeu.DRAG_DROP, a));

        for (int i = 0; i < coordonnees.size(); i++) {
            if (reponses.size() == coordonnees.size()) {
                BoiteReponseConstruction boite = new BoiteReponseConstruction(reponses.get(i));
                boite.setPositionX(coordonnees.get(i)[0] + decalementX);
                boite.setPositionY(coordonnees.get(i)[1] + decalementY);
                this.add(boite);
                listeBoites.add(boite);
                boite.setLocation(coordonnees.get(i)[0] + decalementX, coordonnees.get(i)[1] + decalementY);
            }
        }
    }

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
                nouvelleBoite();
            }
        }
        );
    }

    public void valider(BoiteReponseConstruction boite, String reponse) {
        boite.setPositionX(boite.getX() - decalementX);
        boite.setPositionY(boite.getY() - decalementY);
        boite.setReponse(reponse);;
    }

    public void nouvelleBoite() {
        BoiteReponseConstruction boite = new BoiteReponseConstruction("");
        boite.setLocation(this.largeur - 50, this.hauteur / 2);
        add(boite);
        listeBoites.add(boite);
        creerEvenements();
    }

    public void modifierNiveau() {
        
    }

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

    public void bougerBoite() {
        for (BoiteReponseConstruction boite : listeBoites) {
            try {
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
            } catch (NullPointerException e) {
            }
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
