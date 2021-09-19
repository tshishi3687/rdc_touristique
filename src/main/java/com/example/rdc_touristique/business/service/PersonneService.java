package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
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
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private Mapper<PassWordDTO, PassWord> passWordMapper;
    @Autowired
    private PassWordRepository passWordRepository;
    @Autowired
    private RollRepository rollRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private Mapper<ActionDTO, Action> actionMapper;
    @Autowired
    private final ActionDTO actionDTO = new ActionDTO();
    @Autowired
    private ContactUserRepository contactUserRepository;

    @Override
    public void creat(PersonneSimpleDTO toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public void creatPersonne(CreatPersonne toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if (personneReposytory.existsById(toCreat.getId()))
            throw new PersonneSimpleExisteExeption(toCreat.getId());

        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Personne");
        actionDTO.setIdClasse(toCreat.getId());
        actionDTO.setAction("Création");
        actionDTO.setDescription("Création de compte au nom de  " + toCreat.getNom() + "-" + toCreat.getPrenom());

        Personne personne = new Personne();
        personne.setId(0);
        personne.setNom(toCreat.getNom());
        personne.setPrenom(toCreat.getPrenom());
        personne.setDdn(toCreat.getDdn());
        personne.setRoll(rollRepository.findByNomRoll(toCreat.getRoll().getNomRoll()));
        personne.setDdj(LocalDateTime.now());

        int idPer = personneReposytory.save(personne).getId();

        PassWord passWord = new PassWord();
        passWord.setId(0);
        passWord.setMdp(hasMdp(toCreat.getMdp().getMdp()));
        passWord.setMode(true);
        passWord.setAppartienA(personneReposytory.getOne(idPer));
        passWordRepository.save(passWord);

        ContactUser contactUser = new ContactUser();
        contactUser.setId(0);
        contactUser.setEmail(toCreat.getContactUser().getEmail());
        contactUser.setTelephone(toCreat.getContactUser().getTelephone());
        contactUser.setAppartienA(personneReposytory.getOne(idPer));
        contactUserRepository.save(contactUser);


        actionRepository.save(actionMapper.toEntity(actionDTO));

        Optional<Personne> tesPer = personneReposytory.findById(13);

        System.out.println(tesPer);

        //lire
//        for(Bien bien: tesPer.get().getLikedBien()){
//            System.out.println(bien);
//        }

        //suprimer
//        tesPer.get().getLikedBien().removeIf(bien -> bien.getId() == 1);


//        //ajouter
//        Bien bien = new Bien();
//        bien.setId(1);
//        tesPer.get().getLikedBien().add(bien);
    }

    @Override
    public PersonneSimpleDTO readOne(Integer integer) throws PersonneSimpleFoundExeption {
        Personne entity = personneReposytory.findById(integer)
                .orElseThrow(()-> new PersonneSimpleFoundExeption(integer));

        return personneMapper.toDTO(entity);
    }

    @Transactional
    public PersonneSimpleDTO seloguer(MdpDTO mdp) throws NoSuchAlgorithmException {
        Optional<ContactUser> contactUser = contactUserRepository.findByEmail(mdp.getMail());
        List<PassWord> passWord = passWordRepository.findAllByAppartienA(contactUser.get().getAppartienA());


        for (PassWord word : passWord) {
            if (word.isMode() && word.getMdp().equals(hasMdp(mdp.getMdp())))
                return personneMapper.toDTO(contactUser.get().getAppartienA());
        }
        return null;
    }

    @Transactional
    public void likes(LikeBien likes) throws Exception {
        if( likes == null)
            throw new Exception();

        Optional<Personne> tesPer = personneReposytory.findById(likes.getPersonneSimplifierDTO().getId());

        if ( tesPer.get().getLikedBien().contains(bienRepository.getOne(likes.getBienDTO().getId())))
            tesPer.get().getLikedBien().removeIf(bien -> bien.getId() == likes.getBienDTO().getId());
        else
            tesPer.get().getLikedBien().add(bienRepository.getOne(likes.getBienDTO().getId()));

    }

    @Transactional
    public void myFavory(FavoryDTO favory) throws Exception {
        if( favory == null)
            throw new Exception();

        Optional<Personne> tesPer = personneReposytory.findById(favory.getPersonneSimplifierDTO().getId());

        if ( tesPer.get().getLikedBien().contains(bienRepository.getOne(favory.getBienDTO().getId())))
            tesPer.get().getLikedBien().removeIf(bien -> bien.getId() == favory.getBienDTO().getId());
        else
            tesPer.get().getLikedBien().add(bienRepository.getOne(favory.getBienDTO().getId()));

    }

    @Transactional
    public boolean selonEmail(MdpDTO mdp){
        Optional<ContactUser> contact = contactUserRepository.findByEmail(mdp.getMail());
        return contact.isPresent();
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
