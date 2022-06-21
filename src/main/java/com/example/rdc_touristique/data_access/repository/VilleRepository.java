package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
    List<Ville> findByOrderByNomVilleAsc();
    List<Ville> findAllByProvinceOrderByNomVilleAsc(Province province);
}
