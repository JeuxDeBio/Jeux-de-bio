/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.Bouton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author 1649904
 */
public class MondeAjoutClasse extends JComponent {

    private Controleur controleur;
    private FenetreAjoutClasse fenetre;

    private Image image;

    private JTextField txtCode = new JTextField();
    private Bouton boutonOuvrir = new Bouton();
    private Bouton boutonSauvegarder = new Bouton();

    private ArrayList<String> listeDA = new ArrayList<>();

    private final int largeur = 350, hauteur = 130;

    MondeAjoutClasse(Controleur controleur, FenetreAjoutClasse fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    private void creerInterface() {
        image = Toolkit.getDefaultToolkit().getImage(controleur.getLocationFenetreAjoutClasses());

        txtCode.setLocation(168, 12);
        txtCode.setSize(173, 20);
        this.add(txtCode);

        boutonOuvrir.setLocation(150, 58);
        boutonOuvrir.setSize(174, 29);
        this.add(boutonOuvrir);

        boutonSauvegarder.setLocation(87, 94);
        boutonSauvegarder.setSize(174, 29);
        this.add(boutonSauvegarder);

    }

    private void creerEvenements() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier texte", "txt");
        jfc.addChoosableFileFilter(filter);

        boutonOuvrir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                int returnValue = jfc.showOpenDialog(MondeAjoutClasse.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();

                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
                        String ligne = bufferedReader.readLine();
                        while (ligne != null) {
                            listeDA.add(ligne);
                            ligne = bufferedReader.readLine();
                        }
                        bufferedReader.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MondeAjoutClasse.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MondeAjoutClasse.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    fenetre.setErrorLog("Opération annulé par l'utilisateur");
                }
            }

        });

        boutonSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                String listeEtudiant = "";

                for (int i = 0; i < listeDA.size(); i++) {
                    listeEtudiant += listeDA.get(i) + "\n";
                }
                if (!txtCode.getText().equals("")) {
                    fenetre.setErrorLog("");
                    if (!listeDA.isEmpty()) {
                        fenetre.setErrorLog("");
                        if (JOptionPane.showConfirmDialog(MondeAjoutClasse.this, "Veuillez confirmer que cette liste est bien la votre\n\n" + listeEtudiant, "titre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            fenetre.setErrorLog("");
                            listeDA.add(0, txtCode.getText());
                            controleur.creerGroupe(listeDA);
                            fenetre.fermerFenetre();
                        } else {
                            fenetre.setErrorLog("Opération annulé par l'utilisateur");
                            listeDA.clear();
                        }
                    } else {
                        fenetre.setErrorLog("ERREUR! Liste d'étudiant est vide!");
                    }
                } else {
                    fenetre.setErrorLog("ERREUR! Code ne peut pas être vide!");
                    listeDA.clear();
                }

            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
    }

}
