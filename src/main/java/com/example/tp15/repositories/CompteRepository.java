package com.example.tp15.repositories;

import com.example.tp15.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    // Méthode personnalisée pour sommer les soldes (optionnelle mais utile)
    @Query("SELECT COALESCE(SUM(c.solde), 0.0) FROM Compte c")
    double sumAllSoldes();
}