package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.DureeLocationDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.DureeLocation;
import com.example.rdc_touristique.data_access.repository.DureeLocationRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DureeLocationService implements CrudService<DureeLocationDTO, Integer>{

    @Autowired
    private DureeLocationRepository dureeLocationRepository;
    @Autowired
    private Mapper<DureeLocationDTO, DureeLocation> dureeLocationMapper;

    @Override
    public void creat(DureeLocationDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {

    }

    @Override
    public DureeLocationDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption {
        return null;
    }

    @Override
    public List<DureeLocationDTO> readAll() {
        return dureeLocationRepository.findAll().stream()
                .map(dureeLocationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(DureeLocationDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }
}
