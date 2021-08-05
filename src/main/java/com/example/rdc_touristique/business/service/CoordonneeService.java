package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Coordonnee;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoordonneeService implements CrudService<CoordonneeDTO, Integer> {
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private CoordonneeRepository coordonneeRepository;


    @Override
    public void creat(CoordonneeDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(coordonneeRepository.existsById(toCreat.getId()))
            throw new CoordonneeExisteExeption(toCreat.getId());

        coordonneeRepository.save(coordonneeMapper.toEntity(toCreat));
    }

    @Override
    public CoordonneeDTO readOne(Integer integer) throws CoordonneeFoundExeption {
        Coordonnee entity = coordonneeRepository.findById(integer)
                .orElseThrow(()-> new CoordonneeFoundExeption(integer));

        return coordonneeMapper.toDTO(entity);
    }

    @Override
    public List<CoordonneeDTO> readAll() {
        return coordonneeRepository.findAll().stream()
                .map(coordonneeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(CoordonneeDTO toUpdate) throws CoordonneeFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !coordonneeRepository.existsById( toUpdate.getId() ))
            throw new CoordonneeFoundExeption(toUpdate.getId());

        coordonneeRepository.save( coordonneeMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws CoordonneeFoundExeption {
        if( !coordonneeRepository.existsById(toDelete))
            throw new CoordonneeFoundExeption(toDelete);

        coordonneeRepository.deleteById(toDelete);
    }
}
