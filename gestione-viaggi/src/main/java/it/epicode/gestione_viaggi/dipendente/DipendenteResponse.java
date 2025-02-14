package it.epicode.gestione_viaggi.dipendente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteResponse {

    private Long id;
    private String username;
    private String name;
    private String cognome;
    private String email;
}
