package it.epicode.gestione_viaggi.dipendente;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DipendenteConfig {

    private final Faker faker = new Faker();

    @Bean
    @Scope("prototype")
    public Dipendente dipendente() {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(faker.name().username());
        dipendente.setName(faker.name().firstName());
        dipendente.setCognome(faker.name().lastName());
        dipendente.setEmail(faker.internet().emailAddress());
        return dipendente;
    }
}
