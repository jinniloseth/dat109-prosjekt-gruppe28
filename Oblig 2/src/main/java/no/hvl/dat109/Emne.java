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
	
	@ManyToOne
	List<Forelesning> forelesninger;

	public Emne(String emnekode, List<Forelesning> forelesninger) {
		this.emnekode = emnekode;
		this.forelesninger = forelesninger;
	}
	
	public void getResultatForelesning(String emnekode) {
		Emne emne = emneRepo.findByEmnekode(emnekode);
		
	}
	
	public double getResultat(int forelesningsID) {
		return 
	}
	
	public void giVurdering(int studentID, int tilbakemelding) {
		
		
	}
	
	public String getEmnekode() {
		return emnekode;
	}
	
}
