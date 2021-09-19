package com.example.rdc_touristique.business.service;


import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.InfoBancaire;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.InfoBancaireRepository;
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

    @Override
    public void creat(InfoBancaireDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if (infoBancaireRepository.existsById(toDTO.getId()))
            throw new InfoBancaireExisteExeption(toDTO.getId());

        List<InfoBancaire> infoBancaireList= infoBancaireRepository.findAllByAppartienA(personneMapper.toEntity(toDTO.getAppartienA()));

        for (InfoBancaire infoBancaire: infoBancaireList){
            if(infoBancaire.isActive()){
                infoBancaire.setActive(false);
                infoBancaireRepository.save(infoBancaire);
            }
        }

        InfoBancaire entity = infoBancaireMapper.toEntity(toDTO);
        entity.setActive(true);
        infoBancaireRepository.save(entity);
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
    public List<InfoBancaireDTO> selonPersonne(PersonneSimplifierDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return infoBancaireRepository.findAllByAppartienA(personneMapper.toEntity(personne)).stream()
                .map(infoBancaireMapper::toDTO)
                .collect(Collectors.toList());
    }
}
