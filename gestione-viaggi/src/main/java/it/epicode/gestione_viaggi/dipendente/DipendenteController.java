package it.epicode.gestione_viaggi.dipendente;

import it.epicode.gestione_viaggi.response.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {

    private final DipendenteService dipendenteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DipendenteResponse> findAll() {
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DipendenteResponse findById(@PathVariable Long id) {
        return dipendenteService.dipendenteResponseFromEntity(dipendenteService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody DipendenteRequest request) {
        return dipendenteService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente modify(@PathVariable Long id, @RequestBody DipendenteRequest request) {
        return dipendenteService.modify(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dipendenteService.delete(id);
    }

    @GetMapping("/{id}/dettagli")
    @ResponseStatus(HttpStatus.OK)
    public DipendenteDetailResponse getDipendenteDetails(@PathVariable Long id) {
        return dipendenteService.findDipendenteResponseFromId(id);
    }
}
