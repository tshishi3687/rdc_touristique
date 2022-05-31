package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.ContratLocation;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratLocationRepository extends JpaRepository<ContratLocation, Integer> {
    List<ContratLocation> findAllByPreneurOrderByIdDesc(Personne personne);
    List<ContratLocation> findAllByBailleurOrderByIdDesc(Personne personne);
    List<ContratLocation> findAllByIdBien(Bien bien);
}
