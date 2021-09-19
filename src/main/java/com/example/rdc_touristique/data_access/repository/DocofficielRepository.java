package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.DocOfficiel;

import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocofficielRepository extends JpaRepository<DocOfficiel, Long> {
    Optional<DocOfficiel> findByName(String name);
    List<DocOfficiel> findAllByPersonneId(Personne personne);
}
