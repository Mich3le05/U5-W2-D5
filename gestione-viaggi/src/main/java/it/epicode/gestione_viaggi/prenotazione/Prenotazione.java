package it.epicode.gestione_viaggi.prenotazione;

import it.epicode.gestione_viaggi.dipendente.Dipendente;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate dataRichiesta; // Data di richiesta prenotazione
    private String note; // Eventuali note del dipendente
    private String preferenze; // Preferenze per volo, alloggio, ecc.

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente; // Riferimento al dipendente

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio; // Riferimento al viaggio
}
