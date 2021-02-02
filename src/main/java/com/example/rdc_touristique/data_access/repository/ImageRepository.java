package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {
    Optional<ImageModel> findByName(String name);
}
