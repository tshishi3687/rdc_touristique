package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.AdressUserDTO;

public class AdressUserFoundExeption extends ElementFoundException{
    public AdressUserFoundExeption(int id){super(AdressUserDTO.class, id);}
}
