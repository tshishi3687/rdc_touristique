package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonneService implements CrudService<PersonneSimpleDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public void creat(PersonneSimpleDTO toCreat) throws ElementAlreadyExistsException {
        if (personneReposytory.existsById(toCreat.getId()))
            throw new PersonneSimpleExisteExeption(toCreat.getId());

        personneReposytory.save(personneMapper.toEntity(toCreat));
    }

    @Override
    public PersonneSimpleDTO readOne(Integer integer) throws PersonneSimpleFoundExeption {
        Personne entity = personneReposytory.findById(integer)
                .orElseThrow(()-> new PersonneSimpleFoundExeption(integer));

        return personneMapper.toDTO(entity);
    }

    @Override
    public List<PersonneSimpleDTO> readAll() {
        return personneReposytory.findAll().stream()
                .map(personneMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(PersonneSimpleDTO toUpdate) throws PersonneSimpleFoundExeption {
        if( !personneReposytory.existsById( toUpdate.getId() ))
            throw new PersonneSimpleFoundExeption(toUpdate.getId());

        personneReposytory.save( personneMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws PersonneSimpleFoundExeption {
        if( !personneReposytory.existsById(toDelete))
            throw new PersonneSimpleFoundExeption(toDelete);

        personneReposytory.deleteById(toDelete);
    }
}
