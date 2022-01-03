package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ReservationDTO;

public class ReservationFoundExeption extends ElementFoundException{
    public ReservationFoundExeption(int id) {
        super( ReservationDTO.class, id);
    }
}
