package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.dipendente.Dipendente;
import it.epicode.gestione_viaggi.dipendente.DipendenteRepository;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import it.epicode.gestione_viaggi.viaggio.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public Prenotazione createPrenotazione(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note, String preferenze) {
        // Recupera il dipendente
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        // Recupera il viaggio
        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        // Controlla se il dipendente ha già una prenotazione per questa data
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByDipendenteAndDataRichiesta(dipendenteId, dataRichiesta);
        if (!prenotazioni.isEmpty()) {
            throw new IllegalStateException("Il dipendente ha già una prenotazione per questa data");
        }

        // Crea la prenotazione
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(dataRichiesta);
        prenotazione.setNote(note);
        prenotazione.setPreferenze(preferenze);

        return prenotazioneRepository.save(prenotazione);
    }
}
