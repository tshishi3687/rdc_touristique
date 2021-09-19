package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.InfoBancaire;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoBancaireRepository extends JpaRepository<InfoBancaire, Integer> {

    List<InfoBancaire> findAllByAppartienA(Personne personne);
}
