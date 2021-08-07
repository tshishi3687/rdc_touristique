package com.example.rdc_touristique.business.mapper;


import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.data_access.entity.ImageBien;
import org.springframework.stereotype.Component;

@Component
public class ImageModelMapper implements Mapper<ImageModelDTO, ImageBien>{

    @Override
    public ImageModelDTO toDTO(ImageBien imageModel) {
        if (imageModel== null)
            return null;

        return new ImageModelDTO(
        );
    }

    @Override
    public ImageBien toEntity(ImageModelDTO imageModelDTO) {
        if (imageModelDTO == null)
            return null;

        ImageBien img = new ImageBien();
        return img;
    }

}
