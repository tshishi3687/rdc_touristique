package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.DureeOnLineDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import com.example.rdc_touristique.data_access.repository.DureeOnLineRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DureeLocationService implements CrudService<DureeOnLineDTO, Integer>{

    @Autowired
    private DureeOnLineRepository dureeLocationRepository;
    @Autowired
    private Mapper<DureeOnLineDTO, BienMisEnLigne> dureeLocationMapper;

    @Override
    public void creat(DureeOnLineDTO toDTO) throws DureeLocationExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if(dureeLocationRepository.existsById(toDTO.getId()))
            throw new DureeLocationExisteExeption(toDTO.getId());

        dureeLocationRepository.save(dureeLocationMapper.toEntity(toDTO));
    }

    @Override
    public DureeOnLineDTO readOne(Integer integer) throws DureeLocationFoundExeption{
        BienMisEnLigne entity = dureeLocationRepository.findById(integer)
                .orElseThrow(()-> new DureeLocationFoundExeption(integer));
        return dureeLocationMapper.toDTO(entity);
    }

    @Override
    public List<DureeOnLineDTO> readAll() {
        return dureeLocationRepository.findAll().stream()
                .map(dureeLocationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(DureeOnLineDTO toUpdate) throws DureeLocationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!dureeLocationRepository.existsById(toUpdate.getId()))
            throw new DureeLocationFoundExeption(toUpdate.getId());
        dureeLocationRepository.save(dureeLocationMapper.toEntity(toUpdate));
    }

    @Override
    public void delete(Integer toDelete) throws DureeLocationFoundExeption {
        if(!dureeLocationRepository.existsById(toDelete))
            throw new DureeLocationFoundExeption(toDelete);
        dureeLocationRepository.deleteById(toDelete);
    }
}
