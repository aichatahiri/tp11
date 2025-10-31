package com.example.demo;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.TypeCompte;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository,
                            ClientRepository clientRepository,
                            RepositoryRestConfiguration restConfiguration) {
        return args -> {
            // Exposer les IDs dans les réponses JSON
            restConfiguration.exposeIdsFor(Compte.class, Client.class);

            // Création de clients
            Client c1 = clientRepository.save(new Client(null, "Amal", "amal@mail.com", null));
            Client c2 = clientRepository.save(new Client(null, "Ali", "ali@mail.com", null));

            // Création de comptes associés aux clients
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c1));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT, c1));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c2));

            // Affichage des comptes et clients pour vérification
            compteRepository.findAll().forEach(System.out::println);
            clientRepository.findAll().forEach(System.out::println);
        };
    }
}
