package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.BienDTO;

public class ActionFoundExeption extends ElementFoundException{
    public ActionFoundExeption(int id) {
        super(BienDTO.class, id);
    }
}
