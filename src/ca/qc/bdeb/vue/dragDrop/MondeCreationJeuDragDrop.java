/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Niopo
 */
public class MondeCreationJeuDragDrop extends JComponent {

    private FenetreCreationDragDrop fenetre;

    private Image imageQuestion;

    private final int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20, hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;

    private ArrayList<BoiteReponseConstruction> listeReponses = new ArrayList<>();

    //private BoiteReponseConstruction boite = new BoiteReponseConstruction("");
    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                bougerBoite();
                invalidate();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
        }
    };

    public MondeCreationJeuDragDrop(String titre, String lien1, String lien2, String largeur, String hauteur) {

        this.creerInterface(titre, lien1, lien2, largeur, hauteur);
        this.creerEvenements();

        this.thread.start();
    }

    public void creerInterface(String titre, String lien1, String lien2, String largeur, String hauteur) {

        imageQuestion = Toolkit.getDefaultToolkit().getImage(lien1);

        this.setPreferredSize(new Dimension(this.largeur, this.hauteur));
        this.setLayout(null);

        nouvelleBoite();
    }

    public void creerEvenements() {
        for (BoiteReponseConstruction boite : listeReponses) {
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
                        //faire apparaitre un JOptionPane qui demande la réponse à assigner à la boite
                        //valider = la boite se place et les coordonnées sont entrées       annuler =  la boite disparait
                        if (true) { //changer pour ↑
                            boite.setPositionX(boite.getX());
                            boite.setPositionX(boite.getY());
                        }
                        nouvelleBoite();

                    }
                }
            });
        }
    }

    public void nouvelleBoite() {
        BoiteReponseConstruction boite = new BoiteReponseConstruction("");
        boite.setLocation(this.largeur - 50, this.hauteur / 2);
        add(boite);
        listeReponses.add(boite);
        System.out.println(" boite de plus");
    }

    public void bougerBoite() {
        for (BoiteReponseConstruction boite : listeReponses) {
            try {
                if (boite.isHold()) {
                    if (boite.getX() < 5) {
                        boite.holdFalse();
                        boite.setLocation(this.largeur - 50, this.hauteur / 2);
                    } else if (boite.getX() + boite.getWidth() > largeur - 5) {
                        boite.holdFalse();
                        boite.setLocation(this.largeur - 50, this.hauteur / 2);
                    } else if (boite.getY() < 5) {
                        boite.holdFalse();
                        boite.setLocation(this.largeur - 50, this.hauteur / 2);
                    } else if (boite.getY() + boite.getHeight() > hauteur - 5) {
                        boite.holdFalse();
                        boite.setLocation(this.largeur - 50, this.hauteur / 2);
                    } else {
                        boite.setLocation((int) this.getMousePosition().getX() - 10, (int) this.getMousePosition().getY() - 15);
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void creerNiveau(String titre, String lien1, String lien2, String largeur, String hauteur) {
        System.out.println("wahooo");
        System.out.println(listeReponses.size());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Information niveaux\\Drag & Drop" + titre));
            bufferedWriter.write(titre);
            bufferedWriter.write(lien1);
            bufferedWriter.write(lien2);
            bufferedWriter.write(largeur + ";" + hauteur);
            for (int i = 1; i < listeReponses.size(); i++) {
                bufferedWriter.write(listeReponses.get(i).getPositionX() + ":" + listeReponses.get(i).getPositionY() + ":" + listeReponses.get(i).getReponse());
            }

            bufferedWriter.close();

        } catch (IOException ex) {

        }
        fermerFenetre();
    }

    public void fermerFenetre() {
        fenetre.fermerFenetre();
    }
}
