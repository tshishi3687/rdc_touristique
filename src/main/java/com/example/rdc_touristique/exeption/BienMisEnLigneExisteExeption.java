package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.BienMisEnLigneDTO;

public class BienMisEnLigneExisteExeption extends ElementAlreadyExistsException{
    public BienMisEnLigneExisteExeption(int id){super(id, BienMisEnLigneDTO.class);}
}
