package no.hvl.dat109;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Person {

	@Override
	public String toString() {
	    return "Person{brukernavn=" + brukernavn + "}";
	}


	@Id
	private int brukernavn;

	@Column(name = "er_lektor", nullable = false)
	private boolean erLektor;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	    name = "person_emne",
	    joinColumns = @JoinColumn(name = "brukernavn"),
	    inverseJoinColumns = @JoinColumn(name = "emnenr")
	)
	private List<Emne> emner;

	public Person() {
	 
	}
	
	public Person(int brukernavn, boolean erLektor) {
		this.brukernavn = brukernavn;
		this.erLektor = erLektor;
		this.emner = List.of();
	}

	public Person(int brukernavn, boolean erLektor, List<Emne> emner) {
		this.brukernavn = brukernavn;
		this.erLektor = erLektor;
		this.emner = emner;
	}

	public int getBrukernavn() {
		return brukernavn;
	}

	public boolean erLektor() {
		return erLektor;
	}

	public List<Emne> getEmner() {
		return emner;
	}

	public void setEmner(List<Emne> emner) {
		this.emner = emner;
	}

	public boolean harEmne(Emne emne) {
		return emner.contains(emne);
	}

}
