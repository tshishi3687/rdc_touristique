package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ContratLocationDTO;

public class ContratLocationFoundExeption extends ElementFoundException{
    public ContratLocationFoundExeption(int id){super(ContratLocationDTO.class,id);}
}
