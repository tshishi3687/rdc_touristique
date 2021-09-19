package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.InfoBancaireDTO;

public class InfoBancaireFoundExeption extends ElementFoundException{
    public InfoBancaireFoundExeption(int id){super(InfoBancaireDTO.class, id);}
}
