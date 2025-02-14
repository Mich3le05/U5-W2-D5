package it.epicode.gestione_viaggi.dipendente;

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
    public List<Dipendente> findAll() {
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente findById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody Dipendente request) {
        return dipendenteService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente modify(@PathVariable Long id, @RequestBody DipendenteRequest request) {
        return dipendenteService.modify(id, reques);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dipendenteService.delete(id);
    }
}
