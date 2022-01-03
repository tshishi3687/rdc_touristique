package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.DureeOnLineDTO;

public class DureeLocationExisteExeption extends ElementAlreadyExistsException{
    public DureeLocationExisteExeption(int id){super(id, DureeOnLineDTO.class);}
}
