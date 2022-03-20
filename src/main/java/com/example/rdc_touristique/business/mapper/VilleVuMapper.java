package com.example.rdc_touristique.business.mapper;



import com.example.rdc_touristique.business.dto.VilleVuDTO;
import com.example.rdc_touristique.business.service.ImageVilleService;
import com.example.rdc_touristique.data_access.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class VilleVuMapper implements Mapper<VilleVuDTO, Ville> {
    @Autowired
    private ImageVilleService imageVilleService;

    @Override
    public VilleVuDTO toDTO(Ville ville) {
        return new VilleVuDTO(
                ville.getId(),
                ville.getNomVille(),
                ville.getNhabitant(),
                ville.getDesciption(),
                imageVilleService.getImage(ville.getId())
        );
    }

    @Override
    public Ville toEntity(VilleVuDTO villeVuDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
