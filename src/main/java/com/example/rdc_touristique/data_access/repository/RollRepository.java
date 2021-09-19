package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollRepository extends JpaRepository<Roll,Integer> {
    Roll findByNomRoll(String nomRoll);
}
