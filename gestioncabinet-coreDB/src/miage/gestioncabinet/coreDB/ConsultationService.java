package miage.gestioncabinet.coreDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Stateful
@Remote(ConsultationRemoteService.class)
public class ConsultationService implements ConsultationRemoteService {

	private static final Integer DUREE_CONSULTATION = 30;
	private static int incr = 0;
	private Consultation consultation;// consultation courante
	@PersistenceContext(unitName = "gestioncabinet")
	private EntityManager em;
	@EJB
	private PrescriptionService prescriptionService;

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
		// verifier qu'un rdv n'existe pas à cette date
		Consultation c;
		// try{
		// Query c =
		// }catch(NoResultException e){
		//
		// }
		c = new ConsultationDB();
		c.setDebut(date);
		date.add(Calendar.MINUTE, this.DUREE_CONSULTATION);
		c.setFin(date);
		c.setInteractions(new ArrayList<Interaction>());// init
		c.setCompteRendu("");// init
		c.setPatient(new PatientDB());// init

		return c;
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Produit> listeProduits = new ArrayList<Produit>();
		Produit p = new ProduitDB();
		p.setNom(keyword);
		p.setCis("cis" + incr++);
		listeProduits.add(p);
		return listeProduits;
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		List<Traitement> traitements = this.consultation.getPrescription();
		prescriptionService.setTraitements(traitements);
		List<Interaction> interactions = prescriptionService
				.listerLesInteractions();
		this.consultation.setInteractions(interactions);

	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {

		// // regarder si le patient n'exsite pas deja
		// PatientDB p = (PatientDB) this.consultation.getPatient();
		// Query query = em.createNamedQuery("Patient.findbyNameAndBirthday");
		// query.setParameter("dateNaissance", p.getDateNaissance());
		// query.setParameter("prenom", p.getPrenom());
		// query.setParameter("name", p.getNom());
		// PatientDB pDb;
		// try {
		// pDb = (PatientDB) query.getSingleResult();
		// } catch (NoResultException e) {
		// em.persist(p);
		// pDb = p;
		// }
		// this.consultation.setPatient(pDb);

		ConsultationDB consultationDb = (ConsultationDB) this.consultation;
		if (consultationDb.getId() > 0) {
			/*
			 * consultationDb = em.find(ConsultationDB.class,
			 * consultationDb.getId());// on remet l'objet en mémoire pour //
			 * que hybernate puisse faire un // update
			 * consultationDb.setCompteRendu(consultation.getCompteRendu());
			 * consultationDb.set
			 */
			em.merge(consultationDb);
		} else {
			em.persist(consultationDb);
		}

		// prescriptionService.setTraitements(this.consultation.getPrescription());
		// prescriptionService.saveTraitements();

		return consultationDb;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		ConsultationDB cDB = em.find(ConsultationDB.class,
				((ConsultationDB) this.consultation).getId());
		em.remove(cDB);
		em.flush();
	}
}
