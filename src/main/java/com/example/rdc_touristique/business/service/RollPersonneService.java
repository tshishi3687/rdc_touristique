package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.RollDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.RolePersonne;
import com.example.rdc_touristique.data_access.repository.RollRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RollPersonneService implements CrudService<RollDTO, Integer> {

    @Autowired
    private Mapper<RollDTO, RolePersonne> rollMapper;
    @Autowired
    private RollRepository rollRepository;

    @Override
    @Secured("Admin")
    public void creat(RollDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(rollRepository.existsById(toDTO.getId()))
            throw new RollExisteExeption(toDTO.getId());
        rollRepository.save(rollMapper.toEntity(toDTO));
    }

    @Override
    @Secured("Admin")
    public RollDTO readOne(Integer integer) throws  RollFoundExeption {
        RolePersonne entity = rollRepository.findById(integer)
                .orElseThrow(()-> new RollFoundExeption(integer));
        return rollMapper.toDTO(entity);
    }

    @Override
    @Secured("Admin")
    public List<RollDTO> readAll() {
        return rollRepository.findAll().stream()
                .map(rollMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Secured("Admin")
    public void update(RollDTO toUpdate) throws RollFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!rollRepository.existsById(toUpdate.getId()))
            throw new RollFoundExeption(toUpdate.getId());
        rollRepository.save(rollMapper.toEntity(toUpdate));
    }

    @Override
    @Secured("Admin")
    public void delete(Integer toDelete) throws RollFoundExeption {
        if(!rollRepository.existsById(toDelete))
            throw new RollFoundExeption(toDelete);

        rollRepository.deleteById(toDelete);
    }
}
