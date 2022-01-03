package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class BienVuMapper implements Mapper<BienVuDTO, Bien>{

    @Autowired
    private Mapper<DureeOnLineDTO, BienMisEnLigne> dureeLocationMapper;
    @Autowired
    private Mapper<Type_bienDTO, Type_bien> type_bienMapper;
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Mapper<AladispositionDTO, Aladisposition> aladispositionMapper;
    @Autowired
    private ImageModelService imageModelService;

    @Override
    public BienVuDTO toDTO(Bien bien) {
        if (bien==null)
            return null;

        int likes;
        if((bien.getLikes()== null) || (bien.getLikes().size()<=0)){
            likes = 0;
        }else{
            likes = bien.getLikes().size();
        }

        return new BienVuDTO(
                bien.getId(),
                type_bienMapper.toDTO(bien.getType()),
                aladispositionMapper.toDTO(bien.getAladisposition()),
                bien.getPrix(),
                bien.getNpmin(),
                bien.getNpmax(),
                bien.getNchambre(),
                bien.getNsdb(),
                bien.getNwc(),
                bien.getSuperficie(),
                bien.getDescription(),
                coordonneeMapper.toDTO(bien.getCoordonnee()),
                bien.getDateCreation(),
                likes,
                bien.isModeActive(),
                imageModelService.getImage(bien.getId()),
                0,
                bien.getDateFinMisEnLigne()
        );
    }

    @Override
    public Bien toEntity(BienVuDTO bienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
