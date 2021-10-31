package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.Type_serviceDTO;
import com.example.rdc_touristique.data_access.entity.Type;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Type_serviceMapper implements Mapper<Type_serviceDTO, Type>{
    @Override
    public Type_serviceDTO toDTO(Type type) {
        if(type==null)
            return null;

        return new Type_serviceDTO(
                type.getId(),
                type.getNomtype(),
                type.getDateCreation()
        );
    }

    @Override
    public Type toEntity(Type_serviceDTO typeServiceDTO) {
        if(typeServiceDTO ==null)
            return null;

        Type type = new Type();
        type.setId(typeServiceDTO.getId());
        type.setNomtype(typeServiceDTO.getNomtype());
        type.setDateCreation(LocalDateTime.now());
        return type;
    }
}
