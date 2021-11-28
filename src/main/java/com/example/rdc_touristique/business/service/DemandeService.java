package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.DemandeDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Demande;
import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
import com.example.rdc_touristique.data_access.repository.DemandeRepository;
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
public class DemandeService implements CrudService<DemandeDTO, Integer> {

    @Autowired
    private Mapper<DemandeDTO, Demande> demandeMapper;
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneSimplifierMapper;
    @Autowired
    private EngistrementEmail mail;
    @Autowired
    private StringText textMail;
    @Autowired
    private ContactUserRepository contactUserRepository;

    @Transactional
    public List<DemandeDTO> selonLaPer(PersonneSimpleDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {

        return demandeRepository.findAllByFaitParOrderByIdDesc(personneMapper.toEntity(personne)).stream()
                .map(demandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DemandeDTO> damandeFaite(PersonneSimpleDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return demandeRepository.findAllByBienDemandee_AppartientOrderByEtatDemandeId(personneMapper.toEntity(personne))
                .stream()
                .map(demandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void creat(DemandeDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException, MessagingException {
        if (demandeRepository.existsById(toCreat.getId())) {
            throw new ReservationExisteExeption(toCreat.getId());
        }
        int numDemande = demandeRepository.save(demandeMapper.toEntity(toCreat)).getId();

        List<ContactUser> listContactsFaitPar = contactUserRepository.findAllByAppartienA(personneMapper.toEntity(toCreat.getFaitPar()));
        List<ContactUser> listContactA = contactUserRepository.findAllByAppartienA(personneSimplifierMapper.toEntity(toCreat.getBienDemandee().getAppartient()));
        for (ContactUser contactUser : listContactsFaitPar) {
            mail.envoyer(contactUser.getEmail(),
                    textMail.getSujetEnvoisDemande(),
                    textMail.confimationEnvoisDemande(toCreat.getBienDemandee().getCoordonnee().getVille().getProvince().getNomprovince(),
                            toCreat.getBienDemandee().getCoordonnee().getVille().getNomVille(),
                            toCreat.getBienDemandee().getType_bien().getNom(),
                            toCreat.getBienDemandee().getCoordonnee().getNum() + " " + toCreat.getBienDemandee().getCoordonnee().getRue(),
                            toCreat.getFaitPar().getPrenom(),
                            numDemande,
                            "En Attente",
                            toCreat.getDda(),
                            toCreat.getDdd()));
        }

        for (ContactUser contactUser : listContactA) {
            mail.envoyer(contactUser.getEmail(),
                    textMail.getSujetDemanderecu(),
                    textMail.notificationDEmande(toCreat.getBienDemandee().getCoordonnee().getVille().getProvince().getNomprovince(),
                            toCreat.getBienDemandee().getCoordonnee().getVille().getNomVille(),
                            toCreat.getBienDemandee().getType_bien().getNom(),
                            toCreat.getBienDemandee().getCoordonnee().getNum() + " " + toCreat.getBienDemandee().getCoordonnee().getRue(),
                            toCreat.getBienDemandee().getAppartient().getPrenom(),
                            numDemande,
                            "En Attente",
                            toCreat.getDda(),
                            toCreat.getDdd(),
                            toCreat.getFaitPar().getNom() + " " + toCreat.getFaitPar().getPrenom()));
        }

    }

    @Override
    public DemandeDTO readOne(Integer integer) throws ReservationFoundExeption {
        Demande entity = demandeRepository.findById(integer)
                .orElseThrow(()-> new ReservationFoundExeption(integer));

        return demandeMapper.toDTO(entity);
    }

    @Override
    public List<DemandeDTO> readAll() {
        return demandeRepository.findAll().stream()
                .map(demandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(DemandeDTO toUpdate) throws ReservationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !demandeRepository.existsById( toUpdate.getId() ))
            throw new ReservationFoundExeption(toUpdate.getId());

        demandeRepository.save( demandeMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws ReservationFoundExeption {
        if( !demandeRepository.existsById(toDelete))
            throw new ReservationFoundExeption(toDelete);

        demandeRepository.deleteById(toDelete);
    }

//    @Transactional
//    public void deleteDemande(DemandeDTO demandeDTO) throws ReservationFoundExeption {
//        if( !demandeRepository.existsById(demandeDTO.getId()))
//            throw new ReservationFoundExeption(demandeDTO.getId());
//    }
}
