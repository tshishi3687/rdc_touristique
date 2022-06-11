package com.example.rdc_touristique.data_access.repository;


import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BienRepository extends JpaRepository<Bien, Integer> {
    List<Bien> findAllByAppartientAndModeActiveFalse(Personne personne);

    List<Bien> findAllByModeActiveTrueOrderByIdDesc(Pageable pageable);

    // si tout est vide
    List<Bien> findAllByModeActiveTrueOrderByIdDesc();

    // si type et province
    List<Bien> findByType_IdAndCoordonnee_Ville_Province_IdAndModeActiveTrueOrderByIdDesc(int type_id, int coordonnee_ville_province_id, Pageable pageable);

    // par type
    List<Bien> findByType_IdAndModeActiveTrueOrderByIdDesc(int type, Pageable pageable);
    // par province
    List<Bien> findByCoordonnee_Ville_Province_IdAndModeActiveTrueOrderByIdDesc(int province, Pageable pageable);
    // par type ville et province
    List<Bien> findByType_IdAndCoordonnee_Ville_IdAndCoordonnee_Ville_Province_IdAndModeActiveTrueOrderByIdDesc(Integer type_Id, Integer ville_Id, Integer province_ID, Pageable pageable);
    // par ville et province
    List<Bien> findByCoordonnee_Ville_IdAndCoordonnee_Ville_Province_IdAndModeActiveTrueOrderByIdDesc(int ville_ID, int province_Id, Pageable pageable);



}
