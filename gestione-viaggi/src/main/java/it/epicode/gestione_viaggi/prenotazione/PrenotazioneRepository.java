package it.epicode.gestione_viaggi.prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByDipendenteAndDataRichiesta(Long dipendenteId, LocalDate dataRichiesta);
}
