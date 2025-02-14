package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.response.CreateResponse;
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

    public CreateResponse createPrenotazione(PrenotazioneRequest request) {
        Dipendente dipendente = dipendenteRepository.findById(request.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(request.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        List<Prenotazione> prenotazioni = prenotazioneRepository.findByDipendenteAndDataRichiesta(request.getDipendenteId(), request.getDataRichiesta());
        if (!prenotazioni.isEmpty()) {
            throw new IllegalStateException("Il dipendente ha già una prenotazione per questa data");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(request.getDataRichiesta());
        prenotazione.setNote(request.getNote());
        prenotazione.setPreferenze(request.getPreferenze());

        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);

        return new CreateResponse(savedPrenotazione.getId());
    }

    public List<PrenotazioneResponse> findAll() {
        return null;
    }

    public PrenotazioneResponse findById(Long id) {
        return null;
    }

    public PrenotazioneResponse modify(Long id, PrenotazioneRequest request) {
        return null;
    }

    public void delete(Long id) {
    }
}
