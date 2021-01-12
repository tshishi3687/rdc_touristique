package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;

public class CoordonneeExisteExeption extends ElementAlreadyExistsException{
    public CoordonneeExisteExeption(int id) {
        super( id, CoordonneeDTO.class);
    }
}
