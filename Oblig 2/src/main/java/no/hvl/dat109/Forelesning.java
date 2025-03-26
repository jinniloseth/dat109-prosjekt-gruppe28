package no.hvl.dat109;

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
    private int forelesningId;
	
	private String emnekode;

	private int forelesningsnr;
	
	private String foreleser;
	
	@OneToMany(mappedBy = "forelesning")
	private List<Tilbakemelding> tilbakemeldinger;
	
	public Forelesning() {
		
	}
	
	public Forelesning(String e, int nr) {
		emnekode = e;
		forelesningsnr = nr;
		foreleser = null;
	}
	
	public Forelesning(String e, int nr, String f) {
		emnekode = e;
		forelesningsnr = nr;
		foreleser = f;
	}
	
	public String getEmnekode() {
		return emnekode;
	}



	public void setEmnekode(String emnekode) {
		this.emnekode = emnekode;
	}



	public int getForelesningsnr() {
		return forelesningsnr;
	}



	public void setForelesningsnr(int forelesningsnr) {
		this.forelesningsnr = forelesningsnr;
	}



	public String getForeleser() {
		return foreleser;
	}



	public void setForeleser(String foreleser) {
		this.foreleser = foreleser;
	}



	public List<Tilbakemelding> getTilbakemelding() {
		return tilbakemeldinger;
	}



	public void setTilbakemelding(List<Tilbakemelding> tilbakemeldinger) {
		this.tilbakemeldinger = tilbakemeldinger;
	}

	@Override
	public String toString() {
		String s = "Forelesningsnr: " + forelesningsnr + "\n" +
				   "Emnekode: " + emnekode + "\n" +
				   "Foreleser: " + foreleser + "\n";
		
		for(Tilbakemelding t : tilbakemeldinger) {
			s += t.toString();
		}
		
		return s;
	}
	
	public void registrerTilbakemelding(Tilbakemelding t) {
		
		if(!sjekkGittTilbakemelding(t.getStudentId())) {
			tilbakemeldinger.add(t);
		}
	}
	
	public boolean sjekkGittTilbakemelding(int studentId) {

		for(Tilbakemelding t : tilbakemeldinger) {
			if(t.getStudentId() == studentId) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
}

