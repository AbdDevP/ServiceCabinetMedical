package miage.gestioncabinet.coreDB;

import javax.persistence.*;

import miage.gestioncabinet.api.Medecin;

@Entity
@DiscriminatorValue("medecin")
@NamedQueries({
		@NamedQuery(name = "Medecin.findAll", query = "select m from MedecinDB m"),
		@NamedQuery(name = "Medecin.findByRpps", query = "select m from MedecinDB m where m.RPPS = :rpps") })
public class MedecinDB extends UtilisateurDB implements Medecin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "rpps")
	private String RPPS;

	public MedecinDB() {
		super();
	}

	public String getRPPS() {
		return this.RPPS;
	}

	public void setRPPS(String rpps) {
		this.RPPS = rpps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RPPS == null) ? 0 : RPPS.hashCode());
		return result;
	}

	public boolean equals(Medecin obj) {
		return this.RPPS.equals(obj.getRPPS());
	}

}
