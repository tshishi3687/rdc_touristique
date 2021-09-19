package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ContactUserDTO;

public class ContactUserExisteExeption extends ElementAlreadyExistsException{
    public ContactUserExisteExeption(int id){super(id, ContactUserDTO.class);}
}
