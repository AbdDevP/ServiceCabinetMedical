package miage.gestioncabinet.coreDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Stateful
public class ConsultationService implements ConsultationRemoteService {

	private Consultation consultation;

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
		Consultation con = (Consultation) new ConsultationDB();
		con.setDebut(date);
		return con;
	
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Traitement> traitements  = this.consultation.getPrescription();
		List<Produit> listeProduits = new ArrayList<Produit>();
		for (Traitement traitement : traitements) {
			if(traitement.getProduit().getNom().equals(keyword)){
				listeProduits.add(traitement.getProduit());
			}
		}
		return listeProduits;
		
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		// TODO Auto-generated method stub

	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		// TODO Auto-generated method stub

	}

}
