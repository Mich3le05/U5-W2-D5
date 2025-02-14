package it.epicode.gestione_viaggi.viaggio;

import it.epicode.gestione_viaggi.response.CreateResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public ViaggioResponse viaggioResponseFromEntity(Viaggio viaggio) {
        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;
    }

    public List<ViaggioResponse> findAll() {
        return viaggioRepository.findAll().stream()
                .map(this::viaggioResponseFromEntity)
                .toList();
    }

    @Transactional
    public ViaggioDetailResponse findViaggioResponseFromId(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        ViaggioDetailResponse response = new ViaggioDetailResponse();
        BeanUtils.copyProperties(viaggio, response);
        return response;
    }

    public CreateResponse save(@Valid ViaggioRequest request) {
        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
        return new CreateResponse(viaggio.getId());
    }

    public ViaggioDetailResponse modify(Long id, ViaggioRequest request) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);
        return new ViaggioDetailResponse(viaggio.getId(), viaggio.getDestinazione(), viaggio.getData(), viaggio.getStato(), null);
    }

    public void delete(Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio non trovato");
        }
        viaggioRepository.deleteById(id);
    }
}
