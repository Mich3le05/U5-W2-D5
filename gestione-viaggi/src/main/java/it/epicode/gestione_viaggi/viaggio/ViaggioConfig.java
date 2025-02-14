package it.epicode.gestione_viaggi.viaggio;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Configuration
public class ViaggioConfig {
    private final Faker faker = new Faker();

    @Bean
    @Scope("prototype")
    public Viaggio viaggio() {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(faker.address().cityName());
        viaggio.setData(LocalDate.now().plusDays(faker.number().numberBetween(-90, 90)));
        if (viaggio.getData().isBefore(LocalDate.now()) || viaggio.getData().isEqual(LocalDate.now())) {
            viaggio.setStato(StatoViaggio.COMPLETATO);
        } else {
            viaggio.setStato(StatoViaggio.IN_PROGRAMMA);
        }
        return viaggio;
    }
}
