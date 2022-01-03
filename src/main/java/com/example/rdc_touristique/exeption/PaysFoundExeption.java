package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PaysDTO;

public class PaysFoundExeption extends ElementFoundException{
    public PaysFoundExeption(int id){super(PaysDTO.class, id);}
}
