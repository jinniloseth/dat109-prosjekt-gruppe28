package no.hvl.dat109;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tilbakemelding {

	@Override
	public String toString() {
		return "Tilbakemelding [tilbakemeldingid=" + tilbakemeldingid + ", forelesning=" + forelesning
				+ ", tilbakemelding=" + tilbakemelding + ", student=" + student + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tilbakemeldingid;
	
	@ManyToOne
	@JoinColumn(name = "forelesningid")
	private Forelesning forelesning;

	private int tilbakemelding;

	@ManyToOne
	@JoinColumn(name = "student")
	private Person student;


	public Tilbakemelding(int tilbakemelding, Person student, Forelesning forelesning) {
		this.tilbakemelding = tilbakemelding;
		this.student = student;
		this.forelesning = forelesning;
	}

	public Tilbakemelding() {
	}

	public Tilbakemelding(int t, Person student) {
		tilbakemelding = t;
		this.student = student;
	}

	public int getTilbakemeldingID() {
		return tilbakemeldingid;
	}

	public int getTilbakemelding() {
		return tilbakemelding;
	}

	public Person getStudent() {
		return student;
	}
}