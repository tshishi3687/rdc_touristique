package com.example.rdc_touristique.data_access.repository;

import java.util.Optional;

import com.example.rdc_touristique.data_access.entity.ImageBien;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ImageRepository extends JpaRepository<ImageBien, Long> {
    Optional<ImageBien> findByName(String name);
//    List<ImageModel> findBySuperid(int id);
}
