package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PaysDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.AdressUser;
import com.example.rdc_touristique.data_access.entity.Pays;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.PaysRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class AdressUserMapper implements Mapper<AdressUserDTO, AdressUser>{

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<PaysDTO, Pays> paysMapper;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private PaysRepository paysRepository;



    @Override
    public AdressUserDTO toDTO(AdressUser adressUser) {
        if (adressUser==null)
            return null;
        return new AdressUserDTO(
                adressUser.getId(),
                adressUser.getNumHabitation(),
                adressUser.getNomRue(),
                adressUser.getCodePostal(),
                paysMapper.toDTO(adressUser.getPays()),
                personneMapper.toDTO(adressUser.getAppartienA())
        );
    }

    @Override
    public AdressUser toEntity(AdressUserDTO adressUserDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (adressUserDTO==null)
            return null;

        AdressUser adressUser = new AdressUser();
        adressUser.setId(adressUserDTO.getId());
        adressUser.setNumHabitation(adressUserDTO.getNumHabitation());
        adressUser.setNomRue(adressUserDTO.getNomRue());
        adressUser.setCodePostal(adressUserDTO.getCodePostal());
        adressUser.setPays(paysRepository.getOne(adressUserDTO.getPays().getId()));
        adressUser.setAppartienA(personneReposytory.getOne(adressUserDTO.getAppartienA().getId()));
        return adressUser;
    }
}
