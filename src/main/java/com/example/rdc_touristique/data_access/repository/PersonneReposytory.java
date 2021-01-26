package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.data_access.entity.Personne;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonneReposytory extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByEmail(String email);

}

