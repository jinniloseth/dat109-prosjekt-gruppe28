package no.hvl.dat109;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import no.hvl.dat109.repo.EmneRepo;

@Entity
public class Emne {

	@Id
	private String emnekode;
	
	private String navn;
	
	@ManyToOne
	List<Forelesning> forelesninger;

	public Emne(String emnekode, String navn, List<Forelesning> forelesninger) {
		this.emnekode = emnekode;
		this.navn = navn;
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
	
	public String getNavn() {
		return navn;
	}
	
	public void giVurdering(int studentID, int tilbakemelding, int forelesningsID) {
		
		
	}
	
	public String getEmnekode() {
		return emnekode;
	}
	
}
