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
	private int forelesningnr;
	
	private LocalDate dato;
	private String tittel;

	@OneToMany
	private List<Tilbakemelding> tilbakemeldinger;
	
	private double resultat;
	
	public Forelesning() {
		
	}

	public Forelesning(LocalDate dato, String tittel) {
		this.dato = dato;
		this.tittel = tittel;
		resultat = 0;
	}
	
	public int getForelesningnr() {
		return forelesningnr;
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
		resultat = sum / (double) tilbakemeldinger.size();
		return resultat;
	}

	public boolean giVurdering(int tilbakemelding, Person student) {
		return  tilbakemeldinger.add(new Tilbakemelding(tilbakemelding, student));
	}
}