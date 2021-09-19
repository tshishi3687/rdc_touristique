package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PassWordDTO;

public class PassWordExisteExeption extends ElementAlreadyExistsException{
    public PassWordExisteExeption(int id){super(id, PassWordDTO.class);}
}
