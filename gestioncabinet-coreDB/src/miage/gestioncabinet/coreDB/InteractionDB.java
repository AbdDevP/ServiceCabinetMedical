package miage.gestioncabinet.coreDB;

import javax.persistence.*;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

@Entity
@Table(name = "interaction")
public class InteractionDB implements Interaction {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_interaction")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "nom_produit_1")),
			@AttributeOverride(name = "cis", column = @Column(name = "cis_produit_1")) })
	private ProduitDB prod1;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "nom_produit_2")),
			@AttributeOverride(name = "cis", column = @Column(name = "cis_produit_2")) })
	private ProduitDB prod2;

	@Column(name = "severite")
	private String sev;
	@Column(name = "risques")
	private String risq;
	@Column(name = "precautions")
	private String prec;

	@Column(name = "id_inter_consultation")
	private Long id_consultation;

	public Produit getProduitA() {
		return this.prod1;
	}

	public void setProduitA(Produit produit) {
		this.prod1 = (ProduitDB) produit;
	}

	@Embedded
	public ProduitDB getProduitB() {
		return (ProduitDB) this.prod2;
	}

	public void setProduitB(Produit produit) {
		this.prod2 = (ProduitDB) produit;
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

	public Long getId_consultation() {
		return id_consultation;
	}

	public void setId_consultation(Long id_consultation) {
		this.id_consultation = id_consultation;
	}

	@Override
	public String toString() {
		return "InteractionDB [prod1=" + prod1 + ", prod2=" + prod2 + ", sev="
				+ sev + ", risq=" + risq + ", prec=" + prec + "]";
	}

}
