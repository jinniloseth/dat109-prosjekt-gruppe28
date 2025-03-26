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
	
	private Lektor lektor;
	
	private LocalDate dato;
	private LocalTime tidFra;
	private LocalTime tidTil;
	
	@OneToMany(mappedBy = "forelesning")
	private List<Tilbakemelding> tilbakemeldinger;
	
	public Forelesning(Lektor lektor, LocalDate dato, LocalTime tidFra, LocalTime tidTil) {
		this.lektor = lektor;
		this.dato = dato;
		this.tidFra = tidFra;
		this.tidTil = tidTil;
	}
	
	public int getForelesningsnr() {
		return forelesningsnr;
	}

	public Lektor getLektor() {
		return lektor;
	}

	public void setLektor(Lektor lektor) {
		this.lektor = lektor;
	}
	

	public LocalDate getDato() {
		return dato;
	}

	public LocalTime getTidFra() {
		return tidFra;
	}

	public LocalTime getTidTil() {
		return tidTil;
	}

	public List<Tilbakemelding> getTilbakemeldinger() {
		return tilbakemeldinger;
	}

	public List<Tilbakemelding> getTilbakemelding() {
		return tilbakemeldinger;
	}
	
	public boolean registrerTilbakemelding(Tilbakemelding tilbakemelding) {
		int brukernavn = tilbakemelding.getStudent().getBrukernavn();
		for (Tilbakemelding tm : tilbakemeldinger) {
			if (brukernavn == tm.getStudent().getBrukernavn()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String s = "Forelesningsnr: " + forelesningsnr + "\n" +
				   "Lektor: " + lektor + "\n";
		
		for(Tilbakemelding t : tilbakemeldinger) {
			s += t.toString();
		}
		
		return s;
	}
}

