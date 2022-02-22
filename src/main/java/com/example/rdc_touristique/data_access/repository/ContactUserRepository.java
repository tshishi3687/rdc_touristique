package com.example.rdc_touristique.data_access.repository;


import com.example.rdc_touristique.data_access.entity.ContactPersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactUserRepository extends JpaRepository<ContactPersonne, Integer> {
    ContactPersonne findAllByAppartienA(Personne personne);
    Optional<ContactPersonne> findByEmail(String mdp);
}
