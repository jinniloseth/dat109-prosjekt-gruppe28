package no.hvl.dat109;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Forelesning {

	// en forelesning må ha en lektor?
	private LocalDate dato;
	private String tittel;

	@OneToMany(mappedBy = "forelesning")
	private Map<Integer, Integer> tilbakemeldinger;
	private double resultat;

	public Forelesning(String tittel, LocalDate dato) {
		this.dato = dato;
		this.tittel = tittel;
		tilbakemeldinger = new HashMap<>();
		resultat = 0;
	}

	public LocalDate getDato() {
		return dato;
	}

	public String getTittel() {
		return tittel;
	}

	public double getResultat() {
		double sum = 0;
		for (double i : tilbakemeldinger.values()) {
			sum += i;
		}
		resultat = sum / (double) tilbakemeldinger.size();
		return resultat;
	}

	public boolean giVurdering(int tilbakemelding, Person student) {
		if (1 <= tilbakemelding && tilbakemelding <= 5 && student != null && !student.erLektor() && !tilbakemeldinger.containsKey(student.getBrukernavn())) {
			tilbakemeldinger.put(student.getBrukernavn(), tilbakemelding);
			return true;
		}
		return false;
	}
}
