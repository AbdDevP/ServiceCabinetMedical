package miage.gestioncabinet.coreDB;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import miage.gestioncabinet.api.Personne;

@Entity
@Table(name = "personne")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class PersonneDB implements Personne {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_personne")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String prenom;

	public PersonneDB() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNom() {
		return this.name;
	}

	public void setNom(String n) {
		this.name = n;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String p) {
		this.prenom = p;
	}

	@Override
	public String toString() {
		return "PersonneDB [name=" + name + ", prenom=" + prenom + "]";
	}

}
