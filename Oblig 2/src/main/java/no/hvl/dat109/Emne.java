package no.hvl.dat109;

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
	
	@ManyToOne
	List<Forelesning> forelesninger;

	public Emne(String emnekode, String navn, String semester, List<Person> lektorer, List<Forelesning> forelesninger) {
		this.emnekode = emnekode;
		this.navn = navn;
		this.semester = semester;
		this.lektorer = lektorer;
		this.forelesninger = forelesninger;
	}
	
	public double getResultat() {
		double sum = 0;
		for (Forelesning f : forelesninger) {
			sum += f.getResultat();
		}
		return sum;
	}
	
	public double getResultat(int forelesningsnr) {
		for (Forelesning f : forelesninger) {
			if (forelesningsnr == f.getForelesningsnr()) {
				return f.getResultat();
			}
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
	
	public void giVurdering(int brukernavn, int tilbakemelding, int forelesningsID) {
		
		
	}
	
	public String getEmnekode() {
		return emnekode;
	}
	
}
