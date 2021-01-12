package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.BienDTO;

public class BienExisteExeption extends ElementAlreadyExistsException{
    public BienExisteExeption(int id) {
        super( id, BienDTO.class);
    }
}
