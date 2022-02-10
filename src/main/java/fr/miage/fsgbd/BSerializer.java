package main.java.fr.miage.fsgbd;

import java.io.*;
	
	public class BSerializer<Type, ValueType>
	{	   
	  public BSerializer (BTreePlus<Type, ValueType> arbre, String path) 
	  {	    
	    try {
	      FileOutputStream fichier = new FileOutputStream(path);
	      ObjectOutputStream oos = new ObjectOutputStream(fichier);
	      oos.writeObject(arbre);
	      oos.flush();
	      oos.close();
	    }
	    catch (java.io.IOException e) {
	      e.printStackTrace();
	    }
	  }
	}