package no.hvl.dat109;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import no.hvl.dat109.repo.EmneRepo;

@Entity
public class Emne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emnenr;

	private String emnekode;
	private String semester;
	private String navn;
	private List<Person> lektorer;
	private double resultat;

	@ManyToOne
	List<Forelesning> forelesninger;

	public Emne(String emnekode, String navn, String semester, List<Person> lektorer, List<Forelesning> forelesninger) {
		this.emnekode = emnekode;
		this.navn = navn;
		this.semester = semester;
		this.lektorer = lektorer;
		this.forelesninger = forelesninger;
		this.resultat = 0;
	}

	public double getResultat() {
		double sum = 0;
		for (Forelesning f : forelesninger) {
			sum += f.getResultat();
		}
		resultat = sum / (double) forelesninger.size();
		return resultat;
	}

	public double getResultat(int forelesningnr) {
		if (forelesningnr < forelesninger.size()) {
			return forelesninger.get(forelesningnr - 1).getResultat();
		}
		return 0;
	}

	public int getEmnenr() {
		return emnenr;
	}

	public String getNavn() {
		return navn;
	}

	public String getSemester() {
		return semester;
	}

	public List<Person> getLektorer() {
		return lektorer;
	}

	public boolean giVurdering(int forelesningnr, int tilbakemelding, Person student) {
		if (forelesningnr < forelesninger.size()) {
			return forelesninger.get(forelesningnr - 1).giVurdering(tilbakemelding, student);
		}
		return false;
	}

	public String getEmnekode() {
		return emnekode;
	}

	public Forelesning finnForelesning(int forelesningnr) {
		return forelesninger.get(forelesningnr - 1);
	}

	public int antallForelesninger() {
		return forelesninger.size();
	}
}
