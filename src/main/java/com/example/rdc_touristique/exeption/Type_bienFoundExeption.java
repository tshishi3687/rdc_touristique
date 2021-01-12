package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.Type_bienDTO;

public class Type_bienFoundExeption extends ElementFoundException{
    public Type_bienFoundExeption(int id) {
        super( Type_bienDTO.class, id);
    }
}
