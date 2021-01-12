package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Bien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BienRepository extends JpaRepository<Bien, Integer> {
}
