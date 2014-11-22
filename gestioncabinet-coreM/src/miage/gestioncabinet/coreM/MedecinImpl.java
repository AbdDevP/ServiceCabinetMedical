package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Medecin;

public class MedecinImpl extends UtilisateurImpl implements Medecin {
	
	private static final long serialVersionUID = 1L;
	private String rpps;

	
	public String getRPPS(){
		return this.rpps;
	}

}
