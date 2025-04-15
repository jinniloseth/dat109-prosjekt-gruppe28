package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.Tilbakemelding;

@Repository
public interface TilbakemeldingRepo extends JpaRepository<Tilbakemelding, Integer> {

}
