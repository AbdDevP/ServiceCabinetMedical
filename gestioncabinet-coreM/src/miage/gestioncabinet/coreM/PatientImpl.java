package miage.gestioncabinet.coreM;

import java.util.Calendar;

import miage.gestioncabinet.api.Patient;

public class PatientImpl extends PersonneImpl implements Patient {

	private static final long serialVersionUID = 1L;
	
	private Calendar date;
	private Integer age;
	
	
	public Calendar getDateNaissance(){
		return this.date;
	}
	
	public void setDateNaissance(Calendar c){
		this.date = c;
	}
	
	public Integer getAge(){
		return this.age;
	}
}
