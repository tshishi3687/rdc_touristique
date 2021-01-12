package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;

public class PersonneSimplifierExisteExeption extends ElementAlreadyExistsException{
    public PersonneSimplifierExisteExeption(int id) {
        super( id, PersonneSimplifierDTO.class);
    }
}
