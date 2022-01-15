package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.MdpDTO;
import com.example.rdc_touristique.business.dto.PassWordDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.PassWord;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
import com.example.rdc_touristique.data_access.repository.PassWordRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class PassWordService implements CrudService<PassWordDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> mapper;
    @Autowired
    private Mapper<PassWordDTO, PassWord> passWordMapper;
    @Autowired
    private PassWordRepository passWordRepository;


    @Override
    public void creat(PassWordDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if (passWordRepository.existsById(toDTO.getId()))
            throw new PassWordExisteExeption(toDTO.getId());

        PassWord entity = passWordMapper.toEntity(toDTO);
        entity.setMode(true);
        passWordRepository.save(entity);
    }

    @Override
    public PassWordDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {
        return null;
    }

    @Override
    public List<PassWordDTO> readAll() {
        return null;
    }

    @Override
    public void update(PassWordDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }
}
