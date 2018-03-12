/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.shooter;

import ca.qc.bdeb.vue.principale.FenetreJeu;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Niopo
 */
public class MondeShooter extends JComponent {

    FenetreJeu fenetre;
    Canon canon = new Canon();
    Composant composantProjectile = new Composant(Composant.Couleur.BLEU, 0, Composant.Direction.N);
    Composant composantMechant = new Composant(Composant.Couleur.BLEU, 0, Composant.Direction.N);
    private ArrayList<Integer> listeKeyCodes = new ArrayList<>();
    private ArrayList<Projectile> listeProjectiles = new ArrayList<>();
    private ArrayList<Projectile> listeProjectilesEnlever = new ArrayList<>();
    private ArrayList<Mechant> listeMechants = new ArrayList<>();
    private ArrayList<Mechant> listeMechantsEnlever = new ArrayList<>();

    Thread thread = new Thread() {
        int compteurTir = 0;
        int compteurMechant;
        boolean finPartie = false;

        @Override
        public void run() {
            super.run();
            while (!finPartie) {
                canon.requestFocus();
                choixMunition();
                compteurTir = tirer(compteurTir);
                deplacementProjectiles();
                deplacementMechants();
                retirerMechantsEtProjectiles();
                if (compteurMechant % 100 == 0 && compteurMechant != 0) {
                    apparitionMechant();
                }
                collisionMechants();
                finPartie = voirSiFinPartie();
                MondeShooter.this.invalidate();
                MondeShooter.this.repaint();
                compteurMechant++;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    invalidate();
                    repaint();
                }
            }
            fenetre.fermerFenetre();
        }
    };

    public MondeShooter(FenetreJeu fenetre) {
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(null);

        this.fenetre = fenetre;

        this.creerInterface();
        this.creerEvenements();
        this.thread.start();
    }

    public void creerInterface() {
        add(canon);
        canon.setVisible(true);
        canon.setLocation(400 - (canon.getWidth() / 2), 400 - (canon.getWidth() / 2));
    }

    public void creerEvenements() {
        canon.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (!listeKeyCodes.contains(e.getKeyCode())) {
                    listeKeyCodes.add(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                listeKeyCodes.remove(new Integer(e.getKeyCode()));
            }

        });
    }

    public void choixMunition() {
        if (listeKeyCodes.contains(KeyEvent.VK_1)) {
            composantProjectile.setCouleur(Composant.Couleur.BLEU);
        } else if (listeKeyCodes.contains(KeyEvent.VK_2)) {
            composantProjectile.setCouleur(Composant.Couleur.ROUGE);
        }
    }

    public int tirer(int compteurTir) {

        if (canon.isPeutTirer()) {
            if (listeKeyCodes.contains(KeyEvent.VK_UP) && listeKeyCodes.contains(KeyEvent.VK_RIGHT)) {
                composantProjectile.setDirection(Composant.Direction.NE);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_UP) && listeKeyCodes.contains(KeyEvent.VK_LEFT)) {
                composantProjectile.setDirection(Composant.Direction.NO);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_UP)) {
                composantProjectile.setDirection(Composant.Direction.N);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_DOWN) && listeKeyCodes.contains(KeyEvent.VK_LEFT)) {
                composantProjectile.setDirection(Composant.Direction.SO);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_DOWN) && listeKeyCodes.contains(KeyEvent.VK_RIGHT)) {
                composantProjectile.setDirection(Composant.Direction.SE);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_DOWN)) {
                composantProjectile.setDirection(Composant.Direction.S);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_LEFT)) {
                composantProjectile.setDirection(Composant.Direction.O);
                lancerProjectile();
            } else if (listeKeyCodes.contains(KeyEvent.VK_RIGHT)) {
                composantProjectile.setDirection(Composant.Direction.E);
                lancerProjectile();
            }

        } else {
            compteurTir++;
            if (compteurTir % 40 == 0) {
                canon.setPeutTirer(true);
            }
        }
        return compteurTir;
    }

    public void deplacementProjectiles() {
        for (Projectile projectile : listeProjectiles) {
            switch (projectile.getDirection()) {
                case N:
                    projectile.setLocation(projectile.getX(), projectile.getY() - projectile.getVitesse());
                    break;
                case S:
                    projectile.setLocation(projectile.getX(), projectile.getY() + projectile.getVitesse());
                    break;
                case E:
                    projectile.setLocation(projectile.getX() + projectile.getVitesse(), projectile.getY());
                    break;
                case O:
                    projectile.setLocation(projectile.getX() - projectile.getVitesse(), projectile.getY());
                    break;
                case NO:
                    projectile.setLocation(projectile.getX() - ((projectile.getVitesse() / 2) + 2), projectile.getY() - ((projectile.getVitesse() / 2) + 2));
                    break;
                case NE:
                    projectile.setLocation(projectile.getX() + ((projectile.getVitesse() / 2) + 2), projectile.getY() - ((projectile.getVitesse() / 2) + 2));
                    break;
                case SO:
                    projectile.setLocation(projectile.getX() - ((projectile.getVitesse() / 2) + 2), projectile.getY() + ((projectile.getVitesse() / 2) + 2));
                    break;
                case SE:
                    projectile.setLocation(projectile.getX() + ((projectile.getVitesse() / 2) + 2), projectile.getY() + ((projectile.getVitesse() / 2) + 2));
                    break;
                default:
                    break;
            }
        }
    }

    public void lancerProjectile() {
        canon.setPeutTirer(false);
        Projectile projectile = new Projectile(composantProjectile.getCouleur(), 7, composantProjectile.getDirection());
        listeProjectiles.add(projectile);
        add(projectile);
        projectile.setLocation(canon.getX() + canon.getWidth() / 2 - projectile.getWidth() / 2, canon.getY() + canon.getWidth() / 2 - projectile.getWidth() / 2);
    }

    public void apparitionMechant() { 
        Random r = new Random();
        int choixCouleur = r.nextInt(2);
        if (choixCouleur == 0) {
            composantMechant.setCouleur(Composant.Couleur.BLEU);
        } else {
            composantMechant.setCouleur(Composant.Couleur.ROUGE);
        }
        Mechant mechant = new Mechant(composantMechant.getCouleur(), 2, composantMechant.getDirection());
        add(mechant);
        listeMechants.add(mechant);
        int choixDirection = r.nextInt(8);
        switch (choixDirection) {
            case 0:
                mechant.setDirection(Composant.Direction.N);
                mechant.setLocation(375, 800);
                break;
            case 1:
                mechant.setDirection(Composant.Direction.NE);
                mechant.setLocation(0 - mechant.getWidth(), 800 + mechant.getHeight()/2);
                break;
            case 2:
                mechant.setDirection(Composant.Direction.NO);
                mechant.setLocation(800, 800 + mechant.getHeight()/2);
                break;
            case 3:
                mechant.setDirection(Composant.Direction.S);
                mechant.setLocation(375, 0 - mechant.getHeight());
                break;
            case 4:
                mechant.setDirection(Composant.Direction.SE);
                mechant.setLocation(0 - mechant.getWidth(), 0 - mechant.getHeight());
                break;
            case 5:
                mechant.setDirection(Composant.Direction.SO);
                mechant.setLocation(800, 0 - mechant.getHeight());
                break;
            case 6:
                mechant.setDirection(Composant.Direction.E);
                mechant.setLocation(0 - mechant.getWidth(), 400 - mechant.getHeight() / 2);
                break;
            case 7:
                mechant.setDirection(Composant.Direction.O);
                mechant.setLocation(800, 400 - mechant.getHeight() / 2);
                break;

        }

    }

    public void retirerMechantsEtProjectiles() {
        for (Projectile projectile : listeProjectiles) {
            if (projectile.getX() <= 0 || projectile.getX() + projectile.getWidth() >= 850
                    || projectile.getY() + projectile.getHeight() <= 0 || projectile.getY() >= 800) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        remove(projectile);
                    }
                });
                listeProjectilesEnlever.add(projectile);

            }
        }

        for (Projectile projectile : listeProjectiles) {
            for (Mechant mechant : listeMechants) {
                if (projectile.getBounds().intersects(mechant.getBounds())) {
                    if (projectile.getCouleur().equals(mechant.getCouleur())) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                remove(projectile);
                                remove(mechant);
                                listeMechantsEnlever.add(mechant);
                                // ajouter des pts pour avoir tué un mechant
                            }

                        });
                    } else {
                        mechant.setVitesse((mechant.getVitesse() - 1) * 2);

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                remove(projectile);
                            }

                        });
                    }
                    listeProjectilesEnlever.add(projectile);
                }
            }
        }

        listeProjectiles.removeAll(listeProjectilesEnlever);
        listeMechants.removeAll(listeMechantsEnlever);
    }

    public void deplacementMechants() {
        for (Mechant mechant : listeMechants) {
            switch (mechant.getDirection()) {
                case N:
                    mechant.setLocation(mechant.getX(), mechant.getY() - mechant.getVitesse());
                    break;
                case NE:
                    mechant.setLocation(mechant.getX() + ((mechant.getVitesse() / 2) + 1), mechant.getY() - ((mechant.getVitesse() / 2) + 1));
                    break;
                case NO:
                    mechant.setLocation(mechant.getX() - ((mechant.getVitesse() / 2) + 1), mechant.getY() - ((mechant.getVitesse() / 2) + 1));
                    break;
                case S:
                    mechant.setLocation(mechant.getX(), mechant.getY() + mechant.getVitesse());
                    break;
                case SE:
                    mechant.setLocation(mechant.getX() + ((mechant.getVitesse() / 2) + 1), mechant.getY() + ((mechant.getVitesse() / 2) + 1));
                    break;
                case SO:
                    mechant.setLocation(mechant.getX() - ((mechant.getVitesse() / 2) + 1), mechant.getY() + ((mechant.getVitesse() / 2) + 1));
                    break;
                case E:
                    mechant.setLocation(mechant.getX() + mechant.getVitesse(), mechant.getY());
                    break;
                case O:
                    mechant.setLocation(mechant.getX() - mechant.getVitesse(), mechant.getY());
                    break;
            }
        }
    }

    public void collisionMechants() {
        for (Mechant mechant : listeMechants) {
            if (mechant.getBounds().intersects(canon.getBounds())) {
                canon.setNbVies(canon.getNbVies() - 1);
                if (canon.getNbVies() > 0) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            remove(mechant);
                        }

                    });
                }
                listeMechantsEnlever.add(mechant);

            }
        }
        listeMechants.removeAll(listeMechantsEnlever);
    }

    public boolean voirSiFinPartie() {
        boolean fin = false;
        if (canon.getNbVies() <= 0) {
            fin = true;
        }

        return fin;
    }
}
