package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.CreatPersonne;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.RollDTO;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Roll;
import com.example.rdc_touristique.data_access.repository.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class PersonneSimpleMaper implements Mapper<PersonneSimpleDTO, Personne>{

    @Autowired
    private Mapper<RollDTO, Roll> rollMapper;
    @Autowired
    private RollRepository rollRepository;

    @Override
    public PersonneSimpleDTO toDTO(Personne personne) {
        if(personne==null)
            return null;

        return new PersonneSimpleDTO(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getDdn(),
                rollMapper.toDTO(personne.getRoll()),
                personne.getDdj(),
                personne.isActive()
        );

    }

    @Override
    public Personne toEntity(PersonneSimpleDTO personneSimpleDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(personneSimpleDTO==null)
            return null;

        Personne personne = new Personne();
        personne.setId(personneSimpleDTO.getId());
        personne.setNom(personneSimpleDTO.getNom());
        personne.setPrenom(personneSimpleDTO.getPrenom());
        personne.setDdn(personneSimpleDTO.getDdn());
        personne.setRoll(rollRepository.getOne(personneSimpleDTO.getRoll().getId()));
        personne.setDdj(LocalDateTime.now());
        return personne;
    }
}
