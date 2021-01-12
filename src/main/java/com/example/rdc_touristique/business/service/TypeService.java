package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.TypeDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Type;
import com.example.rdc_touristique.data_access.repository.TypeRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService implements CrudService<TypeDTO, Integer> {

    @Autowired
    private Mapper<TypeDTO, Type> typeMapper;
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void creat(TypeDTO toCreat) throws ElementAlreadyExistsException {
        if (typeRepository.existsById(toCreat.getId()))
            throw new TypeExisteExeption(toCreat.getId());

        typeRepository.save(typeMapper.toEntity(toCreat));
    }

    @Override
    public TypeDTO readOne(Integer integer) throws TypeFoundExeption {
        Type entity = typeRepository.findById(integer)
                .orElseThrow(()-> new TypeFoundExeption(integer));

        return typeMapper.toDTO(entity);
    }

    @Override
    public List<TypeDTO> readAll() {
        return typeRepository.findAll().stream()
                .map(typeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TypeDTO toUpdate) throws TypeFoundExeption {
        if( !typeRepository.existsById( toUpdate.getId() ))
            throw new TypeFoundExeption(toUpdate.getId());

        typeRepository.save( typeMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws TypeFoundExeption {
        if( !typeRepository.existsById(toDelete))
            throw new TypeFoundExeption(toDelete);

        typeRepository.deleteById(toDelete);
    }
}
