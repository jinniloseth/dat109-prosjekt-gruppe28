package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.Emne;

@Repository
public interface EmneRepo extends JpaRepository<Emne, Integer> {
    Emne findByEmnekode(String emnekode);
}
