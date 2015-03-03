package miage.gestioncabinet.coreDB;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import miage.gestioncabinet.api.*;

@Entity
@Table(name = "consultation")
@NamedQuery(name = "Consultation.findAllByMedecinAndDateDebAndDateEnd", query = "select c from ConsultationDB c where c.medecin = :medecin and c.date_rdv = :date_rdv and c.heure_debut >= :heure_deb and c.heure_fin < :heure_fin")
public class ConsultationDB implements Consultation {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_consultation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = PatientDB.class)
	@JoinColumn(name = "id_patient")
	private Patient patient;

	@ManyToOne(targetEntity = MedecinDB.class)
	@JoinColumn(name = "id_medecin")
	private Medecin medecin;

	private Time heure_debut;
	private Time heure_fin;
	private Date date_rdv;

	@Transient
	private Calendar debut;

	@Transient
	private Calendar fin;

	@Column(name = "compte_rendu")
	private String compteRendu;

	@OneToMany(targetEntity = TraitementDB.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_consultation")
	private Set<Traitement> listeT = new HashSet<Traitement>();

	@OneToMany(targetEntity = InteractionDB.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_inter_consultation")
	private Set<Interaction> listeI = new HashSet<Interaction>();

	public ConsultationDB() {
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
		setDate_rdv(new java.sql.Date(d.getTimeInMillis()));
		setHeure_debut(new java.sql.Time(d.getTimeInMillis()));
		this.debut = d;
	}

	@Override
	public Calendar getFin() {
		return this.fin;
	}

	@Override
	public void setFin(Calendar f) {
		setHeure_fin(new java.sql.Time(f.getTimeInMillis()));
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
		return new ArrayList<Traitement>(this.listeT);
	}

	@Override
	public Boolean ajouterTraitement(Produit p) {

		for (Traitement traitement : listeT) {
			if (traitement.getProduit().getCis().equals(p.getCis()))
				return false;
		}

		TraitementDB traitement = new TraitementDB();
		traitement.setProduit(p);
		traitement.setPosologie("");
		this.listeT.add(traitement);
		return true;

	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		return this.listeT.remove(medicament);
	}

	@Override
	public List<Interaction> getInteractions() {
		return new ArrayList<Interaction>(listeI);
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		this.listeI = new HashSet<Interaction>(interactions);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(Time heure_debut) {
		this.heure_debut = heure_debut;
	}

	public Time getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(Time heure_fin) {
		this.heure_fin = heure_fin;
	}

	public Date getDate_rdv() {
		return date_rdv;
	}

	public void setDate_rdv(Date date_rdv) {
		this.date_rdv = date_rdv;
	}

	public Set<Traitement> getListeT() {
		return listeT;
	}

	public void setListeT(Set<Traitement> listeT) {
		this.listeT = listeT;
	}

	public Set<Interaction> getListeI() {
		return listeI;
	}

	public void setListeI(Set<Interaction> listeI) {
		this.listeI = listeI;
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
