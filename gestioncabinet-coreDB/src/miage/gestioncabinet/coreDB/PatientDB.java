package miage.gestioncabinet.coreDB;

import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import miage.gestioncabinet.api.Patient;

@Entity
@DiscriminatorValue("patient")
@NamedQueries({
		@NamedQuery(name = "Patient.findbyNameAndBirthday", query = "select p from PatientDB p where p.dateNaissance = :dateNaissance and p.name = :name and p.prenom = :prenom "),
		@NamedQuery(name = "Patient.countPatientByNameAndBirthday", query = "select count(p) from PatientDB p where p.dateNaissance = :dateNaissance and p.name = :name and p.prenom = :prenom ") })
public class PatientDB extends PersonneDB implements Patient {

	private static final long serialVersionUID = 1L;
	@Column(name = "date_naissance")
	private Calendar dateNaissance;

	@Transient
	private Integer age;// valeur calculé et non persisté dans la bdd

	public PatientDB() {
		super();
	}

	public Calendar getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Calendar c) {
		this.dateNaissance = c;
		setAge();
	}

	private void setAge() {
		Calendar toDay = Calendar.getInstance(Locale.FRANCE);
		int age = toDay.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);
		if (toDay.get(Calendar.DAY_OF_YEAR) < dateNaissance
				.get(Calendar.DAY_OF_YEAR)) {
			age -= 1;
		}
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}

	/*
	 * public static void main(String[] args) { PatientImpl patient = new
	 * PatientImpl(); patient.setNom("toto"); Calendar c =
	 * Calendar.getInstance(Locale.FRANCE); patient.setDateNaissance(c);
	 * System.out.println("Age : " + patient.getAge()); }
	 */

}
