package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PersonneInfoDTO;

public class PersonneInfoExisteExeption extends ElementAlreadyExistsException{
    public PersonneInfoExisteExeption(int id) {
        super( id, PersonneInfoDTO.class);
    }
}
