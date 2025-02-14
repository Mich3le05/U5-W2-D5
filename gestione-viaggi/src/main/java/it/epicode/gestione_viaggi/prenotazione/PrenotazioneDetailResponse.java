package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.dipendente.Dipendente;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDetailResponse {

    private Long id;
    private LocalDate dataRichiesta;
    private String note;
    private String preferenze;
    private Dipendente dipendente;
    private Viaggio viaggio;
}
