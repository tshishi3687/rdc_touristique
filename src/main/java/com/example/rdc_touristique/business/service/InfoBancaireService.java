package com.example.rdc_touristique.business.service;


import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.InfoBancaire;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.InfoBancaireRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoBancaireService implements CrudService<InfoBancaireDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<InfoBancaireDTO, InfoBancaire> infoBancaireMapper;
    @Autowired
    private InfoBancaireRepository infoBancaireRepository;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public void creat(InfoBancaireDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if (infoBancaireRepository.existsByAppartienA(personneMapper.toEntity(toDTO.getAppartienA()))){
            int idInfoB = infoBancaireRepository.findOneByAppartienA(personneMapper.toEntity(toDTO.getAppartienA())).getId();
            throw new InfoBancaireExisteExeption(idInfoB);
        }


        infoBancaireRepository.save(infoBancaireMapper.toEntity(toDTO));
    }

    @Override
    public InfoBancaireDTO readOne(Integer integer) throws InfoBancaireFoundExeption {
        InfoBancaire entity = infoBancaireRepository.findById(integer)
                .orElseThrow(()-> new InfoBancaireFoundExeption(integer));
        return infoBancaireMapper.toDTO(entity);
    }

    @Override
    public List<InfoBancaireDTO> readAll() {
        return infoBancaireRepository.findAll().stream()
                .map(infoBancaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(InfoBancaireDTO toUpdate) throws InfoBancaireFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if (!infoBancaireRepository.existsById(toUpdate.getId()))
            throw new InfoBancaireFoundExeption(toUpdate.getId());

        infoBancaireRepository.save(infoBancaireMapper.toEntity(toUpdate));
    }

    @Override
    public void delete(Integer toDelete) throws InfoBancaireFoundExeption {
        if (!infoBancaireRepository.existsById(toDelete))
            throw new InfoBancaireFoundExeption(toDelete);

        infoBancaireRepository.deleteById(toDelete);
    }

    @Transactional
    public InfoBancaireDTO selonPersonne(PersonneSimplifierDTO personne) throws PersonneInfoExisteExeption {
        if (!personneReposytory.existsById(personne.getId()))
            throw new PersonneInfoExisteExeption(personne.getId());
        Personne entity = personneReposytory.getOne(personne.getId());
        return infoBancaireMapper.toDTO(entity.getInfoBancaires());
    }
}
