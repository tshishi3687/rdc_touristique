package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.DureeLocationDTO;

public class DureeLocationFoundExeption extends ElementFoundException{
    public DureeLocationFoundExeption(int id){super(DureeLocationDTO.class,id);}
}
