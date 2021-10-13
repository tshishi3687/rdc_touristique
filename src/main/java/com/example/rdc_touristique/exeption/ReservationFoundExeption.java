package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.DemandeDTO;

public class ReservationFoundExeption extends ElementFoundException{
    public ReservationFoundExeption(int id) {
        super( DemandeDTO.class, id);
    }
}
