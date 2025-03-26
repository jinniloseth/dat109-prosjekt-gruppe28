package no.hvl.dat109;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.OneToMany;
import no.hvl.dat109.repo.EmneRepo;

public class Tilbakemeldingsystem {
	
	@Autowired
	EmneRepo emneRepo;
	
	@OneToMany
	private List<Student> studenter;
	
	public Tilbakemeldingsystem() {

	}

	@Override
	public String toString() {
		String s = "Tilbakemeldingsystem: \n";
		
		
		for(Emne emne : emner) {
			s += emne.toString() + " \n";
		}
		
		for(Student st : studenter) {
			s += st.toString() + " \n";
		}
		
		for(String e : alleEmnekoder) {
			s += e + " \n";
		}
		
		return s;
	}



	public double getResultat(String emnekode, int forelesningsnr) {
		return finnEmne(emnekode).getResultat(int forelesningsnr);

		double resultat = null;
		
		Forelesning fo = forelesningRepository.findByEmnekodeAndForelesningsnr(emnekode, forelesningsnr);
		
		for(Forelesning f : forelesninger) {
			if(f.getEmnekode().equals(emnekode) && f.getForelesningsnr() == forelesningsnr) {
				fo = f;
			}
		}
		
		List<Tilbakemelding> tilbakemeldinger = fo.getTilbakemelding();
		
		for(Tilbakemelding t : tilbakemeldinger) {
			resultat += t.getTilbakemelding();
		}
		
		
		return resultat;
	}
	
	public void giVurdering(int studentId, String emnekode, int forelesningsnr, int tilbakemelding){
		Forelesning f = forelesninger.stream()
									 .filter(fo -> fo.getEmnekode().equals(emnekode))
									 .filter(a -> a.getForelesningsnr() == forelesningsnr)
									 .findFirst()
									 .orElse(null);
		
		if(f != null) {
			Tilbakemelding t = new Tilbakemelding(tilbakemelding, studentId);
			f.registrerTilbakemelding(t);
		}
	}
	
//	public List<Tilbakemelding> getTilbakemeldinger(String emnekode){
//		
//	}
	
	private Emne finnEmne(String emnekode) {
		for (Emne e : emner) {
			if (emnekode.equals(e.getEmnekode())) {
				return e;
			}
		}
		return null;
	}
	
}
