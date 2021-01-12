package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.TypeDTO;

public class TypeFoundExeption extends ElementFoundException{
    public TypeFoundExeption(int id) {
        super( TypeDTO.class, id);
    }
}
