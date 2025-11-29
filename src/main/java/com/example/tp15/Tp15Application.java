package com.example.tp15;

import com.example.tp15.entities.Compte;
import com.example.tp15.entities.TypeCompte;
import com.example.tp15.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp15Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp15Application.class, args);
	}

	// 4 comptes de démo créés automatiquement au démarrage
	@Bean
	CommandLineRunner initData(CompteRepository compteRepository) {
		return args -> {
			if (compteRepository.count() == 0) {
				compteRepository.save(new Compte(4500.0, TypeCompte.COURANT));
				compteRepository.save(new Compte(12300.0, TypeCompte.EPARGNE));
				compteRepository.save(new Compte(89000.0, TypeCompte.EPARGNE));
				compteRepository.save(new Compte(800.0, TypeCompte.COURANT));
				System.out.println("4 comptes de démo créés avec succès !");
			}
		};
	}

	// Message d'accueil au démarrage
	@Bean
	CommandLineRunner printUrls() {
		return args -> {
			System.out.println("\n" +
					"==================================================\n" +
					"  SERVICE GRAPHQL BANQUE PRÊT !\n" +
					"  GraphiQL (interface graphique) → http://localhost:8080/graphiql\n" +
					"  Endpoint GraphQL               → http://localhost:8080/graphql\n" +
					"  Console H2                     → http://localhost:8080/h2-console\n" +
					"==================================================\n");
		};
	}
}