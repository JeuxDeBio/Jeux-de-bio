//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc 
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas
 */
public class MondeCreationJeuDragDrop extends JComponent {

    private Controleur controleur;

    private FenetreCreationDragDrop fenetre;

    private Image imageQuestion;

    private final int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20, hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;

    private ArrayList<BoiteReponseConstruction> listeReponses = new ArrayList<>();

    int decalementX = 0;
    int decalementY = 0;

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

    /**
     * Constructeur
     *
     * @param titre le titre
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrige le location de l'image du corrige
     * @param largeur le largeur de l'image
     * @param hauteur la hauteur de l'image
     * @param fenetre la fenetre de jeu
     * @param controleur le controleur
     */
    public MondeCreationJeuDragDrop(String titre, String locationImage, String locationImageCorrige, String largeur, String hauteur, FenetreCreationDragDrop fenetre, Controleur controleur) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.creerInterface(titre, locationImage, locationImageCorrige, largeur, hauteur);
        this.creerEvenements(largeur, hauteur);

        this.thread.start();
    }

    /**
     * Cree l'interface graphique
     *
     * @param titre le titre
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrige le location de l'imade du corrige
     * @param largeurImage le largeur de l'image
     * @param hauteurImage la hauteur de l'image
     */
    public void creerInterface(String titre, String locationImage, String locationImageCorrige, String largeurImage, String hauteurImage) {

        imageQuestion = Toolkit.getDefaultToolkit().getImage(locationImage);

        decalementX = (largeur - 20 - Integer.parseInt(largeurImage)) / 2;
        decalementY = (hauteur - Integer.parseInt(hauteurImage)) / 2;

        this.setPreferredSize(new Dimension(this.largeur, this.hauteur));
        this.setLayout(null);

        nouvelleBoite(largeurImage, hauteurImage);
    }

    /**
     * Cree les evenements
     *
     * @param largeurImage le largeur de l'image
     * @param hauteurImage la hauteur de l'image
     */
    public void creerEvenements(String largeurImage, String hauteurImage) {
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
                        if (verification(boite, largeurImage, hauteurImage)) {
                            boite.setLocation(boite.getPositionX(), boite.getPositionY());
                        } else {
                            String message = JOptionPane.showInputDialog(MondeCreationJeuDragDrop.this, "réponse associée à la boîte", "Ajout de la réponse", JOptionPane.INFORMATION_MESSAGE);
                            if (boite.getReponse().equals("")) {
                                nouvelleBoite(largeurImage, hauteurImage);
                            }
                            if (message != null && !message.equals("")) {

                                valider(boite, message);

                            } else {

                                listeReponses.remove(boite);
                                remove(boite);

                            }
                        }

                    }
                }
            });
        }
    }

    /**
     * Verifie que la boite fournie ne touche pas une autre
     *
     * @param boite la boite de reponse
     * @param largeurImage le largeur de l'image
     * @param hauteurImage la hauteur de l'image
     * @return si la boite touche une autre boite
     */
    public boolean verification(BoiteReponseConstruction boite, String largeurImage, String hauteurImage) {
        boolean mauvaiseBoite = false;
        ArrayList<BoiteReponseConstruction> liste = new ArrayList<>();
        liste.addAll(listeReponses);
        liste.remove(boite);
        for (int i = 0; i < liste.size(); i++) {
            if (boite.getBounds().intersects(liste.get(i).getBounds())) {
                mauvaiseBoite = true;
            }
        }
        if (boite.getX() > (Integer.parseInt(largeurImage) + decalementX + boite.getWidth()) || boite.getX() < decalementX || boite.getY() > (Integer.parseInt(hauteurImage) + decalementY + boite.getHeight()) || boite.getY() < decalementY) {
            mauvaiseBoite = true;
        }
        return mauvaiseBoite;
    }

    /**
     * Verifie que le texte de la boite est correcte
     *
     * @param boite la boite de reponse
     * @param reponse le reponse
     */
    public void valider(BoiteReponseConstruction boite, String reponse) {
        boite.setPositionX(boite.getX() - decalementX);
        boite.setPositionY(boite.getY() - decalementY);
        boite.setReponse(reponse);;
    }

    /**
     * Cree une nouvelle boite apres l'emplacement de l'ancien
     *
     * @param largeurImage le largeur de l'image
     * @param hauteurImage la hauteur de l'image
     */
    public void nouvelleBoite(String largeurImage, String hauteurImage) {
        BoiteReponseConstruction boite = new BoiteReponseConstruction("");
        boite.setLocation(this.largeur - 50, this.hauteur / 2);
        add(boite);
        listeReponses.add(boite);
        creerEvenements(largeurImage, hauteurImage);
    }

    /**
     * Bouge la boite
     */
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

    /**
     * Cree le niveau
     *
     * @param titre le titre
     * @param locationImage le location de l'image du jeu
     * @param locationImageCorrige le location de l'image du corrige
     * @param largeur le largeur de l'image
     * @param hauteur la hauteur de l'image
     */
    public void creerNiveau(String titre, String locationImage, String locationImageCorrige, String largeur, String hauteur) {
        listeReponses.remove(listeReponses.size() - 1);
        controleur.creerNiveauDragDrop(titre, locationImage, locationImageCorrige, largeur, hauteur, listeReponses);
        controleur.refresh();
        fermerFenetre();
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
