package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
