package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.Type_serviceDTO;

public class TypeFoundExeption extends ElementFoundException{
    public TypeFoundExeption(int id) {
        super( Type_serviceDTO.class, id);
    }
}
