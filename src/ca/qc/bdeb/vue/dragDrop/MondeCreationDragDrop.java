/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.professeur.FenetreCreation;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Niopo
 */
public class MondeCreationDragDrop extends JComponent {

    //Une boite de texte apparait afin que le prof puisse donner le lien de l'image
    //ensuite l'image apparait et il peut placer les boites et leur assigner une réponse
    //puis lorsqu'il est satisfait de son image et qu'il appuie sur valider, les boites de texte contenant le reste des infomations utiles apparaissent
    //finalement le tout est écrit dans un fichier lorsque le bouton de création est enfoncé
    private Controleur controleur;

    private FenetreCreation fenetre;
    private String ImageTest;
    private String ImageCorrigee;

    private JTextField titreNiveau = new JTextField();
    private JTextField largeurImage = new JTextField();
    private JTextField hauteurImage = new JTextField();

    private JButton choixImageTest = new JButton("ImTest");
    private JButton choixImageCorrigee = new JButton("ImCor");

    public MondeCreationDragDrop(Controleur controleur, FenetreCreation fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(null);

        this.controleur = controleur;

        creerInterface();
        creerEvenements();

    }

    public void creerInterface() {
        titreNiveau.setSize(195, 25);
        titreNiveau.setLocation(100, 100);
        add(titreNiveau);
        choixImageTest.setSize(195, 25);
        choixImageTest.setLocation(100, 135);
        add(choixImageTest);
        choixImageCorrigee.setSize(195, 25);
        choixImageCorrigee.setLocation(100, 170);
        add(choixImageCorrigee);
        largeurImage.setSize(50, 25);
        largeurImage.setLocation(100, 200);
        add(largeurImage);
        hauteurImage.setSize(50, 25);
        hauteurImage.setLocation(175, 200);
        add(hauteurImage);

    }

    public void creerEvenements() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "JPG");
        jfc.addChoosableFileFilter(filter);

        choixImageTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    ImageTest = selectedFile.getPath();
                    
                    //fucking verification de marde pour que le tout soit compatible entre ordis en attendant la base de données
                    String a = "Ecrans";
                    char[] toCharArray = ImageTest.toCharArray();
                    for (int i = 0; i < toCharArray.length; i++) {
                        if (toCharArray[i] == a.charAt(0) && toCharArray[i + 1] == a.charAt(1) && toCharArray[i + 2] == a.charAt(2) && toCharArray[i + 3] == a.charAt(3) && toCharArray[i + 4] == a.charAt(4) && toCharArray[i + 5] == a.charAt(5)) {
                            ImageTest = "";
                            for (int j = i; j < toCharArray.length; j++) {
                                ImageTest += toCharArray[j];

                            }
                            
                        }
                    }
                    
                    
                    
                }

            }
        });
        choixImageCorrigee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                   
                        File selectedFile = jfc.getSelectedFile();
                        ImageCorrigee = selectedFile.getPath();
                        
                        //fucking verification de marde pour que le tout soit compatible entre ordis en attendant la base de données
                        String a = "Ecrans";
                    char[] toCharArray = ImageCorrigee.toCharArray();
                    for (int i = 0; i < toCharArray.length; i++) { 
                        if (toCharArray[i] == a.charAt(0) && toCharArray[i + 1] == a.charAt(1) && toCharArray[i + 2] == a.charAt(2) && toCharArray[i + 3] == a.charAt(3) && toCharArray[i + 4] == a.charAt(4) && toCharArray[i + 5] == a.charAt(5)) {
                            ImageCorrigee = "";
                            for (int j = i; j < toCharArray.length; j++) {
                                ImageCorrigee += toCharArray[j];

                            }
                            
                        }
                    }
                    
                    
                    
                    
                }

            }
        });
    }

    public void sauvegarderInfo() {
        if (titreNiveau.getText() != null && ImageTest != null && ImageCorrigee != null && largeurImage.getText() != null && hauteurImage.getText() != null) {
            try {
                if (0 < Integer.parseInt(largeurImage.getText()) && 0 < Integer.parseInt(hauteurImage.getText())) {
                    fenetre.ouvrirFenetre(titreNiveau.getText(), ImageTest, ImageCorrigee, largeurImage.getText(), hauteurImage.getText());
                    fenetre.dispose();
                } else {
                    System.out.println("la largeur et la hauteur doivent être plus grands que 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("la largeur et la hauteur doivent être des nombres");
            }

        } else {
            if (titreNiveau.getText() == null) {

            }
            if (ImageTest == null) {

            }
            if (ImageCorrigee == null) {

            }
            if (largeurImage.getText() == null) {

            }
            if (hauteurImage.getText() == null) {

            }
        }
    }
}
