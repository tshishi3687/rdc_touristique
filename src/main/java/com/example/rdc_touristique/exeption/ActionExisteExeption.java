package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.BienDTO;

public class ActionExisteExeption extends ElementAlreadyExistsException{
    public ActionExisteExeption(int id) {
        super( id, BienDTO.class);
    }
}
