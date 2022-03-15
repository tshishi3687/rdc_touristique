package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findByOrderByNomtype();
}
