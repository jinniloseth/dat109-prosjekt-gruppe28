package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import no.hvl.dat109.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	Student finnStudent(int brukernavn);
}
