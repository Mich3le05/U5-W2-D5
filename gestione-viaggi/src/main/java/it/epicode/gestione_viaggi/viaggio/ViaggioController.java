package it.epicode.gestione_viaggi.viaggio;

import it.epicode.gestione_viaggi.response.CreateResponse;
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
    public List<ViaggioResponse> findAll() {
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio findById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody ViaggioRequest request) {
        return viaggioService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio modify(@PathVariable Long id, @RequestBody ViaggioRequest request) {
        return viaggioService.modify(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }
}
