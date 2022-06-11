package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.AladispositionDTO;
import com.example.rdc_touristique.business.dto.BienVuSimplifierDTO;
import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.dto.Type_bienDTO;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.Aladisposition;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Coordonnee;
import com.example.rdc_touristique.data_access.entity.Type_bien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

    @Override
    public BienVuSimplifierDTO toDTO(Bien bien) {
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
                bien.getNpmin(),
                bien.getNpmax(),
                bien.getNchambre(),
                bien.getNsdb(),
                bien.getNwc(),
                bien.getSuperficie(),
                bien.getDescription(),
                coordonneeMapper.toDTO(bien.getCoordonnee()),
                likes,
                bien.isModeActive(),
                imageModelService.getImage(bien.getId()).get(0)
        );
    }

    @Override
    public Bien toEntity(BienVuSimplifierDTO bienVuSimplifierDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
