package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {

    @Modifying
    @Query(value = "insert into image_table (image) values (:image)", nativeQuery = true)
    void insertImage(@Param("image") byte[] image);
}
