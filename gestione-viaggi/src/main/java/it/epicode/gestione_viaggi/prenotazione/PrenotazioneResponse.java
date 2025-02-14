package it.epicode.gestione_viaggi.prenotazione;

import java.time.LocalDate;

public class PrenotazioneResponse {

    private Long id;
    private LocalDate dataRichiesta;
    private String note;
    private String preferenze;
    private Long dipendenteId;
    private Long viaggioId;

}
