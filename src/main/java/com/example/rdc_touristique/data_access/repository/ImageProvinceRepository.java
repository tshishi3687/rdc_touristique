package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.ImageProvince;
import com.example.rdc_touristique.data_access.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageProvinceRepository extends JpaRepository<ImageProvince, Long> {
    List<ImageProvince> findAllByProvinceID(Province provinceId);
}
