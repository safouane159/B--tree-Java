package main.java.fr.miage.fsgbd;

import java.io.*;

public class BDeserializer<Type , ValueType> 
{	
	  public  BTreePlus<Type , ValueType> getArbre(String path) 
	  {
		BTreePlus<Type, ValueType> arbre = null; 
	    try {
	      
	      FileInputStream fichier = new FileInputStream(path);
	      ObjectInputStream ois = new ObjectInputStream(fichier);
	      arbre = (BTreePlus<Type ,ValueType>) ois.readObject();
	      
	    } 
	    catch (java.io.IOException e) {
	      e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	    return arbre;
	   }
	
}

