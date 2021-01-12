package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.TypeDTO;
import com.example.rdc_touristique.data_access.entity.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper implements Mapper<TypeDTO, Type>{
    @Override
    public TypeDTO toDTO(Type type) {
        if(type==null)
            return null;

        return new TypeDTO(
                type.getId(),
                type.getNomtype()
        );
    }

    @Override
    public Type toEntity(TypeDTO typeDTO) {
        if(typeDTO==null)
            return null;

        Type type = new Type();
        type.setId(typeDTO.getId());
        type.setNomtype(typeDTO.getNomtype());
        return type;
    }
}
