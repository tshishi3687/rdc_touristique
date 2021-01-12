package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
