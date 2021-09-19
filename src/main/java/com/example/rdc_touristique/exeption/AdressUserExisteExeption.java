package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.AdressUserDTO;

public class AdressUserExisteExeption extends  ElementAlreadyExistsException{
    public AdressUserExisteExeption(int id){super(id,AdressUserDTO.class);}
}
