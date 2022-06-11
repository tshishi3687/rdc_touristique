package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.RolePersonne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollRepository extends JpaRepository<RolePersonne,Integer> { }
