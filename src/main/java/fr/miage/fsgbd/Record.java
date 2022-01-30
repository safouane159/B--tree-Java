package main.java.fr.miage.fsgbd;

public class Record {
	
	private int Numero_etudiant;
	private String nom;
	private String prenom;
	
	
	
	
	
	public Record(int Numero_etudiant, String nom, String prenom) {
		super();
		this.Numero_etudiant = Numero_etudiant;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public int getNumeroSocial() {
		return Numero_etudiant;
	}
	public void Numero_etudiant(int Numero_etudiant) {
		this.Numero_etudiant = Numero_etudiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	

}
