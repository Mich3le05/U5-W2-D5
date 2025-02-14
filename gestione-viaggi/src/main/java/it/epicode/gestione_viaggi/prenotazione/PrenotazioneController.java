package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.response.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse createPrenotazione(@RequestBody PrenotazioneRequest request) {
        return prenotazioneService.createPrenotazione(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PrenotazioneResponse> findAll() {
        return prenotazioneService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneResponse findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneResponse modify(@PathVariable Long id, @RequestBody PrenotazioneRequest request) {
        return prenotazioneService.modify(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prenotazioneService.delete(id);
    }
}
