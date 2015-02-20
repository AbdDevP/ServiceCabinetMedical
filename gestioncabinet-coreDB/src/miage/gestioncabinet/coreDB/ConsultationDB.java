package miage.gestioncabinet.coreDB;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import miage.gestioncabinet.api.*;
import miage.gestioncabinet.coreDB.*;

@Entity
@Table(name="consultation")
@SequenceGenerator(name="consultation_id", sequenceName="consultation_id_seq", allocationSize=1)
public class ConsultationDB implements Consultation {

	
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name="id_consultation")
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="consultation_id")
		private Long id;
		
		@ManyToOne(targetEntity=PatientDB.class)
		@JoinColumn(name="id_patient")
		private Patient patient;
		
		@ManyToOne(targetEntity=MedecinBD.class)
		@JoinColumn(name="id_medecin")
		private Medecin medecin;
		
		private Calendar debut;
		private Calendar fin;
		private String compteRendu;
		
		//@OneToMany
		@JoinColumn(name="id_traitement")
		private List<Traitement> listeT;
		
		@OneToMany
		@JoinColumn(name="id_interaction")
		private List<InteractionDB> listeI;

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
		public Medecin getMedecin(){
			return this.medecin;
		}

		@Override
		public void setMedecin (Medecin m){
			this.medecin= m;
		}
		
		public Calendar getDebut(){
			return this.debut;
		}
		
		public void setDebut (Calendar d){
			this.debut = d;
		}
		
		public Calendar getFin(){
			return this.fin;
		}
		
		public void setFin(Calendar f){
			this.fin=f;
		}
		
		public String getCompteRendu(){
			return this.compteRendu;
		}
		
		public void setCompteRendu(String cr){
			this.compteRendu = cr;
		}
		
		public List<Traitement> getPrescription(){
			return this.listeT;
		}
		
		public Boolean ajouterTraitement(Produit p){
			if (this.listeT.contains(p))
			{
				return false;
			}
			else{
				TraitementDB traitement = new TraitementDB();
				traitement.setProduit(p);
				return this.listeT.add(traitement);
			}
		}
		
		public Boolean supprimerTraitement (Traitement medicament){
			return this.listeT.remove(medicament);
		}
		
		public List<InteractionDB> getListe() {
			return this.listeI;
		}

		public void setListe(List<InteractionDB> interactions) {
			this.listeI = interactions;
		}

		@Override
		public int compareTo(Consultation o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<Interaction> getInteractions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setInteractions(List<Interaction> interactions) {
			// TODO Auto-generated method stub
			
		}
		
		
}
