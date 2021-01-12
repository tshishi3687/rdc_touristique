package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;

public class PersonneSimplifierFoundExeption extends ElementFoundException{
    public PersonneSimplifierFoundExeption(int id) {
        super( PersonneSimplifierDTO.class, id);
    }
}
