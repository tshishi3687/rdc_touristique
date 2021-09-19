package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ContactUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactUserService implements CrudService<ContactUserDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<ContactUserDTO, ContactUser> contactUserMapper;
    @Autowired
    private ContactUserRepository contactUserRepository;


    @Override
    public void creat(ContactUserDTO toDTO) throws ContactUserExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if (contactUserRepository.existsById(toDTO.getId()))
            throw new ContactUserExisteExeption(toDTO.getId());
        contactUserRepository.save(contactUserMapper.toEntity(toDTO));
    }

    @Override
    public ContactUserDTO readOne(Integer integer) throws ContactUserFoundExeption {
        ContactUser entity = contactUserRepository.findById(integer)
                .orElseThrow(()-> new ContactUserFoundExeption(integer));

        return contactUserMapper.toDTO(entity);
    }

    @Override
    public List<ContactUserDTO> readAll() {
        return contactUserRepository.findAll().stream()
                .map(contactUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ContactUserDTO toUpdate) throws ContactUserFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if (!contactUserRepository.existsById(toUpdate.getId()))
            throw new ContactUserFoundExeption(toUpdate.getId());

        contactUserRepository.save(contactUserMapper.toEntity(toUpdate));
    }

    @Override
    public void delete(Integer toDelete) throws ContactUserFoundExeption {
        if (!contactUserRepository.existsById(toDelete))
            throw new ContactUserFoundExeption(toDelete);

        contactUserRepository.deleteById(toDelete);
    }

    @Transactional
    public List<ContactUserDTO> selonPersonne(PersonneSimplifierDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return contactUserRepository.findAllByAppartienA(personneMapper.toEntity(personne)).stream()
                .map(contactUserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
