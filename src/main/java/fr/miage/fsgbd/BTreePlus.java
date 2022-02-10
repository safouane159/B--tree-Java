package main.java.fr.miage.fsgbd;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;





/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 * @param <Type, ValueType>
 */
public class BTreePlus<Type, ValueType>  implements java.io.Serializable {
    private Noeud<Type, ValueType>  racine;
 
 static Record[] records= new Record[10000];
 static int[] pointeur= new int[10000];



    public BTreePlus(int u, Executable e) {
        racine = new Noeud<Type, ValueType> (u, e, null);
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

    private DefaultMutableTreeNode bArbreToJTree(Noeud<Type, ValueType> root) {
        StringBuilder txt = new StringBuilder();
        for (keyPointer<Type, ValueType> key : root.keys)
            txt.append(key.toString()).append(" ");

        DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode(txt.toString(), true);
        for (Noeud<Type,ValueType > fil : root.fils)
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

    public boolean addValeur(keyPointer<Type, ValueType> valeur) {
    	
    
        if (racine.contient(valeur.getKey()) == null) {
            Noeud<Type, ValueType> newRacine = racine.addValeur(valeur);
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
            Noeud<Type, ValueType> newRacine = racine.removeValeur(valeur, false);
            if (racine != newRacine)
                racine = newRacine;
        }
    }
    public keyPointer<Type, ValueType> rechercheArbre(Type valeur) {
        Noeud<Type, ValueType> noeud = this.racine.contient(valeur);
        if (noeud != null) {
            for (keyPointer<Type, ValueType> keyvalue : noeud.keys) {
                if (keyvalue.getKey().equals(valeur)) return keyvalue;
            }
        }
        return null;
    }

    public keyPointer<Type, ValueType> rechercheArbreHomemade(Type valeur){
        Noeud<Type, ValueType> noeud = this.racine;
        keyPointer<Type, ValueType> result = null;

        while(result == null){
            int index = 0;
            for(keyPointer<Type, ValueType> kv : noeud.keys){
                if(valeur.equals(kv.getKey())){
                    result = kv;
                    break;
                }

                if(noeud.compare(valeur, kv.getKey())) break;
                else index++;
            }

            if(noeud.fils.size() == 0 || noeud.fils.size() < index) break;
            else noeud = noeud.fils.get(index);
        }

        return result;
    }

    public keyPointer<Type, ValueType> rechercheSequentielle(Type valeur) {
        Stack<Noeud<Type, ValueType>> stack = new Stack();

        Noeud<Type, ValueType> noeud = this.racine;

        do {
            for (keyPointer<Type, ValueType> keyvalue : noeud.keys) {
                if (keyvalue.getKey().equals(valeur)) return keyvalue;
            }

            if (noeud.fils != null && noeud.fils.size() > 0) {
                if (stack.addAll(noeud.fils)) /*System.out.println("Add to stack")*/;
                //else System.out.println("Error couldnt add to the stack");
            }

//            System.out.println("Pop stack");
            noeud = stack.pop();
        } while (!stack.empty());
        return null;
    }
}

