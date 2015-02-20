package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Stateful
@LocalBean
public class ConsultationService implements ConsultationRemoteService {

	private Consultation consultation;// consultation courante
	public static List<Consultation> consultationduservice = new ArrayList<Consultation>();

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

		for (int i = 0; i < consultationduservice.size(); i++) {
			Consultation cons = consultationduservice.get(i);
			if (cons.compareTo(this.consultation) == 1) {
				consultationduservice.remove(i);
			}
		}
	}

}
