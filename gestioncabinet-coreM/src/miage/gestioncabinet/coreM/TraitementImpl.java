package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

public class TraitementImpl  implements Traitement {

	private static final long serialVersionUID = 1L;
	private Produit prod;
	private String poso;
	
	public TraitementImpl(){
		
	}
	
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
