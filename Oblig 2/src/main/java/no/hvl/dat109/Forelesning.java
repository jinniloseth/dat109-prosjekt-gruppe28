package no.hvl.dat109;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
	private List<Tilbakemelding> tilbakemeldinger;

	public Forelesning(LocalDate dato, String tittel) {
		this.dato = dato;
		this.tittel = tittel;
	}

	public LocalDate getDato() {
		return dato;
	}

	public String getTittel() {
		return tittel;
	}

	public boolean registrerTilbakemelding(Tilbakemelding tilbakemelding) {
		int brukernavn = tilbakemelding.getStudent().getBrukernavn();
		for (Tilbakemelding tm : tilbakemeldinger) {
			if (brukernavn == tm.getStudent().getBrukernavn()) {
				return false;
			}
		}

		tilbakemeldinger.add(tilbakemelding);
		return true;
	}

	public double getResultat() {
		double sum = 0;
		for (Tilbakemelding t : tilbakemeldinger) {
			sum += t.getTilbakemelding();
		}
		return sum / (double) tilbakemeldinger.size();
	}

	public double getResultat(int tilbakemeldingID) {
		for (Tilbakemelding t : tilbakemeldinger) {
			if (tilbakemeldingID == t.getTilbakemeldingID())
				return t.getTilbakemelding();
		}
		return 0;
	}

	public boolean giVurdering(int tilbakemelding, Person student) {
		return tilbakemeldinger.add(new Tilbakemelding(tilbakemelding, student));
	}
}
