package it.epicode.gestione_viaggi.viaggio;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequest {

    @NotBlank(message = "Il campo destinazione non può essere vuoto")
    private String destinazione;

    private LocalDate data;

    private StatoViaggio stato;
}
