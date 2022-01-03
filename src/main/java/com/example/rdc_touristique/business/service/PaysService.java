package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.PaysDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Pays;
import com.example.rdc_touristique.data_access.repository.PaysRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaysService implements CrudService<PaysDTO, Integer>{

    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private Mapper<PaysDTO, Pays> paysMapper;

    @Override
    public void creat(PaysDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, MessagingException {
        if (paysRepository.existsById(toDTO.getId()))
            throw new PaysExisteExeption(toDTO.getId());
        paysRepository.save(paysMapper.toEntity(toDTO));
    }

    @Override
    public PaysDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption, PaysFoundExeption {
        Pays entity = paysRepository.findById(integer)
                .orElseThrow(()-> new PaysFoundExeption(integer));
        return paysMapper.toDTO(entity);
    }

    @Override
    public List<PaysDTO> readAll() {
        return paysRepository.findByOrderByNomFrFrAsc().stream()
                .map(paysMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(PaysDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }
}
