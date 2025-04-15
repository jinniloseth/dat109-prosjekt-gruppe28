package no.hvl.dat109;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Forelesning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int forelesningnr;
	
	private LocalDate dato;
	private String tittel;

	@OneToMany(mappedBy = "forelesning", cascade = CascadeType.ALL)
	private List<Tilbakemelding> tilbakemeldinger;

	private double resultat;
	
	 @ManyToOne
	 @JoinColumn(name = "emne_emnenr") // Matcher SQL: `emne_emnenr INTEGER REFERENCES emne(emnenr)`
	private Emne emne;
	
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

	@Override
	public String toString() {
		return "Forelesning [forelesningnr=" + forelesningnr + "]";
	}
		
	public double getResultat() {
		return resultat;
	    
	}

	
	public void oppdaterResultat(int tall) {
	    double sum = 0;
	    int antall = tilbakemeldinger.size();

	    for (Tilbakemelding t : tilbakemeldinger) {
	        sum += t.getTilbakemelding();
	    }

	    // Legg til ekstra vurdering
	    sum += tall;
	    antall += 1;

	    double gjennomsnitt = (antall > 0) ? sum / antall : 0;

	    resultat = gjennomsnitt;
	}



	public boolean giVurdering(int tilbakemelding, Person student) {
		return  tilbakemeldinger.add(new Tilbakemelding(tilbakemelding, student));
	}
}