package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.Type_serviceDTO;

public class TypeExisteExeption extends ElementAlreadyExistsException{
    public TypeExisteExeption(int id) {
        super( id, Type_serviceDTO.class);
    }
}
