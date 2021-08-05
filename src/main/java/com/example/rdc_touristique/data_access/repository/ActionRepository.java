package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Action;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ActionRepository extends JpaRepository<Action, Integer> {

    List<Action> findAllByDate(LocalDate date);


}
