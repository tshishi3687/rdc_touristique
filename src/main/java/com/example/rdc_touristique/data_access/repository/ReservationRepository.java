package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByContratLieeReservation_PreneurOrderByIdDesc(Personne personne);
//    List<Reservation> findAllByContratLieeReservation(Personne personne);
//    void deleteAllByBienReservation(Bien bien);
}
