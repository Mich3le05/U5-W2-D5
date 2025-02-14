package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.response.CreateResponse;
import it.epicode.gestione_viaggi.dipendente.Dipendente;
import it.epicode.gestione_viaggi.dipendente.DipendenteRepository;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import it.epicode.gestione_viaggi.viaggio.ViaggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public PrenotazioneDetailResponse prenotazioneDetailResponseFromPrenotazione(Prenotazione prenotazione) {
        PrenotazioneDetailResponse response = new PrenotazioneDetailResponse();
        response.setId(prenotazione.getId());
        response.setDataRichiesta(prenotazione.getDataRichiesta());
        response.setNote(prenotazione.getNote());
        response.setPreferenze(prenotazione.getPreferenze());
        response.setDipendente(prenotazione.getDipendente());
        response.setViaggio(prenotazione.getViaggio());
        return response;
    }

    public Page<PrenotazioneDetailResponse> findAll(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable).map(this::prenotazioneDetailResponseFromPrenotazione);
    }

    public PrenotazioneDetailResponse findById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        return prenotazioneDetailResponseFromPrenotazione(prenotazione);
    }

    public CreateResponse save(PrenotazioneRequest prenotazioneRequest) {
        Dipendente dipendente = dipendenteRepository.findById(prenotazioneRequest.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        Viaggio viaggio = viaggioRepository.findById(prenotazioneRequest.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndViaggioAndDataRichiesta(dipendente, viaggio, prenotazioneRequest.getDataRichiesta())) {
            throw new IllegalStateException("Il dipendente ha già una prenotazione per questa data e viaggio");
        }

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(prenotazioneRequest, prenotazione);
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);

        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);

        return new CreateResponse(savedPrenotazione.getId());
    }

    public PrenotazioneDetailResponse modify(Long id, PrenotazioneRequest prenotazioneRequest) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        BeanUtils.copyProperties(prenotazioneRequest, prenotazione);
        prenotazione.setDipendente(dipendenteRepository.findById(prenotazioneRequest.getDipendenteId()).get());
        prenotazione.setViaggio(viaggioRepository.findById(prenotazioneRequest.getViaggioId()).get());
        prenotazioneRepository.save(prenotazione);
        return prenotazioneDetailResponseFromPrenotazione(prenotazione);
    }

    public void delete(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
        prenotazioneRepository.deleteById(id);
    }
}
