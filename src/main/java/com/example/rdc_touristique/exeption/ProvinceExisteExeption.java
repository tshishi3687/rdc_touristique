package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ProvinceDTO;

public class ProvinceExisteExeption extends ElementAlreadyExistsException{
    public ProvinceExisteExeption(int id) {
        super( id, ProvinceDTO.class);
    }
}
