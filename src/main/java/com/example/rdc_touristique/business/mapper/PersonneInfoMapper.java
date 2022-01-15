package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.data_access.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.stream.Collectors;

@Component
public class PersonneInfoMapper implements Mapper<PersonneInfoDTO, Personne>{

    @Autowired
    private Mapper<BienDTO, Bien> bienDTOMapper;


    @Override
    public PersonneInfoDTO toDTO(Personne personne) {
        if(personne==null)
            return null;

        return null;
    }

    @Override
    public Personne toEntity(PersonneInfoDTO personneInfoDTO) {
        if(personneInfoDTO==null)
            return null;

        Personne personne = new Personne();
        personne.setId(personneInfoDTO.getId());
        personne.setBien(personneInfoDTO.getBien().stream()
                    .map(bienDTO -> {
                        try {
                            return bienDTOMapper.toEntity(bienDTO);
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList()));

        return null;
//        return personne;
    }
}
