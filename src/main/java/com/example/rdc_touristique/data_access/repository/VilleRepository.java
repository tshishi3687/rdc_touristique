package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
}
