package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.PassWordDTO;

public class PassWordFoundExeption extends ElementFoundException{
    public PassWordFoundExeption(int id){super(PassWordDTO.class, id);}
}
