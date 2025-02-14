package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.response.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse createPrenotazione(@RequestParam Long dipendenteId,
                                             @RequestParam Long viaggioId,
                                             @RequestParam LocalDate dataRichiesta,
                                             @RequestParam(required = false) String note,
                                             @RequestParam(required = false) String preferenze) {
        Prenotazione prenotazione = prenotazioneService.createPrenotazione(dipendenteId, viaggioId, dataRichiesta, note, preferenze);
        return new CreateResponse(prenotazione.getId());
    }
}
