package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ProvinceDTO;

public class ProvinceFoundExeption extends ElementFoundException{
    public ProvinceFoundExeption(int id) {
        super( ProvinceDTO.class, id);
    }
}
