package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Coordonnee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordonneeRepository extends JpaRepository<Coordonnee, Integer> {

}
