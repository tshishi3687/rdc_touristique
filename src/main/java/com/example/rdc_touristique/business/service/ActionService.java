package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ActionDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Action;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ActionRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ActionService implements CrudService<ActionDTO, Integer>{


    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<ActionDTO, Action> actionMapper;
    @Autowired
    private ActionRepository actionRepository;

    @Override
    public void creat(ActionDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if (actionRepository.existsById(toDTO.getId()))
            throw new ActionFoundExeption(toDTO.getId());

        actionRepository.save(actionMapper.toEntity(toDTO));
    }

    @Override
    public ActionDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption {
        Action action = actionRepository.findById(integer)
                .orElseThrow(()-> new ActionFoundExeption(integer));
        return actionMapper.toDTO(action);
    }

    @Override
    public List<ActionDTO> readAll() {
        return actionRepository.findAll().stream()
                .map(actionMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<ActionDTO> readAllByDate(LocalDate date) {
        return actionRepository.findAllByDate(date).stream()
                .map(actionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ActionDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if(!actionRepository.existsById(toUpdate.getId()))
            throw new ActionFoundExeption(toUpdate.getId());

        actionRepository.save(actionMapper.toEntity(toUpdate));
    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption {
        if(!actionRepository.existsById(toDelete))
            throw new ActionFoundExeption(toDelete);

        actionRepository.deleteById(toDelete);
    }
}
