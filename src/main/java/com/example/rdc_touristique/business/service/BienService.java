package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BienService implements CrudService<BienDTO, Integer> {

    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private CoordonneeRepository coordorRepository;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Transactional
    public List<BienDTO> selonLaPersonne(PersonneSimplifierDTO personne) {
        List<Bien> listBien = bienRepository.findAllByAppartient(personneMapper.toEntity(personne));
        return bienRepository.findAllByAppartient(personneMapper.toEntity(personne)).stream()
        .map(bienMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public void creat(BienDTO toCreat) throws ElementAlreadyExistsException {
        if (bienRepository.existsById(toCreat.getId()))
            throw new BienExisteExeption(toCreat.getId());

        Bien entity = bienMapper.toEntity(toCreat);
        coordorRepository.save(entity.getCoordonnee());
        bienRepository.save(entity);
    }

    @Override
    public BienDTO readOne(Integer integer) throws  BienFoundExeption {
        Bien entity = bienRepository.findById(integer)
                .orElseThrow(()-> new BienFoundExeption(integer));

        return bienMapper.toDTO(entity);
    }

    @Override
    public List<BienDTO> readAll() {
        return bienRepository.findAll().stream()
                .map(bienMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BienDTO toUpdate) throws  BienFoundExeption {
        if( !bienRepository.existsById( toUpdate.getId() ))
            throw new BienFoundExeption(toUpdate.getId());

        bienRepository.save( bienMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws BienFoundExeption {
        if( !bienRepository.existsById(toDelete))
            throw new BienFoundExeption(toDelete);

        bienRepository.deleteById(toDelete);
    }
}
