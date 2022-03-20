package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.ImageVille;
import com.example.rdc_touristique.data_access.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageVilleRepository extends JpaRepository<ImageVille, Long> {
    List<ImageVille> findAllByVille(Ville ville);
}
