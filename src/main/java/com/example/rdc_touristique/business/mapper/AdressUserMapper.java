package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PaysDTO;
import com.example.rdc_touristique.data_access.entity.AdressePersonne;
import com.example.rdc_touristique.data_access.entity.Pays;
import com.example.rdc_touristique.data_access.repository.PaysRepository;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class AdressUserMapper implements Mapper<AdressUserDTO, AdressePersonne>{

    @Autowired
    private Mapper<PaysDTO, Pays> paysMapper;
    @Autowired
    private PaysRepository paysRepository;


    @Override
    public AdressUserDTO toDTO(AdressePersonne adresse) {
        if (adresse ==null)
            return null;
        return new AdressUserDTO(
                adresse.getId(),
                adresse.getNumHabitation().charAt(0) + "**",
                adresse.getNomRue().charAt(0) + "*******",
                adresse.getCodePostal().charAt(0) + "*****",
                paysMapper.toDTO(adresse.getPays())
        );
    }

    @Override
    public AdressePersonne toEntity(AdressUserDTO adressUserDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (adressUserDTO==null)
            return null;

        AdressePersonne adresse = new AdressePersonne();
        adresse.setId(adressUserDTO.getId());
        adresse.setNumHabitation(adressUserDTO.getNumHabitation());
        adresse.setNomRue(adressUserDTO.getNomRue());
        adresse.setCodePostal(adressUserDTO.getCodePostal());
        adresse.setPays(paysRepository.getOne(adressUserDTO.getPays().getId()));
        adresse.setAppartienA(JwtRequestFilter.maPersonne());
        return adresse;
    }
}
