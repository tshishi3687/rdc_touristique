package com.example.rdc_touristique.data_access.repository;


import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactUserRepository extends JpaRepository<ContactUser, Integer> {
    List<ContactUser> findAllByAppartienA(Personne personne);
    Optional<ContactUser> findByEmail(String mdp);
}
