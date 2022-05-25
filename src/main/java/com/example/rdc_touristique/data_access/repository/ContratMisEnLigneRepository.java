package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.ContratMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratMisEnLigneRepository extends JpaRepository<ContratMisEnLigne, Integer> {
    List<ContratMisEnLigne> findAllByBailleurOrderByCreatedAtDesc(Personne personne);
    List<ContratMisEnLigne> findAllByPreneurOrderByCreatedAtDesc(Personne personne);
    void deleteByBailleur(Personne personne);

    List<ContratMisEnLigne> findAllByIdBien(Bien bien);
}
