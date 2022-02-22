package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.RollDTO;
import com.example.rdc_touristique.data_access.entity.AdressePersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.RolePersonne;
import com.example.rdc_touristique.data_access.repository.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class PersonneSimpleMaper implements Mapper<PersonneSimpleDTO, Personne>{

    @Autowired
    private Mapper<RollDTO, RolePersonne> rollMapper;
    @Autowired
    private RollRepository rollRepository;
    @Autowired
    private Mapper<AdressUserDTO, AdressePersonne> adressUserMapper;

    @Override
    public PersonneSimpleDTO toDTO(Personne personne) {
        if(personne==null)
            return null;

        return new PersonneSimpleDTO(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getDdn(),
                rollMapper.toDTO(personne.getRoleId()),
                personne.isActive(),
                adressUserMapper.toDTO(personne.getAdresse()),
                personne.getCreatedAt()
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
        personne.setRoleId(rollRepository.getOne(personneSimpleDTO.getRoll().getId()));
        return personne;
    }
}
