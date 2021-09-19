package com.example.rdc_touristique.data_access.repository;


import com.example.rdc_touristique.data_access.entity.PassWord;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
public interface PassWordRepository extends JpaRepository<PassWord, Integer> {
    List<PassWord> findAllByAppartienA(Personne toDTO);
}
