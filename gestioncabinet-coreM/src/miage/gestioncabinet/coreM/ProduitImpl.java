package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Produit;

public class ProduitImpl implements Produit {

	private static final long serialVersionUID = 1L;
	private String cis;
	private String name;

	public ProduitImpl() {

	}

	public String getCis() {
		return this.cis;
	}

	public void setCis(String c) {
		this.cis = c;
	}

	public String getNom() {
		return this.name;
	}

	public void setNom(String n) {
		this.name = n;
	}

}
