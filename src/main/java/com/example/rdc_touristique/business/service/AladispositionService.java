package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.AladispositionDTO;
import com.example.rdc_touristique.exeption.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class AladispositionService implements CrudService<AladispositionDTO, Integer>{
    @Override
    public void creat(AladispositionDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {

    }

    @Override
    public AladispositionDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption {
        return null;
    }

    @Override
    public List<AladispositionDTO> readAll() {
        return null;
    }

    @Override
    public void update(AladispositionDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }
}
