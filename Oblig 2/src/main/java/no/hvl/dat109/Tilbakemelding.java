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
	
	@ManyToOne
	private int tilbakemelding;
	
	@ManyToOne
	private Student student;
	
	public Tilbakemelding(int t, Student student) {
		tilbakemelding = t;
		this.student = student;
	}

	public int getTilbakemeldingID() {
		return tilbakemeldingID;
	}
	
	public int getTilbakemelding() {
		return tilbakemelding;
	}

	public Student getStudent() {
		return student;
	}

	@Override
	public String toString() {
		return "Tilbakemelding: " + tilbakemelding + "\n" +
			   "Student " + student.getBrukernavn() + "\n";
	}
	
	
	
}
