package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneReposytory extends JpaRepository<Personne, Integer> {
}
