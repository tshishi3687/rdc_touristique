package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;

public class PersonneSimpleExisteExeption extends ElementAlreadyExistsException{
    public PersonneSimpleExisteExeption(int id) {
        super( id, PersonneSimpleDTO.class);
    }
}
