package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.Type_bienDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Type_bien;
import com.example.rdc_touristique.data_access.repository.Type_bienRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Type_bienService implements CrudService<Type_bienDTO, Integer> {

    @Autowired
    private Mapper<Type_bienDTO, Type_bien> type_bienMapper;
    @Autowired
    private Type_bienRepository type_bienRepository;

    @Override
    public void creat(Type_bienDTO toCreat) throws ElementAlreadyExistsException {
        if (type_bienRepository.existsById(toCreat.getId()))
            throw new Type_bienExisteExeption(toCreat.getId());

        type_bienRepository.save(type_bienMapper.toEntity(toCreat));
    }

    @Override
    public Type_bienDTO readOne(Integer integer) throws Type_bienFoundExeption {
        Type_bien entity = type_bienRepository.findById(integer)
                .orElseThrow(()-> new Type_bienFoundExeption(integer));

        return type_bienMapper.toDTO(entity);
    }

    @Override
    public List<Type_bienDTO> readAll() {
        return type_bienRepository.findAll().stream()
                .map(type_bienMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Type_bienDTO toUpdate) throws Type_bienFoundExeption {
        if( !type_bienRepository.existsById( toUpdate.getId() ))
            throw new Type_bienFoundExeption(toUpdate.getId());

        type_bienRepository.save( type_bienMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws Type_bienFoundExeption {
        if( !type_bienRepository.existsById(toDelete))
            throw new Type_bienFoundExeption(toDelete);

        type_bienRepository.deleteById(toDelete);
    }
}
