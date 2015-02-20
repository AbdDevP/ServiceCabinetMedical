package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Utilisateur;

@Stateless
@Remote(PlanningRemoteService.class)
public class PlanningService implements PlanningRemoteService {

	private Utilisateur utilisateur;
	private List<Medecin> listeMedecin;
	private List<Patient> listePatient;
	private Calendar dateDebut;
	private Calendar dateFin;
	private Medecin medecin;
	@EJB
	private ConsultationService serviceConsultation;

	public PlanningService() {

		this.serviceConsultation = new ConsultationService();// initialisation
																// du service
																// consultation
		try {
			this.listeMedecin = rechercherMedecins();// récupère les médecins du
														// service
		} catch (GestionCabinetException e) {
			e.printStackTrace();
		}
		this.setMedecin(this.listeMedecin.get(0)); // medecin par défaut
		Calendar calDeb = Calendar.getInstance();
		calDeb.set(Calendar.HOUR, 9);
		this.setDateDebut(calDeb);
		Calendar calFin = Calendar.getInstance();
		calFin.set(Calendar.HOUR, 18);
		this.setDateFin(calFin);
	}

	@Override
	public Utilisateur getUtilisateur() {
		UtilisateurImpl u = new UtilisateurImpl();
		u.setNom("secrétaire");
		u.setPrenom("1");
		this.utilisateur = u;
		return this.utilisateur;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		if (listeMedecin == null) {
			listeMedecin = new ArrayList<Medecin>();
			MedecinImpl m1 = new MedecinImpl();
			m1.setNom("House");
			m1.setPrenom("Gregory");
			m1.setRPPS("rpps1");
			MedecinImpl m2 = new MedecinImpl();
			m2.setNom("Wilson");
			m2.setPrenom("James");
			m2.setRPPS("rpps2");
			listeMedecin.add(m1);
			listeMedecin.add(m2);
		}
		return listeMedecin;
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
		return this.medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public List<Consultation> listerRdv() {
		List<Consultation> consultations = serviceConsultation.consultationduservice;
		List<Consultation> consultationsDuMedecin = new ArrayList<Consultation>();
		for (Consultation consultation : consultations) {
			if (consultation.getMedecin().getRPPS().equals(medecin.getRPPS())) {
				consultationsDuMedecin.add(consultation);
			}
		}
		return consultationsDuMedecin;
	}

	@Override
	public Consultation getRdvCourant() {
		return serviceConsultation.getConsultation();
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		serviceConsultation.setConsultation(rdv);
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation c = serviceConsultation.creerRdv(date);
		c.setMedecin(this.medecin);
		c.setPatient(new PatientImpl());
		return c;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		return serviceConsultation.enregistrer();
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		serviceConsultation.supprimer();
	}

}
