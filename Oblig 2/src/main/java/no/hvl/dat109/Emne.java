package no.hvl.dat109;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class Emne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emnenr;

	@Override
	public String toString() {
		return "Emne [emnenr=" + emnenr + "]";
	}

	private String emnekode;
	private String semester;
	private String navn;

	@OneToMany(mappedBy = "emne", fetch = FetchType.EAGER)
	@OrderBy("dato ASC")
	List<Forelesning> forelesninger;
	
	public Emne() {
		
	}

	public Emne(String emnekode, String navn, String semester, List<Forelesning> forelesninger) {
		this.emnekode = emnekode;
		this.navn = navn;
		this.semester = semester;
		this.forelesninger = forelesninger;
	}

	public double getResultat(int forelesningnr) {
		Forelesning f = forelesninger.stream().filter(a -> a.getForelesningnr() == forelesningnr).findAny()
				.orElse(null);
		if (f != null) {
			return f.getResultat();
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

	public String getEmnekode() {
		return emnekode;
	}

	public Forelesning finnForelesning(int forelesningnr) {
		return forelesninger.get(forelesningnr - 1);
	}

	public int antallForelesninger() {
		return forelesninger.size();
	}

	public List<Forelesning> getForelesninger() {
		return forelesninger;
	}
}
