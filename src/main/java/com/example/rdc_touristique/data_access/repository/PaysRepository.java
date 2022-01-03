package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaysRepository extends JpaRepository<Pays, Integer> {
    List<Pays> findByOrderByNomFrFrAsc();
}
