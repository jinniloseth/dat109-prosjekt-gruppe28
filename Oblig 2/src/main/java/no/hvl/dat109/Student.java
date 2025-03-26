package no.hvl.dat109;

import java.util.List;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

public class Student {

	private int brukernavn;
	
	@ManyToMany() //må fikses
	private List<Emne> emner;
	
	public Student(int brukernavn, List<Emne> emner) {
		this.brukernavn = brukernavn;
		this.emner = emner;
	}

	public int getBrukernavn() {
		return brukernavn;
	}

	public List<Emne> getEmner() {
		return emner;
	}

	public void setEmner(List<Emne> emner) {
		this.emner = emner;
	}
	
	@Override
	public String toString() {
		String s = "Student Id: " + brukernavn;
		
		for(Emne e : emner) {
			s += e.getEmnekode() + " \n";
		}
		
		return s;
	}

	
}
