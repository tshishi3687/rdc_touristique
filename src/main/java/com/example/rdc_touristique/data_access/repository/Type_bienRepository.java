package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Type_bien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Type_bienRepository extends JpaRepository<Type_bien, Integer> {
}
