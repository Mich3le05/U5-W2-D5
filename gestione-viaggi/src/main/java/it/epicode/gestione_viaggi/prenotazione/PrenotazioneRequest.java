package it.epicode.gestione_viaggi.prenotazione;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequest {

    private String note;
    private String preferenze;

    @NotNull(message = "Il dipendente non può essere nullo")
    private Long dipendenteId;

    @NotNull(message = "Il viaggio non può essere nullo")
    private Long viaggioId;

    @NotNull(message = "La data di richiesta non può essere nulla")
    private LocalDate dataRichiesta;
}
