package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.InfoBancairePersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoBancaireRepository extends JpaRepository<InfoBancairePersonne, Integer> {

    InfoBancairePersonne findOneByAppartienA(Personne personne);
    boolean existsByAppartienA(Personne personne);
}
