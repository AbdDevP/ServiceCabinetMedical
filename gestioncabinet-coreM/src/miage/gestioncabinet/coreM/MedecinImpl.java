package miage.gestioncabinet.coreM;

import java.util.Calendar;

import miage.gestioncabinet.api.Medecin;

public class MedecinImpl extends UtilisateurImpl implements Medecin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rpps;

	public MedecinImpl() {
		super();
	}

	public String getRPPS() {
		return this.rpps;
	}
	
	public void setRPPS(String rpps){
		this.rpps = rpps;	
	}


	
	
}
