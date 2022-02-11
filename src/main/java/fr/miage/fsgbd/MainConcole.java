
package main.java.fr.miage.fsgbd;

import java.io.*;

public class MainConcole {

    public static void main(String args[]) {
        BufferedReader reader = null;
        TestInteger testInt = new TestInteger();
        BTreePlus<Integer, etudiant> bInt = new BTreePlus<Integer, etudiant>(4, testInt);
        try {
		//veuillez changer le chemin d'acces 
            reader = new BufferedReader(new FileReader(new File("C:\\Users\\KHALIFA\\Desktop\\hh\\ghgh\\donnees_etudiant.csv")));
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data successfully loaded !");

//        keyPointer<Integer, etudiant> result = bInt.rechercheArbreHomemade(5400);
//        System.out.println(result.getValue());

//        result = bInt.rechercheSequentielle(5400);
//        keyPointer<Integer, etudiant> result = bInt.rechercheSequentielle(5400);
//        System.out.println(result.getValue());

        
        
        //test de temps
        //1: rechercheArbre()
        long startTime = System.nanoTime();
        long minTime = 147483647;
        long maxTime = 0;
        for(int i=0; i<100; i++) {
            Integer val = (int)(Math.random() * (10000-1));
            long startTimeTmmp = System.nanoTime();
            bInt.rechercheArbreHomemade(val);
            long endTimeTmp = System.nanoTime();
            long timeElapsedTmp = endTimeTmp - startTimeTmmp;
            if(maxTime<timeElapsedTmp) {
                maxTime = timeElapsedTmp;
            }
            if(minTime>timeElapsedTmp){
                minTime = timeElapsedTmp;
            }
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        long moyenneParRecherche = timeElapsed/100;
        System.out.println();
        System.out.println("rechercheArbreHomemade() : " + moyenneParRecherche/1000 + " microsecondes d'execution en moyenne !");
        System.out.println("temps minimum : " + minTime/1000 + " microsecondes d'execution");
        System.out.println("temps maximum : " + maxTime/1000 + " microsecondes d'execution");

        //test de temps
        //2: rechercheArbre()
        startTime = System.nanoTime();
        minTime = 147483647;
        maxTime = 0;
        for(int i=0; i<100; i++) {
            Integer val = (int)(Math.random() * (10000-1));
            long startTimeTmmp = System.nanoTime();
            bInt.rechercheArbre(val);
            long endTimeTmp = System.nanoTime();
            long timeElapsedTmp = endTimeTmp - startTimeTmmp;
            if(maxTime<timeElapsedTmp) {
                maxTime = timeElapsedTmp;
            }
            if(minTime>timeElapsedTmp){
                minTime = timeElapsedTmp;
            }
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        moyenneParRecherche = timeElapsed/100;
        System.out.println();
        System.out.println("rechercheArbre() : " + moyenneParRecherche/1000 + " microsecondes d'execution en moyenne !");
        System.out.println("temps minimum : " + minTime/1000 + " microsecondes d'execution");
        System.out.println("temps maximum : " + maxTime/1000 + " microsecondes d'execution");

        //test de temps()
        //3: rechercheSequencielle
        startTime = System.nanoTime();
        minTime = 147483647;
        maxTime = 0;
        for(int i=0; i<100; i++) {
            Integer val = (int)(Math.random() * (10000-1));
            long startTimeTmmp = System.nanoTime();
            bInt.rechercheSequentielle(val);
            long endTimeTmp = System.nanoTime();
            long timeElapsedTmp = endTimeTmp - startTimeTmmp;
            if(maxTime<timeElapsedTmp) {
                maxTime = timeElapsedTmp;
            }
            if(minTime>timeElapsedTmp){
                minTime = timeElapsedTmp;
            }
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        moyenneParRecherche = timeElapsed/100;
        System.out.println();
        System.out.println("rechercheSequentielle() : " + moyenneParRecherche/1000 + " microsecondes d'execution en moyenne !");
        System.out.println("temps minimum : " + minTime/1000 + " microsecondes d'execution");
        System.out.println("temps maximum : " + maxTime/1000 + " microsecondes d'execution");

    }

}
