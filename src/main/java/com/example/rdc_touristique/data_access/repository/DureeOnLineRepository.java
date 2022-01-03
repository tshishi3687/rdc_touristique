package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DureeOnLineRepository extends JpaRepository<BienMisEnLigne, Integer> {
}
