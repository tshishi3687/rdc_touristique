package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.BienDTO;

public class BienFoundExeption extends ElementFoundException{
    public BienFoundExeption(int id) {
        super(BienDTO.class, id);
    }
}
