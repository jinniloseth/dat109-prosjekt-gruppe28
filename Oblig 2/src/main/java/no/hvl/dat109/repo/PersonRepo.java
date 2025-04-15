package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

}
