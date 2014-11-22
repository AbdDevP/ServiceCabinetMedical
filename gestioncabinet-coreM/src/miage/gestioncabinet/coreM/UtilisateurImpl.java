package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Utilisateur;

public class UtilisateurImpl extends PersonneImpl implements Utilisateur {
	
	private static final long serialVersionUID = 1L;
	private String compte;
	
	public UtilisateurImpl(String nom, String prenom, String compte) {
		super(nom, prenom);
		this.compte = compte;
	}

	public String getCompte(){
		return this.compte;
	}

}
