package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ContratMisEnLigneDTO;

public class ContratMisEnLigneExisteExeption extends ElementAlreadyExistsException{
    public ContratMisEnLigneExisteExeption(int id){super(id, ContratMisEnLigneDTO.class);}
}
