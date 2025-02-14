package it.epicode.gestione_viaggi.viaggio;

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
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioResponse viaggioResponseFromEntity(Viaggio viaggio) {
        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;
    }

    public List<ViaggioResponse> viaggioResponseListFromEntityList(List<Viaggio> viaggi) {
        return viaggi.stream().map(this::viaggioResponseFromEntity).toList();
    }

    public List<ViaggioResponse> findAll() {
        return viaggioResponseListFromEntityList(viaggioRepository.findAll());
    }

    @Transactional
    public ViaggioDetailResponse findViaggioResponseFromId(Long id) {
        if (!viaggioRepository.existsById(id))
            throw new EntityNotFoundException("Viaggio non trovato");

        Viaggio viaggio = viaggioRepository.findById(id).get();
        ViaggioDetailResponse response = new ViaggioDetailResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;
    }

    public Viaggio findById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
    }

    public Viaggio viaggioFromRequest(ViaggioRequest request) {
        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(request, viaggio);
        return viaggio;
    }

    public CreateResponse save(@Valid ViaggioRequest request) {
        Viaggio viaggio = viaggioFromRequest(request);
        viaggioRepository.save(viaggio);

        CreateResponse response = new CreateResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;
    }

    public Viaggio modify(Long id, ViaggioRequest request) {
        Viaggio viaggio = findById(id);
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
        return viaggio;
    }

    public void delete(Long id) {
        if (!viaggioRepository.existsById(id))
            throw new EntityNotFoundException("Viaggio non trovato");
        viaggioRepository.deleteById(id);
    }
}
