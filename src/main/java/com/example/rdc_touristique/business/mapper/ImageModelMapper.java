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
        );
    }

    @Override
    public ImageModel toEntity(ImageModelDTO imageModelDTO) {
        if (imageModelDTO == null)
            return null;

        ImageModel img = new ImageModel();
        return img;
    }

}
