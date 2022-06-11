package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.DocOfficielPersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocofficielRepository extends JpaRepository<DocOfficielPersonne, Long> {
    List<DocOfficielPersonne> findAllByPersonneId(Personne personne);
}
