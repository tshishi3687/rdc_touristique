package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Adresse;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.AdressUserReposytory;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressUserService implements CrudService<AdressUserDTO, Integer>{

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<AdressUserDTO, Adresse> adressUserMapper;
    @Autowired
    private AdressUserReposytory adressUserReposytory;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public void creat(AdressUserDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        if (adressUserReposytory.existsByAppartienA(personneMapper.toEntity(toDTO.getAppartienA()))){
            int idadress = adressUserReposytory.findOneByAppartienA(personneMapper.toEntity(toDTO.getAppartienA())).getId();
            throw new AdressUserExisteExeption(idadress);

        }

        adressUserReposytory.save(adressUserMapper.toEntity(toDTO));
    }

    @Override
    public AdressUserDTO readOne(Integer integer) throws AdressUserFoundExeption {
        Adresse entity = adressUserReposytory.findById(integer)
                .orElseThrow(()-> new AdressUserFoundExeption(integer));
        return adressUserMapper.toDTO(entity);
    }

    @Override
    public List<AdressUserDTO> readAll() {
        return adressUserReposytory.findAll().stream()
                .map(adressUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(AdressUserDTO toUpdate) throws AdressUserFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if (!adressUserReposytory.existsById(toUpdate.getId()))
            throw new AdressUserFoundExeption(toUpdate.getId());

        adressUserReposytory.save(adressUserMapper.toEntity(toUpdate));
    }

    @Override
    public void delete(Integer toDelete) throws AdressUserFoundExeption {
        if (!adressUserReposytory.existsById(toDelete))
            throw new AdressUserFoundExeption(toDelete);

        adressUserReposytory.deleteById(toDelete);

    }

    @Transactional
    public AdressUserDTO selonPersonne(PersonneSimplifierDTO personne) throws PersonneInfoExisteExeption {
        if (!personneReposytory.existsById(personne.getId()))
            throw new PersonneInfoExisteExeption(personne.getId());
        Personne entity = personneReposytory.getOne(personne.getId());
        return adressUserMapper.toDTO(entity.getAdresse());
    }
}
