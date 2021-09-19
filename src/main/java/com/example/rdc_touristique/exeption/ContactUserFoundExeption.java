package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ContactUserDTO;

public class ContactUserFoundExeption extends ElementFoundException{
    public ContactUserFoundExeption(int id){super(ContactUserDTO.class,id);}
}
