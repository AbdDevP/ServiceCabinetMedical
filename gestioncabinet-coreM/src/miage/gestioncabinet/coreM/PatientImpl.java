package miage.gestioncabinet.coreM;

import java.util.Calendar;
import java.util.Locale;

import miage.gestioncabinet.api.Patient;

public class PatientImpl extends PersonneImpl implements Patient {

	private static final long serialVersionUID = 1L;
	private Calendar dateNaissance;
	private Integer age;

	public PatientImpl() {
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
	public static void main(String[] args) {
		PatientImpl patient = new PatientImpl();
		patient.setNom("toto");
		Calendar c = Calendar.getInstance(Locale.FRANCE);
		patient.setDateNaissance(c);
		System.out.println("Age : " + patient.getAge());
	}*/

}
