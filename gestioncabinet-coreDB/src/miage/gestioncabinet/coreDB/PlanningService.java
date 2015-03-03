package miage.gestioncabinet.coreDB;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
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
	private Medecin medecin;
	private static final String DEFAULT_MEDECIN_RPPS = "rpps1";
	@EJB
	private ConsultationRemoteService serviceConsultation;

	@PersistenceContext(unitName = "gestioncabinet")
	private EntityManager em;

	public PlanningService() {
	}

	@Override
	public Utilisateur getUtilisateur() {
		UtilisateurDB u = new UtilisateurDB();
		u.setNom("secrétaire");
		u.setPrenom("1");
		this.utilisateur = u;
		return this.utilisateur;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		try {
			// Medecin m = em.find(MedecinDB.class, 1);
			Query query = em.createNamedQuery("Medecin.findAll");
			this.listeMedecin = (List<Medecin>) query.getResultList();
		} catch (NoResultException e) {
			this.listeMedecin = new ArrayList<Medecin>();
		}
		return this.listeMedecin;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {

		Query query = em.createNamedQuery("Patient.findbyNameAndBirthday");
		query.setParameter("dateNaissance", dateNaissance);
		query.setParameter("prenom", prenom);
		query.setParameter("name", nom);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Patient>();
		}
	}

	@Override
	public Calendar getDateDebut() {
		if (this.dateDebut == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 00);
			calendar.set(Calendar.SECOND, 00);
			this.dateDebut = calendar;
		}
		return this.dateDebut;
	}

	@Override
	public void setDateDebut(Calendar date) {
		this.dateDebut = date;
	}

	@Override
	public Calendar getDateFin() {
		if (this.dateFin == null) {
			Calendar cal = (Calendar) getDateDebut().clone();
			cal.add(Calendar.HOUR, 8);
			this.dateFin = cal;
		}
		return this.dateFin;
	}

	@Override
	public void setDateFin(Calendar date) {
		this.dateFin = date;
	}

	@Override
	public Medecin getMedecin() {

		if (this.medecin == null) {
			try {
				this.medecin = this.rechercherMedecins().get(0);
			} catch (GestionCabinetException e) {
				e.printStackTrace();
			}
			// Query query = em.createNamedQuery("Medecin.findByRpps");
			// query.setParameter("rpps", DEFAULT_MEDECIN_RPPS);
			// Medecin defMedecin = (Medecin) query.getSingleResult();
			// this.medecin = defMedecin;
		}
		return this.medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public List<Consultation> listerRdv() {

		Query query = em
				.createNamedQuery("Consultation.findAllByMedecinAndDateDebAndDateEnd");
		query.setParameter("medecin", this.medecin);

		java.sql.Date dateSqlRdv = new java.sql.Date(
				this.dateDebut.getTimeInMillis());
		java.sql.Time heureSqlDeb = new java.sql.Time(
				this.dateDebut.getTimeInMillis());
		java.sql.Time heureSqlFin = new java.sql.Time(
				this.dateFin.getTimeInMillis());
		query.setParameter("date_rdv", dateSqlRdv);
		query.setParameter("heure_deb", heureSqlDeb);
		query.setParameter("heure_fin", heureSqlFin);

		return query.getResultList();
	}

	@Override
	public Consultation getRdvCourant() {
		return serviceConsultation.getConsultation();
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		if (this.serviceConsultation == null) {
			this.serviceConsultation = new ConsultationService();
		}
		this.serviceConsultation.setConsultation(rdv);
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		if (this.serviceConsultation == null) {
			this.serviceConsultation = new ConsultationService();
		}
		Consultation consultation = serviceConsultation.creerRdv(date);
		consultation.setMedecin(this.medecin);
		return consultation;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		Consultation c = this.serviceConsultation.getConsultation();
		Patient p = c.getPatient();
		List<Patient> patients = this.rechercherPatients(p.getNom(),
				p.getPrenom(), p.getDateNaissance());
		if (patients.size() > 0) {
			c.setPatient(patients.get(0));// on suppose que l'utilisateur
											// choisis toujours le premier
											// patient en cas de homonyme
		} else {
			em.persist(p);
			c.setPatient(p);
		}
		this.serviceConsultation.setConsultation(c);
		return serviceConsultation.enregistrer();
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		serviceConsultation.supprimer();
	}

}
