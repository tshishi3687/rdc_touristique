package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.TypeDTO;

public class TypeExisteExeption extends ElementAlreadyExistsException{
    public TypeExisteExeption(int id) {
        super( id, TypeDTO.class);
    }
}
