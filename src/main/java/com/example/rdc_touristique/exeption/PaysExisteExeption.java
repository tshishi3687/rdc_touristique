package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PaysDTO;

public class PaysExisteExeption extends ElementAlreadyExistsException{
    public PaysExisteExeption(int id){super(id, PaysDTO.class);}
}
