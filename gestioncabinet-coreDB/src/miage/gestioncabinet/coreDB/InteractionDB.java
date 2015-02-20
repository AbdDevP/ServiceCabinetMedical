package miage.gestioncabinet.coreDB;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;


@Entity
@Table(name="interaction")
@SequenceGenerator(name="interaction_id", sequenceName="interaction_id_seq", allocationSize=1)
public class InteractionDB implements Interaction {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_interaction")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="interaction_id")
	private Long id;
	private Produit prod1;
	private Produit prod2;
	private String sev;
	private String risq;
	private String prec;
	
	
	public Produit getProduitA() {
		return this.prod1;
	}


	public void setProduitA(Produit produit) {
		this.prod1 = produit;
	}


	public Produit getProduitB() {
		return this.prod2;
	}


	public void setProduitB(Produit produit) {
		this.prod2 = produit;
	}


	public String getSeverite() {
		return this.sev;
	}


	public void setSeverite(String severite) {
		this.sev = severite;
	}


	public String getRisques() {
		return this.risq;
	}


	public void setRisques(String risques) {
		this.risq = risques;
	}


	public String getPrecautions() {
		return this.prec;
	}


	public void setPrecautions(String precautions) {
		this.prec = precautions;
	}

}
