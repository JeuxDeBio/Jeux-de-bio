/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue.dragDrop;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.FenetreCreation;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTextField;

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
    
    private JTextField titreNiveau = new JTextField();
    private JTextField lienImageTest = new JTextField();
    private JTextField lienImageCorrigee = new JTextField();
    private JTextField largeurImage = new JTextField();
    private JTextField hauteurImage = new JTextField();

    

    

    public MondeCreationDragDrop(Controleur controleur, FenetreCreation fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(null);

        this.controleur = controleur;

        this.creerInterface();

      
    }

    public void creerInterface() {
        titreNiveau.setSize(195, 25);
        titreNiveau.setLocation(100, 100);
        add(titreNiveau);
        lienImageTest.setSize(195, 25);
        lienImageTest.setLocation(100, 135);
        add(lienImageTest);
        lienImageCorrigee.setSize(195, 25);
        lienImageCorrigee.setLocation(100, 170);
        add(lienImageCorrigee);
        largeurImage.setSize(50, 25);
        largeurImage.setLocation(100, 200);
        add(largeurImage);
        hauteurImage.setSize(50, 25);
        hauteurImage.setLocation(175, 200);
        add(hauteurImage);
    }

    

    public void sauvegarderInfo() {
        fenetre.ouvrirFenetre(titreNiveau.getText(),lienImageTest.getText(), lienImageCorrigee.getText(), largeurImage.getText(), hauteurImage.getText());
        fenetre.dispose();
    }
}
