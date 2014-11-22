package miage.gestioncabinet.coreM;

import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.*;
import miage.gestioncabinet.coreM.*;

public class ConsultationImpl {

		private static final long serialVersionUID = 1L;
		private Patient patient;
		private Medecin medecin;
		private Calendar debut;
		private Calendar fin;
		private String compteRendu;
		private List<Traitement> listeT;
		private List<Interaction> listeI;

		
		public ConsultationImpl(Calendar debut) {
			super();
			this.debut = debut;
		}

		public Patient getPatient() {
			return this.patient;
		}

		public void setPatient(Patient p) {
			this.patient = p;
		}

		public Medecin getMedecin(){
			return this.medecin;
		}

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
				TraitementImpl traitement = new TraitementImpl();
				traitement.setProduit(p);
				return this.listeT.add(traitement);
			}
		}
		
		public Boolean supprimerTraitement (Traitement medicament){
			return this.listeT.remove(medicament);
		}
		
		public List<Interaction> getListeI() {
			return this.listeI;
		}


		public void setListeI(List<Interaction> interactions) {
			this.listeI = interactions;
		}
		
		
}
