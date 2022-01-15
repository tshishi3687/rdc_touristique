package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ContratMisEnLigneDTO;

public class ContratMisEnLigneFoundExeption extends ElementFoundException{
    public ContratMisEnLigneFoundExeption(int id){super(ContratMisEnLigneDTO.class, id);}
}
