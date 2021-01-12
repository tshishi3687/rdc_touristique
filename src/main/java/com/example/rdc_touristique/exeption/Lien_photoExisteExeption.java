package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.Lien_photoDTO;

public class Lien_photoExisteExeption extends ElementAlreadyExistsException{
    public Lien_photoExisteExeption(int id) {
        super( id, Lien_photoDTO.class);
    }
}
