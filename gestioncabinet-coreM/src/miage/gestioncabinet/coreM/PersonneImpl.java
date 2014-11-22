package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Personne;

public class PersonneImpl implements Personne {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String prenom;
	
	public PersonneImpl(String nom, String prenom) {
		this.name = nom;
		this.prenom = prenom;
	}

	public String getNom(){
		return this.name;
	}
	
	public void setNom(String n){
		this.name = n;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public void setPrenom(String p){
		this.prenom = p;
	}

}
