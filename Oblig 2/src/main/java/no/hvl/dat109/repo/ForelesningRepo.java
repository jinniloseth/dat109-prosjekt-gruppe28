package no.hvl.dat109.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.Emne;
import no.hvl.dat109.Forelesning;

@Repository
public interface ForelesningRepo extends JpaRepository<Forelesning, Integer> {
	Optional<Forelesning> findByForelesningnrAndEmne(Integer forelesningnr, Emne emne);
}
