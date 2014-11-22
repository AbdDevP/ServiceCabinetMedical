package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

public class InteractionImpl implements Interaction {

	private static final long serialVersionUID = 1L;
	Produit prod1;
	Produit prod2;
	String sev;
	String risq;
	String prec;
	
	
	public Produit getProduitA() {
		return prod1;
	}


	public void setProduitA(Produit produit) {
		this.prod1 = produit;
	}


	public Produit getProduitB() {
		return prod2;
	}


	public void setProduitB(Produit produit) {
		this.prod2 = produit;
	}


	public String getSeverite() {
		return sev;
	}


	public void setSeverite(String severite) {
		this.sev = severite;
	}


	public String getRisques() {
		return risq;
	}


	public void setRisques(String risques) {
		this.risq = risques;
	}


	public String getPrecautions() {
		return prec;
	}


	public void setPrecautions(String precautions) {
		this.prec = precautions;
	}

}
