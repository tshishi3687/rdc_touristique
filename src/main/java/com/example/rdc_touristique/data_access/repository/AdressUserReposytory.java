package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.AdressUser;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdressUserReposytory extends JpaRepository<AdressUser, Integer> {

    List<AdressUser> findAllByAppartienA(Personne personne);
}
