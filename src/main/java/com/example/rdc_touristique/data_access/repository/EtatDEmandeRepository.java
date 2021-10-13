package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.EtatDemande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatDEmandeRepository extends JpaRepository<EtatDemande, Integer> {
}
