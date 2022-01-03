package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BienMisEnLigneRepository extends JpaRepository<BienMisEnLigne, Integer> {
    List<BienMisEnLigne> findByContratBienMisEnLigne_Bailleur(Personne bailleur);
    List<BienMisEnLigne> findByContratBienMisEnLigne_Preneur(Personne preneur);
    Optional<BienMisEnLigne> findByBienLie(Bien bien);
}
