package miage.gestioncabinet.coreDB;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

import miage.gestioncabinet.api.Patient;

@Entity
@Table(name="patient")
@DiscriminatorValue("patient")
public class PatientDB extends PersonneDB implements Patient {
	
	public PatientDB() {
	}

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
