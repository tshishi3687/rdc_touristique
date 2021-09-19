package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.RollDTO;

public class RollExisteExeption extends ElementAlreadyExistsException{
    public RollExisteExeption(int id){super(id, RollDTO.class);}
}
