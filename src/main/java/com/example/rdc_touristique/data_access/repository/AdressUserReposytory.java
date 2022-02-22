package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.AdressePersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressUserReposytory extends JpaRepository<AdressePersonne, Integer> {

    AdressePersonne findOneByAppartienA(Personne personne);
    boolean existsByAppartienA(Personne personne);
}
