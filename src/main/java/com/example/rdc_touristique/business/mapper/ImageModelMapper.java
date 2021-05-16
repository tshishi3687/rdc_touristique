package com.example.rdc_touristique.business.mapper;


import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ImageModelMapper implements Mapper<ImageModelDTO, ImageModel>{

    @Override
    public ImageModelDTO toDTO(ImageModel imageModel) {
        if (imageModel== null)
            return null;
        return new ImageModelDTO(
                imageModel.getId(),
                imageModel.getImage()
        );
    }

    @Override
    public ImageModel toEntity(ImageModelDTO imageModelDTO) {
        if (imageModelDTO == null)
            return null;
        ImageModel img = new ImageModel();
        img.setId(imageModelDTO.getId());
        img.setImage(imageModelDTO.getImage());
        return img;
    }

}
