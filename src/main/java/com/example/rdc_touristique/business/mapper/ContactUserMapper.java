package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.ContactUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class ContactUserMapper implements Mapper<ContactUserDTO, ContactUser> {

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;


    @Override
    public ContactUserDTO toDTO(ContactUser contactUser) {
        if (contactUser == null)
            return null;

        return new ContactUserDTO(
                contactUser.getId(),
                contactUser.getEmail(),
                contactUser.getTelephone(),
                personneMapper.toDTO(contactUser.getAppartienA())
        );
    }

    @Override
    public ContactUser toEntity(ContactUserDTO contactUserDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (contactUserDTO==null)
            return null;
        ContactUser contactUser = new ContactUser();
        contactUser.setId(contactUserDTO.getId());
        contactUser.setEmail(contactUserDTO.getEmail());
        contactUser.setTelephone(contactUserDTO.getTelephone());
        contactUser.setAppartienA(personneReposytory.getOne(contactUserDTO.getAppartienA().getId()));
        return contactUser;
    }
}
