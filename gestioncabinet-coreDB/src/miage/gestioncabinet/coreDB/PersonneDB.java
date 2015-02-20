package miage.gestioncabinet.coreDB;

import javax.persistence.*;
import miage.gestioncabinet.api.Personne;

@Entity
@Table(name="personne")
@SequenceGenerator(name="personne_id", sequenceName="personne_id_seq", allocationSize=1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public abstract class PersonneDB implements Personne {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_personne")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="personne_id")
	private Long id;
	
	private String name;
	private String prenom;
	
	public PersonneDB() {
	}

	public String getNom(){
		return this.name;
	}
	
	public void setNom(String n){
		this.name = n;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public void setPrenom(String p){
		this.prenom = p;
	}

}
