package com.example.rdc_touristique.business.mapper;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Mapper<DTO, Entity> {
    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
