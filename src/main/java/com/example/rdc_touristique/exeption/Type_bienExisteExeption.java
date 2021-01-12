package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.Type_bienDTO;

public class Type_bienExisteExeption extends ElementAlreadyExistsException{
    public Type_bienExisteExeption(int id) {
        super( id, Type_bienDTO.class);
    }
}
