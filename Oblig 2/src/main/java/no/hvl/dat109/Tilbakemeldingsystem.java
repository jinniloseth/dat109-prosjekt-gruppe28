package no.hvl.dat109;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.OneToMany;
import no.hvl.dat109.repo.ForelesningRepo;

public class Tilbakemeldingsystem {
	
	@Autowired
	ForelesningRepo forelesningRepository;
	
	@OneToMany
	private List<Forelesning> forelesninger;
	
	@OneToMany
	private List<Student> studenter;
	
	private List<String> alleEmnekoder = List.of("DAT100", "MAT101", "ING100",
												 "DAT102", "DAT107", "MAT104", "MAT110",
												 "DAT108", "DAT103", "MAT102",
												 "DAT110", "DAT109", "ING164");
	
	public Tilbakemeldingsystem() {

	}
	
	
	
	public List<Forelesning> getForelesninger() {
		return forelesninger;
	}



	public void setForelesninger(List<Forelesning> forelesninger) {
		this.forelesninger = forelesninger;
	}



	public List<Student> getStudenter() {
		return studenter;
	}



	public void setStudenter(List<Student> studenter) {
		this.studenter = studenter;
	}



	public List<String> getAlleEmnekoder() {
		return alleEmnekoder;
	}



	public void setAlleEmnekoder(List<String> alleEmnekoder) {
		this.alleEmnekoder = alleEmnekoder;
	}

	

	@Override
	public String toString() {
		String s = "Tilbakemeldingsystem: \n";
		
		
		for(Forelesning f : forelesninger) {
			s += f.toString() + " \n";
		}
		
		for(Student st : studenter) {
			s += st.toString() + " \n";
		}
		
		for(String e : alleEmnekoder) {
			s += e + " \n";
		}
		
		return s;
	}



	public HashMap<String, Integer> getResultatEnkelForelesning(String emnekode, int forelesningsnr) {

		HashMap<String, Integer> resultat = null;
		
		resultat.put("Bra", 0);
		resultat.put("Middels", 0);
		resultat.put("Dårlig", 0);
		
		Forelesning fo = forelesningRepository.findByEmnekodeAndForelesningsnr(emnekode, forelesningsnr);
		
		for(Forelesning f : forelesninger) {
			if(f.getEmnekode().equals(emnekode) && f.getForelesningsnr() == forelesningsnr) {
				fo = f;
			}
		}
		
		List<Tilbakemelding> tilbakemeldinger = fo.getTilbakemelding();
		
		for(Tilbakemelding t : tilbakemeldinger) {
			if(t.getTilbakemelding() == 1) {
				resultat.merge("Bra", 1, Integer::sum);
			} else if(t.getTilbakemelding() == 2) {
				resultat.merge("Middels", 1, Integer::sum);
			} else {
				resultat.merge("Dårlig", 1, Integer::sum);
			}
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
	
	
	
}
