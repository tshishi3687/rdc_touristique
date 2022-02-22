package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.AdressePersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.AdressUserReposytory;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressePersonneService implements CrudService<AdressUserDTO, Integer>{

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<AdressUserDTO, AdressePersonne> adressUserMapper;
    @Autowired
    private AdressUserReposytory adressUserReposytory;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public void creat(AdressUserDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        if (adressUserReposytory.existsByAppartienA(JwtRequestFilter.maPersonne())){
            int idadress = adressUserReposytory.findOneByAppartienA(JwtRequestFilter.maPersonne()).getId();
            throw new AdressUserExisteExeption(idadress);

        }

        adressUserReposytory.save(adressUserMapper.toEntity(toDTO));
    }

    @Override
    public AdressUserDTO readOne(Integer integer) throws AdressUserFoundExeption {
        AdressePersonne entity = adressUserReposytory.findById(integer)
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
    public AdressUserDTO selonPersonne() throws PersonneInfoExisteExeption {
        return adressUserMapper.toDTO(JwtRequestFilter.maPersonne().getAdresse());
    }
}
