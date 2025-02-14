package it.epicode.gestione_viaggi.dipendente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteDetailResponse {
    private Long id;
    private String username;
    private String name;
    private String cognome;
    private String email;

    @ToString.Exclude
    @JsonIgnoreProperties("dipendenti")
    private List<Viaggio> viaggi;
}
