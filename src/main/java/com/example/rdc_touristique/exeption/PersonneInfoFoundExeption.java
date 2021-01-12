package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PersonneInfoDTO;

public class PersonneInfoFoundExeption extends ElementFoundException{
    public PersonneInfoFoundExeption(int id) {
        super( PersonneInfoDTO.class, id);
    }
}
