package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Medecin;

public class MedecinImpl extends UtilisateurImpl implements Medecin {
	
	private static final long serialVersionUID = 1L;
	private String rpps;
	
	
	public MedecinImpl(String nom, String prenom, String compte, String rpps){
		super(nom, prenom, compte);
		this.rpps = rpps;
	}
	
	public String getRPPS(){
		return this.rpps;
	}

}
