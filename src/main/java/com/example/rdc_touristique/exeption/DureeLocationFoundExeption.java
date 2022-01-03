package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.DureeOnLineDTO;

public class DureeLocationFoundExeption extends ElementFoundException{
    public DureeLocationFoundExeption(int id){super(DureeOnLineDTO.class,id);}
}
