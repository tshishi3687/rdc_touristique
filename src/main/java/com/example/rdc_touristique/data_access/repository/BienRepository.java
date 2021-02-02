package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BienRepository extends JpaRepository<Bien, Integer> {

    List<Bien> findAllByAppartient(Personne personne);
}
