package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Integer> {
}
