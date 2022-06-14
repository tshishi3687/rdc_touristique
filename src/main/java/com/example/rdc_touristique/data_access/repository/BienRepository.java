package com.example.rdc_touristique.data_access.repository;


import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BienRepository extends JpaRepository<Bien, Integer> {
    List<Bien> findAllByAppartientAndModeActiveFalse(Personne personne);

    List<Bien> findByType_NomContainingAndCoordonnee_Ville_NomVilleContainingAndCoordonnee_Ville_Province_NomprovinceContainingAndModeActiveTrueOrderByIdDesc(String type_nom, String coordonnee_ville_nomVille, String coordonnee_ville_province_nomprovince, Pageable pageable);
    int countByType_NomContainingAndCoordonnee_Ville_NomVilleContainingAndCoordonnee_Ville_Province_NomprovinceContainingAndModeActiveTrue(String type_nom, String coordonnee_ville_nomVille, String coordonnee_ville_province_nomprovince);
}
