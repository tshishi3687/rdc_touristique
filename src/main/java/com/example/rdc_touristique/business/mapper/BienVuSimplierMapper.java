package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BienVuSimplierMapper implements Mapper<BienVuSimplifierDTO, Bien>{

    @Autowired
    private Mapper<Type_bienDTO, Type_bien> type_bienMapper;
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Mapper<AladispositionDTO, Aladisposition> aladispositionMapper;
    @Autowired
    private ImageModelService imageModelService;
    @Autowired
    private Mapper<ImageModelDTO, ImageBien> imageModelServiceMapper;
    @Autowired
    private BienRepository bienRepository;

    @Override
    public BienVuSimplifierDTO toDTO(Bien bien) {

        if (imageModelService.getImage(bien.getId()).size() <= 0)
            bienRepository.deleteById(bien.getId());

        int likes;
        if((bien.getLikes()== null) || (bien.getLikes().size()<=0)){
            likes = 0;
        }else{
            likes = bien.getLikes().size();
        }

        return new BienVuSimplifierDTO(
                bien.getId(),
                type_bienMapper.toDTO(bien.getType()),
                aladispositionMapper.toDTO(bien.getAladisposition()),
                bien.getPrix(),
                bien.getNpmax(),
                bien.getNchambre(),
                bien.getNsdb(),
                bien.getSuperficie(),
                coordonneeMapper.toDTO(bien.getCoordonnee()),
                likes,
                imageModelServiceMapper.toDTO(imageModelService.getImage(bien.getId()).get(0))
        );
    }

    @Override
    public Bien toEntity(BienVuSimplifierDTO bienVuSimplifierDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
