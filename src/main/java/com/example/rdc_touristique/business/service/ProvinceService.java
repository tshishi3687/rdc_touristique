package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.repository.ProvinceRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceService implements CrudService<ProvinceDTO, Integer> {

    @Autowired
    private Mapper<ProvinceDTO, Province> provinceMapper;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public void creat(ProvinceDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (provinceRepository.existsById(toCreat.getId()))
            throw new ProvinceExisteExeption(toCreat.getId());

        provinceRepository.save(provinceMapper.toEntity(toCreat));
    }


    @Override
    public ProvinceDTO readOne(Integer integer) throws ProvinceFoundExeption {
        Province entity = provinceRepository.findById(integer)
                .orElseThrow(()-> new ProvinceFoundExeption(integer));

        return provinceMapper.toDTO(entity);
    }

    @Override
    public List<ProvinceDTO> readAll() {
        return provinceRepository.findByOrderByNomprovinceAsc().stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProvinceDTO toUpdate) throws ProvinceFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !provinceRepository.existsById( toUpdate.getId() ))
            throw new ProvinceFoundExeption(toUpdate.getId());

        provinceRepository.save( provinceMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws ProvinceFoundExeption {
        if( !provinceRepository.existsById(toDelete))
            throw new ProvinceFoundExeption(toDelete);

        provinceRepository.deleteById(toDelete);
    }
}
