package no.hvl.dat109;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tilbakemelding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tilbakemeldingID;

	private int tilbakemelding;

	@ManyToOne
	private Person student;
	
	public Tilbakemelding() {
		
	}

	public Tilbakemelding(int t, Person student) {
		tilbakemelding = t;
		this.student = student;
	}

	public int getTilbakemeldingID() {
		return tilbakemeldingID;
	}

	public int getTilbakemelding() {
		return tilbakemelding;
	}

	public Person getStudent() {
		return student;
	}
}