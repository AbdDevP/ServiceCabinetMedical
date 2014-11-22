package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Utilisateur;

@Stateful
@Remote(PlanningRemoteService.class)
public class PlanningService implements PlanningRemoteService {

	private Utilisateur utilisateur;
	private List<Medecin> listeMedecin;
	private List<Patient> listePatient;
	private Calendar dateDebut;
	private Calendar dateFin;
	public static Medecin medecindefaut;
	private Medecin medecin;
	private List<Consultation> listeConsultations;
	private Consultation consultationcourrante;

	@Override
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		return null;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		List<Patient> listeretour = new ArrayList<Patient>();
		for (Patient patient : this.listePatient) {
			if (patient.getNom().equals(nom)
					&& patient.getPrenom().equals(prenom)
					&& patient.getDateNaissance().equals(dateNaissance)) {
				listeretour.add(patient);
			}
		}
		return listeretour;
	}

	@Override
	public Calendar getDateDebut() {
		// Calendar date = new Calendar.getInstance();
		String date = "2014-11-22";
		// return (this.dateDebut != null) ? this.dateDebut : date;
		// return date;
		return this.dateDebut;
	}

	@Override
	public void setDateDebut(Calendar date) {
		this.dateDebut = date;
	}

	@Override
	public Calendar getDateFin() {
		return this.dateFin;
	}

	@Override
	public void setDateFin(Calendar date) {
		this.dateFin = date;
	}

	@Override
	public Medecin getMedecin() {
		if (this.medecin == null) {
			return new MedecinImpl("Leroy", "Clara", "1", "008");
		}
		return this.medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public List<Consultation> listerRdv() {
		return this.listeConsultations;
	}

	@Override
	public Consultation getRdvCourant() {
		return this.consultationcourrante;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		this.consultationcourrante = rdv;

	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation newconsultation = (Consultation) new ConsultationImpl(date);
		this.consultationcourrante = newconsultation;
		return newconsultation;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		this.listeConsultations.add(this.consultationcourrante);
		return this.consultationcourrante;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		this.listeConsultations.remove(this.consultationcourrante);
		return;
	}

}
