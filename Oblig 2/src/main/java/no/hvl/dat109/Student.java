package no.hvl.dat109;

import java.util.List;

import jakarta.persistence.OneToMany;

public class Student {

	private int studentId;
	
	@OneToMany
	private List<String> emnekoder;
	
	public Student() {
		
	}
	
	public Student(int studentId) {
		this.studentId = studentId;
		this.emnekoder = List.of();
	}
	
	
	public Student(int studentId, List<String> emnekoder) {
		this.studentId = studentId;
		this.emnekoder = emnekoder;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<String> getEmnekoder() {
		return emnekoder;
	}

	public void setEmnekoder(List<String> emnekoder) {
		this.emnekoder = emnekoder;
	}
	
	@Override
	public String toString() {
		String s = "Student Id: " + studentId;
		
		for(String e : emnekoder) {
			s += e + " \n";
		}
		
		return s;
	}

	
}
