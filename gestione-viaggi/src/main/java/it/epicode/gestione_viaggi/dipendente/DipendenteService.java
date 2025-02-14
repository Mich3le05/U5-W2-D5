package it.epicode.gestione_viaggi.dipendente;

import it.epicode.gestione_viaggi.response.CreateResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public DipendenteResponse dipendenteResponseFromEntity(Dipendente dipendente) {
        DipendenteResponse response = new DipendenteResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }

    public List<DipendenteResponse> dipendenteResponseListFromEntityList(List<Dipendente> dipendenti) {
        return dipendenti.stream().map(this::dipendenteResponseFromEntity).toList();
    }

    public List<DipendenteResponse> findAll() {
        return dipendenteResponseListFromEntityList(dipendenteRepository.findAll());
    }

    @Transactional
    public DipendenteDetailResponse findDipendenteResponseFromId(Long id) {
        if (!dipendenteRepository.existsById(id))
            throw new EntityNotFoundException("Dipendente non trovato");

        Dipendente dipendente = dipendenteRepository.findById(id).get();
        DipendenteDetailResponse response = new DipendenteDetailResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    public Dipendente dipendenteFromRequest(DipendenteRequest request) {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(request, dipendente);
        return dipendente;
    }

    public CreateResponse save(@Valid DipendenteRequest request) {
        Dipendente dipendente = dipendenteFromRequest(request);
        dipendenteRepository.save(dipendente);

        CreateResponse response = new CreateResponse();
        BeanUtils.copyProperties(dipendente, response);
        return response;
    }

    public Dipendente modify(Long id, DipendenteRequest request) {
        Dipendente dipendente = findById(id);
        BeanUtils.copyProperties(request, dipendente, "id");
        dipendenteRepository.save(dipendente);
        return dipendente;
    }

    public void delete(Long id) {
        if (!dipendenteRepository.existsById(id))
            throw new EntityNotFoundException("Dipendente non trovato");
        dipendenteRepository.deleteById(id);
    }
}
