package miage.gestioncabinet.coreDB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Traitement;

@Stateful
public class PrescriptionService {
	private List<Traitement> traitements;

	@PersistenceContext(unitName = "gestioncabinet")
	private EntityManager em;

	public List<Traitement> getTraitements() {
		return traitements;
	}

	public void setTraitements(List<Traitement> traitements) {
		this.traitements = traitements;
	}

	public List<Interaction> listerLesInteractions() {
		List<Interaction> interactions = new ArrayList<Interaction>();
		for (int i = 0; i < traitements.size(); i++) {
			Traitement t1 = traitements.get(i);
			for (int j = i + 1; j < traitements.size(); j++) {
				Traitement t2 = traitements.get(j);
				Interaction inter = new InteractionDB();
				inter.setProduitA(t1.getProduit());
				inter.setProduitB(t2.getProduit());
				inter.setPrecautions("");
				inter.setRisques("");
				inter.setSeverite("");
				interactions.add(inter);
			}
		}
		return interactions;
	}

}
