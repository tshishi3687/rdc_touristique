package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findByOrderByNomprovinceAsc();
}
