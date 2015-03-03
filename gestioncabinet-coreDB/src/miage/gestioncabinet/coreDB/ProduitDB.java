package miage.gestioncabinet.coreDB;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import miage.gestioncabinet.api.Produit;

@Embeddable
public class ProduitDB implements Produit {

	private static final long serialVersionUID = 1L;
	private String cis;
	private String name;

	public ProduitDB() {

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

	@Override
	public String toString() {
		return "ProduitDB [cis=" + cis + ", name=" + name + "]";
	}

}
