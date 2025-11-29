package com.example.tp15.controllers;

import com.example.tp15.entities.Compte;
import com.example.tp15.repositories.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {

    private final CompteRepository compteRepository;

    // Récupérer tous les comptes
    @QueryMapping(name = "allComptes")
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // Récupérer un compte par ID avec message d'erreur clair
    @QueryMapping(name = "compteById")
    public Compte getCompteById(@Argument Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec l'id " + id + " non trouvé"));
    }

    // Créer ou mettre à jour un compte
    @MutationMapping(name = "saveCompte")
    public Compte saveCompte(@Argument Compte compte) {
        return compteRepository.save(compte);
    }

    // Supprimer un compte
    @MutationMapping(name = "deleteCompte")
    public Boolean deleteCompte(@Argument Long id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Statistiques globales (super pour le TP !)
    @QueryMapping
    public Map<String, Object> statsComptes() {
        long count = compteRepository.count();
        double sum = compteRepository.findAll().stream()
                .mapToDouble(Compte::getSolde)
                .sum();
        double average = count > 0 ? sum / count : 0.0;

        return Map.of(
                "nombreDeComptes", count,
                "soldeTotal", sum,
                "soldeMoyen", Math.round(average * 100.0) / 100.0);
    }
}