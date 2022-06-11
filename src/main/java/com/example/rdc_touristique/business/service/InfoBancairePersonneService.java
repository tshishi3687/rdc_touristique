package com.example.rdc_touristique.business.service;


import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.InfoBancairePersonne;
import com.example.rdc_touristique.data_access.repository.InfoBancaireRepository;
import com.example.rdc_touristique.exeption.*;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoBancairePersonneService implements CrudService<InfoBancaireDTO, Integer> {

    @Autowired
    private Mapper<InfoBancaireDTO, InfoBancairePersonne> infoBancaireMapper;
    @Autowired
    private InfoBancaireRepository infoBancaireRepository;

    @Override
    public void creat(InfoBancaireDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if (infoBancaireRepository.existsByAppartienA(JwtRequestFilter.maPersonne())){
            int idInfoB = infoBancaireRepository.findOneByAppartienA(JwtRequestFilter.maPersonne()).getId();
            throw new InfoBancaireExisteExeption(idInfoB);
        }
        infoBancaireRepository.save(infoBancaireMapper.toEntity(toDTO));
    }

    @Override
    public InfoBancaireDTO readOne(Integer integer) throws InfoBancaireFoundExeption {
        InfoBancairePersonne entity = infoBancaireRepository.findById(integer)
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
    public InfoBancaireDTO selonPersonne() throws PersonneInfoExisteExeption {
        return infoBancaireMapper.toDTO(JwtRequestFilter.maPersonne().getInfoBancaires());
    }
}
