package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.ContratLocation;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratLocationRepository extends JpaRepository<ContratLocation, Integer> {
    List<ContratLocation> findAllByPreneur(Personne personne);
}
