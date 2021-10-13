package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Integer> {

    List<Demande> findAllByFaitParOrderByIdDesc(Personne personne);
    List<Demande> findAllByBienDemandee_AppartientOrderByEtatDemandeId(Personne personne);
}
