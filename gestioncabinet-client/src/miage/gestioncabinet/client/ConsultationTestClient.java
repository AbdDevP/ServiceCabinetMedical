package miage.gestioncabinet.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;

public class ConsultationTestClient {
	/**
	 * L'interface distante de l'EJB
	 */
	private ConsultationRemoteService ejbConsultation;
	private PlanningRemoteService ejbPlanning;

	/**
	 * Constructeur avec lookup pour récupérer le proxy de l'EJB
	 * 
	 * @see java.lang.Object#Object()
	 */
	public ConsultationTestClient() {
		String serviceConsultation = "ejb:gestioncabinet-earDB/gestioncabinet-coreDB//ConsultationService!miage.gestioncabinet.api.ConsultationRemoteService?stateful";
		String servicePlanning = "ejb:gestioncabinet-earDB/gestioncabinet-coreDB//PlanningService!miage.gestioncabinet.api.PlanningRemoteService?stateful";
		try {
			ServiceLocator locator = ServiceLocator.INSTANCE;
			this.ejbPlanning = (PlanningRemoteService) locator
					.getRemoteInterface(servicePlanning);
			System.out.println(this.ejbPlanning);
		} catch (ServiceLocatorException e) {
			System.out.println("Le service " + servicePlanning
					+ " est introuvable");
			e.printStackTrace();
		}

		try {
			ServiceLocator locator = ServiceLocator.INSTANCE;
			this.ejbConsultation = (ConsultationRemoteService) locator
					.getRemoteInterface(serviceConsultation);
			System.out.println("ejb consultation : " + this.ejbConsultation);

		} catch (ServiceLocatorException e) {
			System.out.println("Le service " + serviceConsultation
					+ " est introuvable");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ConsultationTestClient app = new ConsultationTestClient();
		System.out.println("On développe un scénario de test de consultation");

		Medecin medecin = app.ejbPlanning.getMedecin();// pour initialiser un
														// medecin par defaut
		System.out.println("Sélection du médecin courant : " + medecin);

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		System.out.println("Planning du jour : du "
				+ df.format(app.ejbPlanning.getDateDebut().getTime()) + " au "
				+ df.format(app.ejbPlanning.getDateFin().getTime()));

		List<Consultation> rdvs = app.ejbPlanning.listerRdv();
		System.out
				.println("Rercherche les rendez-vous pris pour la journée de "
						+ medecin + " :");
		for (Consultation rdv : rdvs) {
			System.out.println("- " + rdv);
		}

		Consultation consultationCourante = rdvs.get(0);// Le medecin recoit la
														// première consultation
		app.ejbConsultation.setConsultation(consultationCourante);
		System.out.println("Le medecin " + medecin + " consulte le rdv : "
				+ consultationCourante);

		// ajouté un premier médicament
		try {
			List<Produit> listMedicaments = app.ejbConsultation
					.rechercherMedicament("doliprane");
			Produit medicament1 = listMedicaments.get(0);
			boolean ajoute = consultationCourante
					.ajouterTraitement(medicament1);
			if (ajoute) {
				System.out.println("le medicament " + medicament1.getNom()
						+ " a été ajouté à la prescription");
			} else {
				System.out.println("le medicament " + medicament1.getNom()
						+ " a déja été ajouté à la prescription");
			}
		} catch (GestionCabinetException e) {
			System.out.println("Le medicament n'existe pas ! ");
		}

		// ajouté un second médicament
		try {
			List<Produit> listMedicaments = app.ejbConsultation
					.rechercherMedicament("advil");
			Produit medicament2 = listMedicaments.get(0);
			boolean ajoute = consultationCourante
					.ajouterTraitement(medicament2);
			if (ajoute) {
				System.out.println("le medicament " + medicament2.getNom()
						+ " a été ajouté à la prescription");
			} else {
				System.out.println("le medicament " + medicament2.getNom()
						+ " a déja été ajouté à la prescription");
			}
		} catch (GestionCabinetException e) {
			System.out.println("Le medicament n'existe pas ! ");
		}
		app.ejbConsultation.setConsultation(consultationCourante);

		System.out.println("analyse des prescriptions");
		try {
			app.ejbConsultation.analyserPrescription();
		} catch (GestionCabinetException e1) {
			System.out
					.println("L'analyse de la prescription n'a pas pu être faite");
		}
		consultationCourante = app.ejbConsultation.getConsultation();
		app.ejbConsultation.setConsultation(consultationCourante);
		try {
			app.ejbConsultation.enregistrer();
			System.out.println("La consultation a été enregistré");
		} catch (GestionCabinetException e) {
			System.out.println("La consultation n'a pas été enregistré");
		}

		System.out.println();
		System.out.println("Revu de la consultation depuis le planning");
		rdvs = app.ejbPlanning.listerRdv();
		Consultation consultationFaite = rdvs.get(0);

		System.out.println("durant consultation " + consultationFaite
				+ " il a été prescrit");
		List<Traitement> prescriptions = consultationFaite.getPrescription();
		System.out.println(prescriptions.size() + " medicaments");
		for (Traitement tr : prescriptions) {
			System.out.println("- " + tr);
		}

		System.out.println("L'analyse de la consultation a révélé : ");
		List<Interaction> interactions = consultationFaite.getInteractions();
		System.out.println(interactions.size() + " interactions");
		for (Interaction in : interactions) {
			System.out.println("- " + in);
		}
		
		Traitement traitement1 = consultationFaite.getPrescription().get(0);
		consultationFaite.supprimerTraitement(traitement1);
		System.out.println("Suppression du traitement " + traitement1);

		app.ejbConsultation.setConsultation(consultationFaite);
		try {
			app.ejbConsultation.analyserPrescription();
		} catch (GestionCabinetException e) {
			System.out
					.println("L'analyse de la prescription n'a pas pu être faite");
		}
		try {
			app.ejbConsultation.enregistrer();
			System.out
					.println("La modification de la consultation a été enregistré");
		} catch (GestionCabinetException e) {
			System.out
					.println("La modification de la consultation n'a pas été enregistré");
		}
		
		System.out.println();
		System.out.println("Revu de la consultation depuis le planning pour verifier la suppression du medicament et interaction");
		rdvs = app.ejbPlanning.listerRdv();
		consultationFaite = rdvs.get(0);

		System.out.println("durant consultation " + consultationFaite
				+ " il a été prescrit");
		prescriptions = consultationFaite.getPrescription();
		System.out.println(prescriptions.size() + " medicaments");
		for (Traitement tr : prescriptions) {
			System.out.println("- " + tr);
		}

		System.out.println("L'analyse de la consultation a révélé : ");
		interactions = consultationFaite.getInteractions();
		System.out.println(interactions.size() + " interactions");
		for (Interaction in : interactions) {
			System.out.println("- " + in);
		}
		
		
		
	}
}