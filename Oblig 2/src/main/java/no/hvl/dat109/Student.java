package no.hvl.dat109;

import java.util.List;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

public class Student {

	private int studentId;
	
	@ManyToMany() //må fikses
	private List<Emne> emner;
	
	public Student() {
		
	}
	
	public Student(int studentId, List<Emne> emner) {
		this.studentId = studentId;
		this.emner = emner;
	}

	public int getStudentId() {
		return studentId;
	}

	public List<Emne> getEmner() {
		return emner;
	}

	public void setEmner(List<Emne> emner) {
		this.emner = emner;
	}
	
	@Override
	public String toString() {
		String s = "Student Id: " + studentId;
		
		for(Emne e : emner) {
			s += e.getEmnekode() + " \n";
		}
		
		return s;
	}

	
}
