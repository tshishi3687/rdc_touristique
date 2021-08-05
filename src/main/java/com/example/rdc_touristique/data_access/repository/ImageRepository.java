package com.example.rdc_touristique.data_access.repository;

import java.util.List;
import java.util.Optional;

import com.example.rdc_touristique.data_access.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
    List<ImageModel> findBySuperid(int id);
}
