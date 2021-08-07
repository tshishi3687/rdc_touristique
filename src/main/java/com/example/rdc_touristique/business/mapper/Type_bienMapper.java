package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.Type_bienDTO;
import com.example.rdc_touristique.data_access.entity.Type_bien;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Type_bienMapper implements Mapper<Type_bienDTO, Type_bien>{
    @Override
    public Type_bienDTO toDTO(Type_bien type_bien) {
        if(type_bien==null)
            return null;

        return new Type_bienDTO(
                type_bien.getId(),
                type_bien.getNom()
//                type_bien.getDateCreation(),
//                type_bien.getSuperid()
        );
    }

    @Override
    public Type_bien toEntity(Type_bienDTO type_bienDTO) {
        if(type_bienDTO==null)
            return null;

        Type_bien type_bien = new Type_bien();
        type_bien.setId(type_bienDTO.getId());
        type_bien.setNom(type_bienDTO.getNom());
//        type_bien.setDateCreation(LocalDateTime.now());
//        type_bien.setSuperid(0);

        return type_bien;
    }
}
