package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
