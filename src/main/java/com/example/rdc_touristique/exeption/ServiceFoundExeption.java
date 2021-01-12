package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ServiceDTO;

public class ServiceFoundExeption extends ElementFoundException{
    public ServiceFoundExeption(int id) {
        super( ServiceDTO.class, id);
    }
}
