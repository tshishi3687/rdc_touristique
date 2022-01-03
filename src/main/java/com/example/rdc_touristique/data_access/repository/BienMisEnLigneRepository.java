package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BienMisEnLigneRepository extends JpaRepository<BienMisEnLigne, Integer> {
    List<BienMisEnLigne> findByContratBienMisEnLigne_Bailleur(Personne bailleur);
    List<BienMisEnLigne> findByContratBienMisEnLigne_Preneur(Personne preneur);
    List<BienMisEnLigne> findByBienLie_DateFinMisEnLigne(LocalDate date, Personne personne);
}
