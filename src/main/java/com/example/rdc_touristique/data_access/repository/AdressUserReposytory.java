package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Adresse;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressUserReposytory extends JpaRepository<Adresse, Integer> {

    Adresse findOneByAppartienA(Personne personne);
    boolean existsByAppartienA(Personne personne);
}
