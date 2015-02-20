package miage.gestioncabinet.coreM;

import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.*;

public class ConsultationImpl implements Consultation {

	private static final long serialVersionUID = 1L;
	private Patient patient;
	private Medecin medecin;
	private Calendar debut;
	private Calendar fin;
	private String compteRendu;
	private List<Traitement> listeT;
	private List<Interaction> listeI;

	public ConsultationImpl() {
	}

	@Override
	public Patient getPatient() {
		return this.patient;
	}

	@Override
	public void setPatient(Patient p) {
		this.patient = p;
	}

	@Override
	public Medecin getMedecin() {
		return this.medecin;
	}

	@Override
	public void setMedecin(Medecin m) {
		this.medecin = m;
	}

	@Override
	public Calendar getDebut() {
		return this.debut;
	}

	@Override
	public void setDebut(Calendar d) {
		this.debut = d;
	}

	@Override
	public Calendar getFin() {
		return this.fin;
	}

	@Override
	public void setFin(Calendar f) {
		this.fin = f;
	}

	@Override
	public String getCompteRendu() {
		return this.compteRendu;
	}

	@Override
	public void setCompteRendu(String cr) {
		this.compteRendu = cr;
	}

	@Override
	public List<Traitement> getPrescription() {
		return this.listeT;
	}

	@Override
	public Boolean ajouterTraitement(Produit p) {

		for (Traitement traitement : listeT) {
			if (traitement.getProduit().getCis().equals(p.getCis()))
				return false;
		}

		TraitementImpl traitement = new TraitementImpl();
		traitement.setProduit(p);
		return this.listeT.add(traitement);

	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		return this.listeT.remove(medicament);
	}

	@Override
	public List<Interaction> getInteractions() {
		return listeI;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		this.listeI = interactions;
	}

	@Override
	public int compareTo(Consultation consultation) {
		if (this.medecin.getRPPS().equals(consultation.getMedecin().getRPPS())
				&& this.patient.getNom().equals(
						consultation.getPatient().getNom())
				&& this.patient.getPrenom().equals(
						consultation.getPatient().getPrenom())
				&& this.patient.getDateNaissance().equals(
						consultation.getPatient().getDateNaissance())
				&& this.debut.equals(consultation.getDebut())) {
			return 1;
		}
		return 0;
	}

}
