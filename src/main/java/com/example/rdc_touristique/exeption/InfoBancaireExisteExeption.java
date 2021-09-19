package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.InfoBancaireDTO;

public class InfoBancaireExisteExeption extends ElementAlreadyExistsException{
    public InfoBancaireExisteExeption(int id){super(id, InfoBancaireDTO.class);}
}
