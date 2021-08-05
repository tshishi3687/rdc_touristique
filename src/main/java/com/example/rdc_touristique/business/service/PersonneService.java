package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ActionDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Action;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ActionRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonneService implements CrudService<PersonneSimpleDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private Mapper<ActionDTO, Action> actionMapper;
    @Autowired
    private final ActionDTO actionDTO = new ActionDTO();

    @Override
    public void creat(PersonneSimpleDTO toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if (personneReposytory.existsById(toCreat.getId()))
            throw new PersonneSimpleExisteExeption(toCreat.getId());

        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Personne");
        actionDTO.setIdClasse(toCreat.getId());
        actionDTO.setAction("Création");
        actionDTO.setDescription("Création de compte au nom de  " + toCreat.getNom() + "-" + toCreat.getPrenom());

        personneReposytory.save(personneMapper.toEntity(toCreat));
        actionRepository.save(actionMapper.toEntity(actionDTO));
    }

    @Override
    public PersonneSimpleDTO readOne(Integer integer) throws PersonneSimpleFoundExeption {
        Personne entity = personneReposytory.findById(integer)
                .orElseThrow(()-> new PersonneSimpleFoundExeption(integer));

        return personneMapper.toDTO(entity);
    }

    @Transactional
    public PersonneSimpleDTO seloguer(PersonneSimpleDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Optional<Personne> user = personneReposytory.findByEmail(personne.getEmail());
        if(user.isPresent()){
            if((user.get().getMdp()).equals(hasMdp(personne.getMdp()))) {

                actionDTO.setId(0);
                actionDTO.setDate(LocalDateTime.now());
                actionDTO.setClassName("Personne");
                actionDTO.setIdClasse(user.get().getId());
                actionDTO.setAction("Connexion");
                actionDTO.setDescription(user.get().getNom() + "-" + user.get().getPrenom() + " vient de se connecter");
                actionRepository.save(actionMapper.toEntity(actionDTO));
                return personneMapper.toDTO(user.get());
            }
        }
        return null;
    }

    @Transactional
    public boolean selonEmail(PersonneSimpleDTO personne){
        Optional<Personne> user = personneReposytory.findByEmail(personne.getEmail());
        if(user.isPresent())
            return true;

        return false;
    }

    @Override
    public List<PersonneSimpleDTO> readAll() {
        return personneReposytory.findAll().stream()
                .map(personneMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(PersonneSimpleDTO toUpdate) throws PersonneSimpleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !personneReposytory.existsById( toUpdate.getId() ))
            throw new PersonneSimpleFoundExeption(toUpdate.getId());

        personneReposytory.save( personneMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws PersonneSimpleFoundExeption {
        if( !personneReposytory.existsById(toDelete))
            throw new PersonneSimpleFoundExeption(toDelete);

        personneReposytory.deleteById(toDelete);
    }

    private String hasMdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
