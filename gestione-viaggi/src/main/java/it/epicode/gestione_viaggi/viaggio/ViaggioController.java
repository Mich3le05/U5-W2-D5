package it.epicode.gestione_viaggi.viaggio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    private final ViaggioService viaggioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Viaggio> findAll() {
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio findById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody Viaggio request) {
        return viaggioService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio modify(@PathVariable Long id, @RequestBody Viaggio request) {
        return viaggioService.modify(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }
}

