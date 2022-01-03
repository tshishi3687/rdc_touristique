package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.BienMisEnLigneDTO;

public class BienMisEnLigneFoundExeption extends ElementFoundException{
    public BienMisEnLigneFoundExeption(int id){super(BienMisEnLigneDTO.class, id);}
}
