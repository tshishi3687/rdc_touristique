package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;

public class CoordonneeFoundExeption extends ElementFoundException{
    public CoordonneeFoundExeption(int id) {
        super( CoordonneeDTO.class, id);
    }
}
