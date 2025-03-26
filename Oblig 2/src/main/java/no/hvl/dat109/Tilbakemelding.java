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
    private int tilbakemeldingId;
	
	@ManyToOne
	private int tilbakemelding;
	
	@ManyToOne
	private int studentId;
	
	@ManyToOne
	private int emnekode;
	
	public Tilbakemelding() {
		
	}
	
	public Tilbakemelding(int t, int id) {
		tilbakemelding = t;
		studentId = id;
	}

	public int getTilbakemelding() {
		return tilbakemelding;
	}

	public void setTilbakemelding(int tilbakemelding) {
		this.tilbakemelding = tilbakemelding;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getEmneKode() {
		return emnekode;
	}

	public void setEmneKode(int emnekode) {
		this.emnekode = emnekode;
	}

	@Override
	public String toString() {
		return "Tilbakemelding: " + tilbakemelding + "\n" +
			   "StudentId " + studentId + "\n" + 
			   "Emnekode " + emnekode + "\n";
	}
	
	
	
}
