package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.Lien_photoDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Lien_photo;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

@Service
public class Lien_photoService implements CrudService<Lien_photoDTO, Integer> {

    @Autowired
    private Mapper<Lien_photoDTO, Lien_photo> lien_photoMapper;
    @Autowired
    private Lien_photoRepository lien_photoRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private BienRepository bienRepository;

    @Override
    public void creat(Lien_photoDTO toCreat) throws ElementAlreadyExistsException {
        if (lien_photoRepository.existsById(toCreat.getId()))
            throw new Lien_photoExisteExeption(toCreat.getId());

        Lien_photo entity = lien_photoMapper.toEntity(toCreat);
        imageRepository.save(entity.getImage());
        provinceRepository.save(entity.getProvince());
        villeRepository.save(entity.getVille());
        bienRepository.save(entity.getBien());

        lien_photoRepository.save(entity);
    }

    @Override
    public Lien_photoDTO readOne(Integer integer) throws Lien_photoFoundExeption {
        Lien_photo entity = lien_photoRepository.findById(integer)
                .orElseThrow(()-> new Lien_photoFoundExeption(integer));

        return lien_photoMapper.toDTO(entity);
    }

    @Override
    public List<Lien_photoDTO> readAll() {
        return lien_photoRepository.findAll().stream()
                .map(lien_photoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Lien_photoDTO toUpdate) throws Lien_photoFoundExeption {
        if( !lien_photoRepository.existsById( toUpdate.getId() ))
            throw new Lien_photoFoundExeption(toUpdate.getId());

        lien_photoRepository.save( lien_photoMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws Lien_photoFoundExeption {
        if( !lien_photoRepository.existsById(toDelete))
            throw new Lien_photoFoundExeption(toDelete);

        lien_photoRepository.deleteById(toDelete);
    }
}
