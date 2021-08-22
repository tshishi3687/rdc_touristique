package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

@Component
public class BienMapper implements Mapper<BienDTO, Bien>{

    @Autowired
    private Mapper<DureeLocationDTO, DureeLocation> dureeLocationMapper;
    @Autowired
    private DureeLocationRepository dureeLocationRepository;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<Type_bienDTO, Type_bien> type_bienMapper;
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Type_bienRepository type_bienRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private Mapper<AladispositionDTO, Aladisposition> aladispositionMapper;
    @Autowired
    private AladispositionRepository aladispositionRepository;

    @Override
    public BienDTO toDTO(Bien bien) {
        if (bien==null)
            return null;

        return new BienDTO(
                bien.getId(),
                type_bienMapper.toDTO(bien.getType()),
                dureeLocationMapper.toDTO(bien.getDureeLocation()),
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
                personneMapper.toDTO(bien.getAppartient()),
                bien.getDateCreation()
        );
    }

    @Override
    public Bien toEntity(BienDTO bienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (bienDTO==null)
            return null;

        Bien bien = new Bien();
        bien.setId(bienDTO.getId());
        bien.setType(type_bienRepository.getOne(bienDTO.getType_bien().getId()));
        bien.setDureeLocation(dureeLocationRepository.getOne(bienDTO.getDureeLocation().getId()));
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
        bien.setDateCreation(LocalDateTime.now());
        return bien;
    }
}
