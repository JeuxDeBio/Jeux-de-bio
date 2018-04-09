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
                    ImageTest = selectedFile.getAbsolutePath();
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
                    ImageCorrigee = selectedFile.getAbsolutePath();
                }

            }
        });
    }

    public void sauvegarderInfo() {
        fenetre.ouvrirFenetre(titreNiveau.getText(), ImageTest, ImageCorrigee, largeurImage.getText(), hauteurImage.getText());
        fenetre.dispose();
    }
}