package miage.gestioncabinet.coreDB;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import miage.gestioncabinet.api.Utilisateur;

@Entity
@DiscriminatorValue("utilisateur")
public class UtilisateurDB extends PersonneDB implements Utilisateur {
	
	private static final long serialVersionUID = 1L;
	private String compte;
	
	public UtilisateurDB() {
	}

	public String getCompte(){
		return this.compte;
	}

}
