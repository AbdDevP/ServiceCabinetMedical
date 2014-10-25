package miage.gestioncabinet.coreM;

import java.util.Calendar;

import miage.gestioncabinet.api.Patient;

public class PatientImpl extends PersonneImpl implements Patient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Calendar date;
	Integer age;
	
	
	public Calendar getDateNaissance(){
		return date;
	}
	
	public void setDateNaissance(Calendar c){
		this.date = c;
	}
	
	public Integer getAge(){
		return age;
	}
}
