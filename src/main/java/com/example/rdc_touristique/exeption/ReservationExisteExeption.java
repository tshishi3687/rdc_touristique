package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.DemandeDTO;

public class ReservationExisteExeption extends ElementAlreadyExistsException{
    public ReservationExisteExeption(int id) {
        super( id, DemandeDTO.class);
    }
}
