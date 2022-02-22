package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneReposytory extends JpaRepository<Personne, Integer> {
    Optional<Personne> findByCodeActivation(String codeActivation);
    Personne findByRoleId_NomRole(String nomRoll);
}

