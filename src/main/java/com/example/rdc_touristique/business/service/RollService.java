package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.RollDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Roll;
import com.example.rdc_touristique.data_access.repository.RollRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RollService implements CrudService<RollDTO, Integer> {

    @Autowired
    private Mapper<RollDTO, Roll> rollMapper;
    @Autowired
    private RollRepository rollRepository;

    @Override
    public void creat(RollDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, RollExisteExeption {
        if(rollRepository.existsById(toDTO.getId()))
            throw new RollExisteExeption(toDTO.getId());
        rollRepository.save(rollMapper.toEntity(toDTO));
    }

    @Override
    public RollDTO readOne(Integer integer) throws  RollFoundExeption {
        Roll entity = rollRepository.findById(integer)
                .orElseThrow(()-> new RollFoundExeption(integer));
        return rollMapper.toDTO(entity);
    }

    @Override
    public List<RollDTO> readAll() {
        return rollRepository.findAll().stream()
                .map(rollMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(RollDTO toUpdate) throws RollFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!rollRepository.existsById(toUpdate.getId()))
            throw new RollFoundExeption(toUpdate.getId());
        rollRepository.save(rollMapper.toEntity(toUpdate));
    }

    @Override
    public void delete(Integer toDelete) throws RollFoundExeption {
        if(!rollRepository.existsById(toDelete))
            throw new RollFoundExeption(toDelete);

        rollRepository.deleteById(toDelete);
    }
}
