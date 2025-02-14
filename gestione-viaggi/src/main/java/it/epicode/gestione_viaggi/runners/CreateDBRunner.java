package it.epicode.gestione_viaggi.runners;

import it.epicode.gestione_viaggi.dipendente.Dipendente;
import it.epicode.gestione_viaggi.dipendente.DipendenteRepository;
import it.epicode.gestione_viaggi.viaggio.StatoViaggio;
import it.epicode.gestione_viaggi.viaggio.Viaggio;
import it.epicode.gestione_viaggi.viaggio.ViaggioRepository;
import it.epicode.gestione_viaggi.prenotazione.Prenotazione;
import it.epicode.gestione_viaggi.prenotazione.PrenotazioneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateDBRunner implements CommandLineRunner {

    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    @Override
    public void run(String... args) throws Exception {
        // Creazione dipendente e viaggio (già fatto)
        Dipendente dipendente = new Dipendente("mario.rossi", "Mario", "Rossi", "mario.rossi@example.com");
        dipendenteRepository.save(dipendente);

        Viaggio viaggio = new Viaggio("Milano", LocalDate.now().plusDays(10), StatoViaggio.IN_PROGRAMMA);
        viaggioRepository.save(viaggio);

        // Creazione prenotazione
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(LocalDate.now().plusDays(1));
        prenotazione.setNote("Preferenza per volo diretto");
        prenotazione.setPreferenze("Alloggio in hotel 4 stelle");

        prenotazioneRepository.save(prenotazione);
    }
}
