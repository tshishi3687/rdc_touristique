package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VilleService implements CrudService<VilleDTO, Integer> {

    @Autowired
    public Mapper<VilleDTO, Ville> villeMapper;
    @Autowired
    private VilleRepository villeRepository;

    @Override
    public void creat(VilleDTO toCreat) throws ElementAlreadyExistsException {
        if (villeRepository.existsById(toCreat.getId()))
            throw new VilleExisteExeption(toCreat.getId());
        System.out.println(villeMapper.toEntity(toCreat));
        villeRepository.save(villeMapper.toEntity(toCreat));
    }

    @Override
    public VilleDTO readOne(Integer integer) throws VilleFoundExeption {
        Ville entity = villeRepository.findById(integer)
                .orElseThrow(()-> new VilleFoundExeption(integer));

        return villeMapper.toDTO(entity);
    }

    @Override
    public List<VilleDTO> readAll() {
        return villeRepository.findAll().stream()
                .map(villeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(VilleDTO toUpdate) throws VilleFoundExeption {
        if( !villeRepository.existsById( toUpdate.getId() ))
            throw new VilleFoundExeption(toUpdate.getId());

        villeRepository.save( villeMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws VilleFoundExeption {
        if( !villeRepository.existsById(toDelete))
            throw new VilleFoundExeption(toDelete);

        villeRepository.deleteById(toDelete);
    }
}
