package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.AdressUser;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.AdressUserReposytory;
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
    private Mapper<AdressUserDTO, AdressUser> adressUserMapper;
    @Autowired
    private AdressUserReposytory adressUserReposytory;

    @Override
    public void creat(AdressUserDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        if (adressUserReposytory.existsById(toDTO.getId()))
            throw new AdressUserExisteExeption(toDTO.getId());

        adressUserReposytory.save(adressUserMapper.toEntity(toDTO));
    }

    @Override
    public AdressUserDTO readOne(Integer integer) throws AdressUserFoundExeption {
        AdressUser entity = adressUserReposytory.findById(integer)
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
    public List<AdressUserDTO> selonPersonne(PersonneSimplifierDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return adressUserReposytory.findAllByAppartienA(personneMapper.toEntity(personne)).stream()
                .map(adressUserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
