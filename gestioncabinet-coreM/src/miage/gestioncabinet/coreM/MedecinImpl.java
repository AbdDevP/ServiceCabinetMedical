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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rpps == null) ? 0 : rpps.hashCode());
		return result;
	}

	public boolean equals(Medecin obj) {
		return this.rpps.equals(obj.getRPPS());
	}

	
	
	
	
}
