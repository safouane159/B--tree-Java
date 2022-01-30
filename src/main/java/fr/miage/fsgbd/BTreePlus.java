package main.java.fr.miage.fsgbd;

import javax.swing.tree.DefaultMutableTreeNode;


/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 * @param <Type>
 */
public class BTreePlus<Type> implements java.io.Serializable {
    private Noeud<Type> racine;
 
 static Record[] records= new Record[10000];
 static int[] pointeur= new int[10000];



    public BTreePlus(int u, Executable e) {
        racine = new Noeud<Type>(u, e, null);
    }

    public void afficheArbre() {
        racine.afficheNoeud(true, 0);
    }

    /**
     * Méthode récursive permettant de récupérer tous les noeuds
     *
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode bArbreToJTree() {
        return bArbreToJTree(racine);
    }

    private DefaultMutableTreeNode bArbreToJTree(Noeud<Type> root) {
        StringBuilder txt = new StringBuilder();
        for (Type key : root.keys)
            txt.append(key.toString()).append(" ");

        DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode(txt.toString(), true);
        for (Noeud<Type> fil : root.fils)
            racine2.add(bArbreToJTree(fil));

        return racine2;
    }

    public static void addToPointeur(int key, int valeur) {
    	
    	pointeur[key] = valeur;
        
    }
    public static void affichePointeur() {
    	for (int i = 0; i < 200; i++) {
    	System.out.println("value of "+i +" is : "+  pointeur[i]);
    	}
    	
    	
    }

    public boolean addValeur(Type valeur) {
    	
    
        if (racine.contient(valeur) == null) {
            Noeud<Type> newRacine = racine.addValeur(valeur);
            if (racine != newRacine)
                racine = newRacine;
            return true;
        }
        return false;
    }
    
    public static void addRecord(int N_etudiant,String Nom_etudiant,String Prenom_etudiant,int inc) {
    
    	records[inc] = new Record(N_etudiant,Nom_etudiant,Prenom_etudiant);
    	
    	
    	
    }

    public void removeValeur(Type valeur) {
        System.out.println("Retrait de la valeur : " + valeur.toString());
        if (racine.contient(valeur) != null) {
            Noeud<Type> newRacine = racine.removeValeur(valeur, false);
            if (racine != newRacine)
                racine = newRacine;
        }
    }
}
