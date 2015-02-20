package miage.gestioncabinet.coreDB;

import javax.persistence.*;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Entity
@Table(name="traitement")
@SequenceGenerator(name="traitement_id", sequenceName="traitement_id_seq", allocationSize=1)
public class TraitementDB  implements Traitement {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id_traitement")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="traitement_id")
	private Long id;
	@Embedded
	private Produit prod;
	private String poso;
	

	public Produit getProduit(){
		return this.prod;
	}
	
	public void setProduit(Produit p){
		this.prod = p;
	}
	
	public String getPosologie(){
		return this.poso;
	}
	
	public void setPosologie(String po){
		this.poso = po;
	}

}
