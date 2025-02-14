package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.dipendente.Dipendente;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    boolean existsByDipendenteAndViaggioAndDataRichiesta(Dipendente dipendente, Viaggio viaggio, LocalDate dataRichiesta);
}
