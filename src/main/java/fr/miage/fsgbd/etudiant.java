package main.java.fr.miage.fsgbd;

import java.util.Objects;



public class etudiant {
	 public etudiant(String numeroetudiant, String nom, String prenom, int age) {
	
		this.numeroetudiant = numeroetudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	 public etudiant() {
			super();
			this.numeroetudiant = null;
			this.nom = null;
			this.prenom = null;
			this.age = -1;
		}
	@Override
	public String toString() {
		return "etudiant [numeroetudiant=" + numeroetudiant + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age
				+ "]";
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        etudiant that = (etudiant) o;
        return age == that.age && Objects.equals(numeroetudiant, that.numeroetudiant) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom);
    }
	private String numeroetudiant;
	 private String nom;
	    private String prenom;
	    private int age;
	    public String getNumeroetudiant() {
		return numeroetudiant;
	}
	public void setNumeroetudiant(String numeroetudiant) {
		this.numeroetudiant = numeroetudiant;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
		
}
