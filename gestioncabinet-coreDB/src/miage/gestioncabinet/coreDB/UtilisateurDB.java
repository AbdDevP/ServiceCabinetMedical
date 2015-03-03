package miage.gestioncabinet.coreDB;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import miage.gestioncabinet.api.Utilisateur;

@Entity
@DiscriminatorValue("utilisateur")
public class UtilisateurDB extends PersonneDB implements Utilisateur {

	private static final long serialVersionUID = 1L;
	private String compte;
	private String mdp;

	public UtilisateurDB() {
		super();
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getCompte() {
		return this.compte;
	}

	public String toString() {
		return super.toString();
	}
}
