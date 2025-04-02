package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import no.hvl.dat109.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {
}
