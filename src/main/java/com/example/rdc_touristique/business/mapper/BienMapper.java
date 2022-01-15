package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class BienMapper implements Mapper<BienDTO, Bien>{

    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Type_bienRepository type_bienRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private Mapper<AladispositionDTO, Aladisposition> aladispositionMapper;

    @Override
    public BienDTO toDTO(Bien bien) {
        return null;
    }

    @Override
    public Bien toEntity(BienDTO bienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (bienDTO==null)
            return null;

        Bien bien = new Bien();
        bien.setId(bienDTO.getId());
        bien.setType(type_bienRepository.getOne(bienDTO.getType_bien().getId()));
        bien.setAladisposition(aladispositionMapper.toEntity(bienDTO.getAladisposition()));
        bien.setPrix(bienDTO.getPrix());
        bien.setNpmin(bienDTO.getNpmin());
        bien.setNpmax(bienDTO.getNpmax());
        bien.setNchambre(bienDTO.getNchambre());
        bien.setNsdb(bienDTO.getNsdb());
        bien.setNwc(bienDTO.getNwc());
        bien.setSuperficie(bienDTO.getSuperficie());
        bien.setDescription(bienDTO.getDescription());
        bien.setCoordonnee(coordonneeMapper.toEntity(bienDTO.getCoordonnee()));
        bien.setAppartient(personneReposytory.getOne(bienDTO.getAppartient().getId()));
        return bien;
    }
}
