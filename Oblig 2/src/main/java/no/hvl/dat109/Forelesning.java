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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int forelesningsnr;

	// en forelesning må ha en lektor?
	private LocalDate dato;

	@OneToMany(mappedBy = "forelesning")
	private List<Tilbakemelding> tilbakemeldinger;

	public Forelesning(LocalDate dato) {
		this.dato = dato;
	}

	public int getForelesningsnr() {
		return forelesningsnr;
	}

	public LocalDate getDato() {
		return dato;
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

	@Override
	public String toString() {
		String s = "Forelesningsnr: " + forelesningsnr + "\n" + "Lektor: "; // må fikses

		for (Tilbakemelding t : tilbakemeldinger) {
			s += t.toString();
		}

		return s;
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
}
