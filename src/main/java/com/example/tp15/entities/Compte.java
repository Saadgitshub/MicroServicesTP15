package com.example.tp15.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "comptes") // nom de table propre
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double solde;

    // Version moderne avec LocalDate (plus propre que java.util.Date)
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeCompte type;

    // Constructeur pratique pour les tests et les mutations GraphQL
    public Compte(double solde, TypeCompte type) {
        this.solde = solde;
        this.type = type;
    }
}