package fr.miage.fsgbd;


import fr.miage.fsgbd.NameGenerator;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //On crée une nouvelle instance de notre JDialog
              //GUI fenetre = new GUI();
              // fenetre.setVisible(true);
                SGBDconnect Add = new SGBDconnect();
                NameGenerator connect = new NameGenerator();

               /* try {
                    Add.createTable(Add.connectToDatabase());
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
               for (int i = 0; i < 10; i++)
                {
                try {
                Add.addListTest(connect.nssGenerator(),i);
                    Add.addRow(Add.connectToDatabase(),connect.nssGenerator(), connect.nameGenerator(), connect.lastNameGenerator());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                };
                try {
                    Add.testSequentielle(Add.connectToDatabase(),Add.getListTest());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }







		/*
		TestInteger testInt = new TestInteger();
		fr.miage.fsgbd.BTreePlus<Integer> bInt = new fr.miage.fsgbd.BTreePlus<Integer>(2, 4, testInt);
		
		
		int valeur;
		for(int i=0; i < 200; i++)
		{
			valeur =(int) (Math.random() * 250);	
			System.out.println("valeur " + valeur + " " + i );
			bInt.addValeur(valeur);		
			bInt.afficheArbre();
			
		}
		*/

		/*
		TestString test = new TestString();
		Noeud<String> noeud = new Noeud<String>(2, 5,test, null);
		Noeud<String> noeud1 = new Noeud<String>(2, 5,test, null);
		Noeud<String> noeud2 = new Noeud<String>(2, 5,test, null);
		*/
    });
};

};
