package no.hvl.dat109.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import no.hvl.dat109.Emne;

@Service
public interface EmneRepo extends JpaRepository<Emne, Integer> {
    Emne findByEmnekode(String emnekode);
}
