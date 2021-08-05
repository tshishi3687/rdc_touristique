package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.VilleDTO;

public class VilleExisteExeption extends ElementAlreadyExistsException{
    public VilleExisteExeption(int id) {
        super( id, VilleDTO.class);
    }
}
