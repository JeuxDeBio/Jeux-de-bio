//Toutes les methodes QUI NE SONT PAS DE SIMPLES GETTER ont une javadoc
package ca.qc.bdeb.vue.professeur;

import ca.qc.bdeb.controleur.Controleur;
import ca.qc.bdeb.vue.principale.Bouton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Batikan
 */
public class MondeAjoutClasse extends JComponent {

    private Controleur controleur;
    private FenetreAjoutClasse fenetre;

    private Image image;

    private JTextField txtCode = new JTextField();
    private Bouton boutonOuvrir = new Bouton();
    private Bouton boutonSauvegarder = new Bouton();

    private ArrayList<String> listeDA = new ArrayList<>();
    private ArrayList<String> listeDAraw = new ArrayList<>();

    private final int largeur = 350, hauteur = 130;

    MondeAjoutClasse(Controleur controleur, FenetreAjoutClasse fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;

        this.setPreferredSize(new Dimension(largeur, hauteur));
        this.setLayout(null);

        creerInterface();
        creerEvenements();
    }

    /**
     * Cree l'interface graphique
     */
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

    /**
     * Cree les evenements
     */
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
                        FileInputStream fis = new FileInputStream(selectedFile);
                        HSSFWorkbook wb = new HSSFWorkbook(fis);
                        HSSFSheet sheet = wb.getSheetAt(0);
                        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

                        for (Row row : sheet) {
                            for (Cell cell : row) {
                                switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
                                    case STRING:
                                        listeDAraw.add(cell.getStringCellValue());
                                }
                            }
                        }

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MondeAjoutClasse.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MondeAjoutClasse.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listeDAraw.remove(0);

                    for (String string : listeDAraw) {
                        String newString = "";
                        for (int i = 0; i < string.length(); i++) {
                            if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
                                newString += string.charAt(i);
                            }
                        }
                        listeDA.add(newString);
                    }
                } else {
                    fenetre.setErrorLog(controleur.getMessageErreur(16));
                    listeDA.clear();
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
                        if (JOptionPane.showConfirmDialog(MondeAjoutClasse.this, "Veuillez confirmer que cette liste est bien la votre\n\n" + listeEtudiant, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            fenetre.setErrorLog("");
                            listeDA.add(0, txtCode.getText());
                            controleur.creerGroupe(listeDA);
                            controleur.refresh();
                            fenetre.updateFenetre();
                            fenetre.fermerFenetre();
                        } else {
                            fenetre.setErrorLog(controleur.getMessageErreur(16));
                            listeDA.clear();
                        }
                    } else {
                        fenetre.setErrorLog(controleur.getMessageErreur(8));
                    }
                } else {
                    fenetre.setErrorLog(controleur.getMessageErreur(9));
                    listeDA.clear();
                }

            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        g.drawLine(0, hauteur - 1, largeur, hauteur - 1);
    }

}
