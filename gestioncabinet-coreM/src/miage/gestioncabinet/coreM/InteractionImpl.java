package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

public class InteractionImpl implements Interaction{

	Produit produitA;
	Produit produitB;
	String severite;
	String risques;
	String precautions;
	
	private static final long serialVersionUID = 1L;

	@Override
	public Produit getProduitA() {
		return this.produitA;
	}

	@Override
	public void setProduitA(Produit produit) {
		this.produitA = produit;
	}

	@Override
	public Produit getProduitB() {
		return produitB;
	}

	@Override
	public void setProduitB(Produit produit) {
		produitB = produit;
	}

	@Override
	public String getSeverite() {
		return this.severite;
	}

	@Override
	public void setSeverite(String severite) {
		this.severite = severite;	
	}

	@Override
	public String getRisques() {
		return this.risques;
	}

	@Override
	public void setRisques(String risques) {
		this.risques = risques;
		
	}

	@Override
	public String getPrecautions() {
		return this.precautions;
	}

	@Override
	public void setPrecautions(String precautions) {
		this.precautions = precautions;
		
	}

}
