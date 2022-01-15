package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ContratLocationDTO;

public class ContratLocationExisteExeption extends ElementAlreadyExistsException{
    public ContratLocationExisteExeption(int id){super(id, ContratLocationDTO.class);}
}
