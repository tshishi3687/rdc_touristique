package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;

public class PersonneSimpleFoundExeption extends ElementFoundException{
    public PersonneSimpleFoundExeption(int id) {
        super( PersonneSimpleDTO.class, id);
    }
}
