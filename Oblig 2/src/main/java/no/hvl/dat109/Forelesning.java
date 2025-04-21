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
	@JoinColumn(name = "emne") // Matcher SQL: `emne_emnenr INTEGER REFERENCES emne(emnenr)`
	private Emne emne;

	public Forelesning() {

	}

	public Forelesning(LocalDate dato, String tittel) {
		this.dato = dato;
		this.tittel = tittel;
	}
	
	public Emne getEmne() {
		return emne;
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

	@Override
	public String toString() {
		return "Forelesning [forelesningnr=" + forelesningnr + "]";
	}

	public void setResultat(double r) {
		resultat = r;
	}

	public double getResultat() {

		return resultat;

	}
	
	public boolean harGittTilbakemelding(Person p) {
	    return tilbakemeldinger.stream()
	            .anyMatch(t -> t.getStudent().equals(p));
	}


	public void setEmne(Emne e) {
		emne = e;
	}

	public boolean giVurdering(Tilbakemelding tilbakemelding, Person student, Forelesning f) {
		Tilbakemelding t = new Tilbakemelding(tilbakemelding.getTilbakemelding(), student, f);
		boolean lagtTil = tilbakemeldinger.add(t);

		if (lagtTil) {
			double gjennomsnitt = tilbakemeldinger.stream().mapToInt(Tilbakemelding::getTilbakemelding).average()
					.orElse(0);
			this.resultat = gjennomsnitt;
		}

		return lagtTil;
	}

}