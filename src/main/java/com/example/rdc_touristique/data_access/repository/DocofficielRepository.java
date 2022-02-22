package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.DocOfficielPersonne;

import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocofficielRepository extends JpaRepository<DocOfficielPersonne, Long> {
    Optional<DocOfficielPersonne> findByName(String name);
    List<DocOfficielPersonne> findAllByPersonneId(Personne personne);
}
