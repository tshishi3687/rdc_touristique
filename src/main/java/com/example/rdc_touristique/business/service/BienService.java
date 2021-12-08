package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Action;
import com.example.rdc_touristique.data_access.entity.Aladisposition;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.stream.Collectors;

@Service
public class BienService implements CrudService<BienDTO, Integer> {

    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private CoordonneeRepository coordorRepository;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private Mapper<ActionDTO, Action> actionMapper;
    @Autowired
    private final ActionDTO  actionDTO = new ActionDTO();
    @Autowired
    private AladispositionRepository aladispositionRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private DemandeRepository descriptionRepository;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Transactional
    public List<BienDTO> selonLaPersonne(PersonneSimplifierDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Bien-personne");
        actionDTO.setIdClasse(personne.getId());
        actionDTO.setAction("Recherche");
        actionDTO.setDescription("Recherche de tous les biens appartenants à " + personne.getNom() + " / " + personne.getPrenom());
        actionRepository.save(actionMapper.toEntity(actionDTO));

        return bienRepository.findAllByAppartientOrderByModeActive(personneMapper.toEntity(personne)).stream()
        .map(bienMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public void creat(BienDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public int creatKey(BienDTO toCreat) throws BienExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException, InterruptedException {

        if (bienRepository.existsById(toCreat.getId()))
            throw new BienExisteExeption(toCreat.getId());

        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Bien");
        actionDTO.setIdClasse(toCreat.getId());
        actionDTO.setAction("Création");
        actionDTO.setDescription("Création de/d' " +toCreat.getType_bien().getNom() +
                " bien  à " + toCreat.getCoordonnee().getVille().getNomVille() +
                " dans la province de " + toCreat.getCoordonnee().getVille().getProvince().getNomprovince()
                + " par " + toCreat.getAppartient().getNom() + "-" + toCreat.getAppartient().getPrenom());
        actionRepository.save(actionMapper.toEntity(actionDTO));

        Bien entity = bienMapper.toEntity(toCreat);
        entity.setModeActive(false);
        aladispositionRepository.save(entity.getAladisposition());
        coordorRepository.save(entity.getCoordonnee());
        return bienRepository.save(entity).getId();

    }

    @Override
    public BienDTO readOne(Integer integer) throws  BienFoundExeption {
        Bien entity = bienRepository.findById(integer)
                .orElseThrow(()-> new BienFoundExeption(integer));

        return bienMapper.toDTO(entity);
    }

    @Override
    public List<BienDTO> readAll() {
        return bienRepository.findAllByModeActiveIs(true).stream()
                .map(bienMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BienDTO toUpdate) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !bienRepository.existsById( toUpdate.getId() ))
            throw new BienFoundExeption(toUpdate.getId());

        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Bien");
        actionDTO.setIdClasse(toUpdate.getId());
        actionDTO.setAction("Modification");
        actionDTO.setDescription("Modification de/d' " +toUpdate.getType_bien().getNom() +
                " bien  à " + toUpdate.getCoordonnee().getVille().getNomVille() +
                " dans la province de " + toUpdate.getCoordonnee().getVille().getProvince().getNomprovince()
                + " par " + toUpdate.getAppartient().getNom() + "-" + toUpdate.getAppartient().getPrenom());
        actionRepository.save(actionMapper.toEntity(actionDTO));

        bienRepository.save( bienMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public void activationBien(BienDTO bienDTO) throws BienFoundExeption {
        if( !bienRepository.existsById(bienDTO.getId()))
            throw new BienFoundExeption(bienDTO.getId());

        Bien bien = bienRepository.getOne(bienDTO.getId());

        if (bien.isModeActive()){
            bien.setModeActive(false);
            bienRepository.save(bien);
        }else{
            bien.setModeActive(true);
            bienRepository.save(bien);
        }

    }

    @Transactional
    public void deleteBien(BienDTO bienDTO) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !bienRepository.existsById(bienDTO.getId()))
            throw new BienFoundExeption(bienDTO.getId());

        List<Personne> listPersonne = personneReposytory.findAll();
        for (Personne personne : listPersonne) {
            if (personne.getLikedBien().contains(bienRepository.getOne(bienDTO.getId())))
                personne.getLikedBien().removeIf(bien -> bien.getId() == bien.getId());
        }
        imageRepository.deleteAllByBienid(bienMapper.toEntity(bienDTO));
        descriptionRepository.deleteAllByBienDemandee(bienMapper.toEntity(bienDTO));
        bienRepository.deleteById(bienDTO.getId());
    }
}
