package miage.gestioncabinet.coreDB;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import miage.gestioncabinet.api.Medecin;

@Entity
@DiscriminatorValue("medecin")
public class MedecinBD extends UtilisateurDB implements Medecin {
	
	private static final long serialVersionUID = 1L;
	private String rpps;
	
	public MedecinBD(){
	}
	
	public String getRPPS(){
		return this.rpps;
	}

}
