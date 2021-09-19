package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.RollDTO;

public class RollFoundExeption extends ElementFoundException{
    public RollFoundExeption(int id){super(RollDTO.class, id);}
}
