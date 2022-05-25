package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details, String> {
}
