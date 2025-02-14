package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.response.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PrenotazioneDetailResponse> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneDetailResponse findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody PrenotazioneRequest prenotazioneRequest) {
        return prenotazioneService.save(prenotazioneRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrenotazioneDetailResponse modify(@PathVariable Long id, @RequestBody PrenotazioneRequest prenotazioneRequest) {
        return prenotazioneService.modify(id, prenotazioneRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prenotazioneService.delete(id);
    }
}
