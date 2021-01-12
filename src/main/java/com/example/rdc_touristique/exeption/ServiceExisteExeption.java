package com.example.rdc_touristique.exeption;

import com.example.rdc_touristique.business.dto.ServiceDTO;

public class ServiceExisteExeption extends ElementAlreadyExistsException{
    public ServiceExisteExeption(int id) {
        super( id, ServiceDTO.class);
    }
}
