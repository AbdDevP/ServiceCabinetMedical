package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Stateful
public class ConsultationService implements ConsultationRemoteService {

	private Consultation consultation;// consultation courante
	public List<Consultation> consultationduservice = new ArrayList<Consultation>();

	@Override
	public Consultation getConsultation() {
		return this.consultation;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation c = new ConsultationImpl();
		c.setDebut(date);
		return c;
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Traitement> traitements = this.consultation.getPrescription();
		List<Produit> listeProduits = new ArrayList<Produit>();
		for (Traitement traitement : traitements) {
			if (traitement.getProduit().getNom().equals(keyword)) {
				listeProduits.add(traitement.getProduit());
			}
		}
		return listeProduits;
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		/*
		 * List<Interaction> interactions = new ArrayList<Interaction>();
		 * List<Traitement> traitements = this.consultation.getPrescription();
		 * for (Traitement traitement : traitements) { Interaction i = new
		 * InteractionImpl(); i. }
		 */
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		consultationduservice.add(this.consultation);
		return this.consultation;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		consultationduservice.remove(this.consultation);
	}

}
