package it.epicode.gestione_viaggi.viaggio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.gestione_viaggi.dipendente.Dipendente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioDetailResponse {
    private Long id;
    private String destinazione;
    private LocalDate data;
    private StatoViaggio stato;

    @ToString.Exclude
    @JsonIgnoreProperties("viaggi")
    private List<Dipendente> dipendenti;
}
