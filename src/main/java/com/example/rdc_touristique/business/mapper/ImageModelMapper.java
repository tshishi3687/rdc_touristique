package com.example.rdc_touristique.business.mapper;


import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import org.springframework.stereotype.Component;

@Component
public class ImageModelMapper implements Mapper<ImageModelDTO, ImageModel>{

    @Override
    public ImageModelDTO toDTO(ImageModel imageModel) {
        if (imageModel== null)
            return null;
        return new ImageModelDTO(
                imageModel.getId(),
                imageModel.getName(),
                imageModel.getType(),
                imageModel.getPicByte()
        );
    }

    @Override
    public ImageModel toEntity(ImageModelDTO imageModelDTO) {
        if (imageModelDTO == null)
            return null;

        ImageModel imageModel = new ImageModel();
        imageModel.setId(imageModelDTO.getId());
        imageModel.setName(imageModelDTO.getName());
        imageModel.setType(imageModelDTO.getType());
        imageModel.setPicByte(imageModelDTO.getPicByte());
        return null;
    }
}
