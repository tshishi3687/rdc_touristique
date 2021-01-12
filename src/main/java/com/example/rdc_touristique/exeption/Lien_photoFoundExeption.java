package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.Lien_photoDTO;

public class Lien_photoFoundExeption extends ElementFoundException{
    public Lien_photoFoundExeption(int id) {
        super(  Lien_photoDTO.class,id);
    }
}
