package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.Type_bienDTO;
import com.example.rdc_touristique.data_access.entity.Type_bien;
import org.springframework.stereotype.Component;


@Component
public class Type_bienMapper implements Mapper<Type_bienDTO, Type_bien>{
    @Override
    public Type_bienDTO toDTO(Type_bien type_bien) {
        if(type_bien==null)
            return null;

        return new Type_bienDTO(
                type_bien.getId(),
                type_bien.getNom()
        );
    }

    @Override
    public Type_bien toEntity(Type_bienDTO type_bienDTO) {
        if(type_bienDTO==null)
            return null;

        Type_bien type_bien = new Type_bien();
        type_bien.setId(type_bienDTO.getId());
        type_bien.setNom(type_bienDTO.getNom());

        return type_bien;
    }
}
