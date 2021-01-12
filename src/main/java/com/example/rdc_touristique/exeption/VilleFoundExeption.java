package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.VilleDTO;

public class VilleFoundExeption extends ElementFoundException{
    public VilleFoundExeption(int id) {
        super(  VilleDTO.class, id);
    }
}
