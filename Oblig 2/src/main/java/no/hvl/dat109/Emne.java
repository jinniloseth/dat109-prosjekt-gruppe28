package no.hvl.dat109;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import no.hvl.dat109.repo.EmneRepo;

@Entity
public class Emne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emnenr;

	private String emnekode;
	private String semester;
	private String navn;

	private double resultat;

	@OneToMany
	List<Forelesning> forelesninger;
	
	public Emne() {
		
	}

	public Emne(String emnekode, String navn, String semester, List<Forelesning> forelesninger) {
		this.emnekode = emnekode;
		this.navn = navn;
		this.semester = semester;
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
		Forelesning f = forelesninger.stream().filter(a -> a.getForelesningnr() == forelesningnr).findAny()
				.orElse(null);
		if (f != null) {
			return f.getResultat();
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

	public boolean giVurdering(int forelesningnr, int tilbakemelding, Person student) {
		if (0 < forelesningnr && forelesningnr < forelesninger.size() && student != null && !student.erLektor()
				&& student.harEmne(this)) {
			Forelesning f = forelesninger.stream().filter(a -> a.getForelesningnr() == forelesningnr).findAny()
					.orElse(null);
			if (f != null)
				return f.giVurdering(tilbakemelding, student);
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
