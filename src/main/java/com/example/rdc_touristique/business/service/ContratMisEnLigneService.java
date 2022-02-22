package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ContratMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ContratMisEnLigneRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratMisEnLigneService implements CrudService<ContratMisEnLigneDTO, Integer>{

    @Autowired
    private ContratMisEnLigneRepository contratMisEnLigneRepository;
    @Autowired
    private Mapper<ContratMisEnLigneDTO, ContratMisEnLigne> contratMisEnLigneMapper;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneSimpleDTOMapper;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private EngistrementEmail mail;
    @Autowired
    private StringText textMail;

    @Override
    public void creat(ContratMisEnLigneDTO toDTO) throws ContratMisEnLigneExisteExeption{

    }

    @Override
    public ContratMisEnLigneDTO readOne(Integer integer) throws ContratMisEnLigneFoundExeption {
        if (readAll().isEmpty())
            throw new ContratMisEnLigneFoundExeption(integer);
        return contratMisEnLigneMapper.toDTO(contratMisEnLigneRepository.getOne(integer));
    }

    @Transactional
    public List<ContratMisEnLigneDTO> selonBien(PersonneSimpleDTO personneSimpleDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, PersonneSimpleExisteExeption {
        if (!personneReposytory.existsById(personneSimpleDTO.getId()))
            throw new PersonneSimpleExisteExeption(personneSimpleDTO.getId());

        Personne personne = personneSimpleDTOMapper.toEntity(personneSimpleDTO);
        if (personne.getRoleId().getNomRole().equals("Admin"))
            return contratMisEnLigneRepository.findAllByBailleur(personne).stream()
                .map(contratMisEnLigneMapper::toDTO)
                .collect(Collectors.toList());

        return null;
    }


    @Override
    public List<ContratMisEnLigneDTO> readAll() {
        return null;
    }

    @Override
    public void update(ContratMisEnLigneDTO toUpdate) throws ContratMisEnLigneExisteExeption, MessagingException {
        if (!contratMisEnLigneRepository.existsById(toUpdate.getId()))
            throw new ContratMisEnLigneExisteExeption(toUpdate.getId());

        ContratMisEnLigne contact = contratMisEnLigneRepository.getOne(toUpdate.getId());
            contact.setEnCour(false);
            contact.setDdFin(LocalDate.now());
            contact.getIdBien().setModeActive(false);
            contratMisEnLigneRepository.save(contact);

        mail.envoyer(contact.getPreneur().getContactUser().getEmail(),textMail.getSujetBienNonConforme(), textMail.bienNonConforme(contact));
    }

    @Override
    public void delete(Integer toDelete)  {

    }
}
