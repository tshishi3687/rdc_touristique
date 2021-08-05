package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.Type_serviceDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Type;
import com.example.rdc_touristique.data_access.repository.TypeRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService implements CrudService<Type_serviceDTO, Integer> {

    @Autowired
    private Mapper<Type_serviceDTO, Type> typeMapper;
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void creat(Type_serviceDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (typeRepository.existsById(toCreat.getId()))
            throw new TypeExisteExeption(toCreat.getId());

        typeRepository.save(typeMapper.toEntity(toCreat));
    }

    @Override
    public Type_serviceDTO readOne(Integer integer) throws TypeFoundExeption {
        Type entity = typeRepository.findById(integer)
                .orElseThrow(()-> new TypeFoundExeption(integer));

        return typeMapper.toDTO(entity);
    }

    @Override
    public List<Type_serviceDTO> readAll() {
        return typeRepository.findAll().stream()
                .map(typeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Type_serviceDTO toUpdate) throws TypeFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
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
