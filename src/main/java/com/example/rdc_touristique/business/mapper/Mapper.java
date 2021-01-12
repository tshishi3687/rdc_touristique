package com.example.rdc_touristique.business.mapper;

public interface Mapper<DTO, Entity> {
    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);
}
