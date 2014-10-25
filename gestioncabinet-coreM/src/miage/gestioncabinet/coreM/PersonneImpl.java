package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Personne;

public class PersonneImpl implements Personne {
	
	private static final long serialVersionUID = 1L;
	String name;
	String prenom;
	
	public String getNom(){
		return name;
	}
	
	public void setNom(String n){
		this.name = n;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	public void setPrenom(String p){
		this.prenom = p;
	}

}
