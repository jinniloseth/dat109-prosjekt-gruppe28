package no.hvl.dat109;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Person {
	
	@Id
	private int brukernavn;
	
	private boolean erLektor;

	@ManyToMany() // må fikses
	private List<Emne> emner;

	public Person(int brukernavn, boolean erLektor, List<Emne> emner) {
		this.brukernavn = brukernavn;
		this.erLektor = erLektor;
		this.emner = emner;
	}
	public Person(int brukernavn, boolean b) {
		this.brukernavn = brukernavn;
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
