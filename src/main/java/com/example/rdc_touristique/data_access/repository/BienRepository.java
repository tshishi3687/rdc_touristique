package com.example.rdc_touristique.data_access.repository;


import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BienRepository extends JpaRepository<Bien, Integer> {
    List<Bien> findAllByAppartientAndModeActiveFalse(Personne personne);
    List<Bien> findByOrderByIdDesc();
    List<Bien> findAllByModeActiveIs(boolean modeActive);
}
