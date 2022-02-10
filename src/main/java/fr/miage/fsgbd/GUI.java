package main.java.fr.miage.fsgbd;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 */
public class GUI extends JFrame implements ActionListener {
    TestInteger testInt = new TestInteger();
    private JFileChooser fileChooser;
    BTreePlus<Integer,etudiant> bInt;
    private JButton loadfile,btnFileDial,buttonClean, buttonRemove, buttonLoad, buttonSave, buttonAddMany, buttonAddItem, buttonRefresh,buttonTest;
    private JTextField pathF,txtNbreItem, txtNbreSpecificItem, txtU, txtFile, removeSpecific;
    private final JTree tree = new JTree();

    public GUI() {
        super();
        build();
    }

    public void actionPerformed(ActionEvent e) {
    	
    	
        if (e.getSource() == buttonLoad || e.getSource() == buttonClean || e.getSource() == buttonSave || e.getSource() == buttonRefresh) {
            if (e.getSource() == buttonLoad) {
                BDeserializer<Integer,etudiant> load = new BDeserializer<Integer,etudiant>();
                bInt = load.getArbre(txtFile.getText());
                if (bInt == null)
                    System.out.println("Echec du chargement.");

            } else if (e.getSource() == buttonClean) {
                if (Integer.parseInt(txtU.getText()) < 2)
                    System.out.println("Impossible de cr?er un arbre dont le nombre de cl?s est inf?rieur ? 2.");
                else
                    bInt = new BTreePlus<Integer,etudiant>(Integer.parseInt(txtU.getText()), testInt);
            } else if (e.getSource() == buttonSave) {
                BSerializer<Integer,etudiant> save = new BSerializer<Integer,etudiant>(bInt, txtFile.getText());
            }else if (e.getSource() == buttonRefresh) {
                tree.updateUI();
            }
        } else {
            if (bInt == null)
                bInt = new BTreePlus<Integer,etudiant>(Integer.parseInt(txtU.getText()), testInt);

            if (e.getSource() == buttonAddMany) {
            	
                
                	 for (int j = 0; j < Integer.parseInt(txtNbreItem.getText()); j++) {
                         int valeur = (int) (Math.random() * 10 * Integer.parseInt(txtNbreItem.getText()));
                         boolean done = bInt.addValeur(new keyPointer<Integer, etudiant>(valeur, new etudiant()));
/*
                	for (int i = 1; i < 10000; i++) {
                	NameGenerator r = new NameGenerator();
                	
                	int N_etudiant = r.Numero_etudiant();
                	String Nom_etudiant = r.nameGenerator();
                	String Prenom_etudiant = r.lastNameGenerator();
                	
                	
                    boolean done = bInt.addValeur(N_etudiant);
                    BTreePlus.addToPointeur(N_etudiant, i);
                    BTreePlus.addRecord(N_etudiant,Nom_etudiant,Prenom_etudiant,i);
					
					  On pourrait forcer l'ajout mais on risque alors de tomber dans une boucle infinie sans "r?gle" faisant sens pour en sortir

					while (!done)
					{
						valeur =(int) (Math.random() * 10 * Integer.parseInt(txtNbreItem.getText()));
						done = bInt.addValeur(valeur);
					}
					 */
                }

            } else if (e.getSource() == buttonAddItem) {
            	 if (!bInt.addValeur(new keyPointer<Integer, etudiant>(Integer.parseInt(txtNbreSpecificItem.getText()), new etudiant())))
                     ;   System.out.println("Tentative d'ajout d'une valeur existante : " + txtNbreSpecificItem.getText());
                txtNbreSpecificItem.setText(
                        String.valueOf(
                                Integer.parseInt(txtNbreSpecificItem.getText()) + 2
                        )
                );

            } else if (e.getSource() == buttonRemove) {
                bInt.removeValeur(Integer.parseInt(removeSpecific.getText()));
            }else if (e.getSource() == loadfile) {
                System.out.println("Load the data from the file");
                bInt = new BTreePlus<>(Integer.parseInt(txtU.getText()), testInt);
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(new File(pathF.getText())));
                    String line = reader.readLine();
                    int indexRow = 0;
                    while (line != "" && line != null) {
                        String[] tokens = line.split(";");
                        if (indexRow > 0) { // Ignore header
                            if (tokens.length == 5) {
                                etudiant identity = new etudiant(tokens[4], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                                bInt.addValeur(new keyPointer<Integer, etudiant>(Integer.parseInt(tokens[0]), identity));
                            }
                        }
                        try {
                            line = reader.readLine();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        indexRow++;
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        }
        if (e.getSource() == btnFileDial) {
            System.out.println("Open dialog to choose a file");
            int returnVal = fileChooser.showOpenDialog(GUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                pathF.setText(file.getAbsolutePath());
            }
        } else {
        tree.setModel(new DefaultTreeModel(bInt.bArbreToJTree()));
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);

        tree.updateUI();
    } }

    private void build() {
        setTitle("Indexation - B Arbre");
        setSize(760, 760);
        setLocationRelativeTo(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() {
        GridBagLayout gLayGlob = new GridBagLayout();

        JPanel pane1 = new JPanel();
        pane1.setLayout(gLayGlob);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 5, 2, 0);

        JLabel labelU = new JLabel("Nombre max de cl?s par noeud (2m): ");
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        pane1.add(labelU, c);

        txtU = new JTextField("4", 7);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 2;
        pane1.add(txtU, c);

        JLabel labelBetween = new JLabel("Nombre de clefs ? ajouter:");
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        pane1.add(labelBetween, c);

        txtNbreItem = new JTextField("10000", 7);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        pane1.add(txtNbreItem, c);


        buttonAddMany = new JButton("Ajouter n ?l?ments al?atoires ? l'arbre");
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(buttonAddMany, c);

        JLabel labelSpecific = new JLabel("Ajouter une valeur sp?cifique:");
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(labelSpecific, c);

        txtNbreSpecificItem = new JTextField("50", 7);
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(txtNbreSpecificItem, c);

        buttonAddItem = new JButton("Ajouter l'?l?ment");
        c.gridx = 2;
        c.gridy = 3;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(buttonAddItem, c);

        JLabel labelRemoveSpecific = new JLabel("Retirer une valeur sp?cifique:");
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(labelRemoveSpecific, c);

        removeSpecific = new JTextField("54", 7);
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(removeSpecific, c);

        buttonRemove = new JButton("Supprimer l'?l?ment n de l'arbre");
        c.gridx = 2;
        c.gridy = 4;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(buttonRemove, c);

        JLabel labelFilename = new JLabel("Nom de fichier : ");
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(labelFilename, c);

        txtFile = new JTextField("arbre.abr", 7);
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(txtFile, c);

        buttonSave = new JButton("Sauver l'arbre");
        c.gridx = 2;
        c.gridy = 5;
        c.weightx = 0.5;
        c.gridwidth = 1;
        pane1.add(buttonSave, c);
        buttonTest = new JButton("Check list ");
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(buttonTest, c);
        buttonLoad = new JButton("Charger l'arbre");
        c.gridx = 3;
        c.gridy = 5;
        c.weightx = 0.5;
        c.gridwidth = 1;
        pane1.add(buttonLoad, c);

        buttonClean = new JButton("Reset");
        c.gridx = 2;
        c.gridy = 6;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(buttonClean, c);

        buttonRefresh = new JButton("Refresh");
        c.gridx = 2;
        c.gridy = 7;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(buttonRefresh, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 400;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.gridwidth = 4;   //2 columns wide
        c.gridx = 0;
        c.gridy = 8;
      
        
        btnFileDial = new JButton("Open dialog to choose a file");
        c.gridx = 1;
        c.gridy = 8;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(btnFileDial, c);
        loadfile = new JButton("Load data file");
        c.gridx = 2;
        c.gridy = 8;
        c.weightx = 1;
        c.gridwidth = 2;
        pane1.add(loadfile, c);
        pathF = new JTextField("", 7);
        c.gridx = 0;
        c.gridy = 8;
        c.weightx = 1;
        c.gridwidth = 1;
        pane1.add(pathF, c);
        fileChooser = new JFileChooser();
        
        JScrollPane scrollPane = new JScrollPane(tree);
        pane1.add(scrollPane, c);

        tree.setModel(new DefaultTreeModel(null));
        tree.updateUI();
        buttonTest.addActionListener(this);
        txtNbreItem.addActionListener(this);
        buttonAddItem.addActionListener(this);
        buttonAddMany.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonRemove.addActionListener(this);
        buttonClean.addActionListener(this);
        buttonRefresh.addActionListener(this);
        loadfile.addActionListener(this);
        btnFileDial.addActionListener(this);
        return pane1;
    }
}

