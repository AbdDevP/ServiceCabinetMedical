package miage.gestioncabinet.coreDB;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Entity
@Table(name = "traitement")
public class TraitementDB implements Traitement {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_traitement")
	private Long id;

	@Embedded
	private ProduitDB prod;
	@Column(name = "poso")
	private String posologie;
	
	@Column(name="id_consultation")
	private Long id_consultation;

	public TraitementDB() {

	}

	public Produit getProduit() {
		return this.prod;
	}

	public void setProduit(Produit p) {
		this.prod = (ProduitDB) p;
	}

	public String getPosologie() {
		return this.posologie;
	}

	public void setPosologie(String po) {
		this.posologie = po;
	}

	public Long getId_consultation() {
		return id_consultation;
	}

	public void setId_consultation(Long id_consultation) {
		this.id_consultation = id_consultation;
	}

}
