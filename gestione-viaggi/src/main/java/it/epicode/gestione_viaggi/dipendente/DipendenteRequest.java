package it.epicode.gestione_viaggi.dipendente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteRequest {

    @NotBlank(message = "Il campo username non può essere vuoto")
    private String username;

    @NotBlank(message = "Il campo nome non può essere vuoto")
    private String name;

    @NotBlank(message = "Il campo cognome non può essere vuoto")
    private String cognome;

    @NotBlank(message = "Il campo email non può essere vuoto")
    @Email(message = "Email non valida")
    private String email;
}
