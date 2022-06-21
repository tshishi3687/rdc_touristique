package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Service;
import com.example.rdc_touristique.data_access.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findAllByCoordonnee_Ville(Ville ville);
    List<Service> findByOrderByIdDesc();
}
