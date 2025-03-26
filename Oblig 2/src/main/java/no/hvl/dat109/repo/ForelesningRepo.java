package no.hvl.dat109.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import no.hvl.dat109.Forelesning;

@Service
public interface ForelesningRepo extends JpaRepository<Forelesning, Integer> {
    Forelesning findByEmnekodeAndForelesningsnr(String emnekode, int forelesningsnr);
    List<Forelesning> findByEmnekode(String emnekode);
}
