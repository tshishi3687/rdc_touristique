package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import com.example.rdc_touristique.security.MyUserPrincipal;
import com.example.rdc_touristique.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PersonneService implements CrudService<PersonneSimpleDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private Mapper<CreatPersonne, Personne> personneCreaMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private PassWordRepository passWordRepository;
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
    @Autowired
    private EngistrementEmail mail;
    @Autowired
    private StringText textMail;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void creat(PersonneSimpleDTO toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public void creatPersonne(CreatPersonne toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException, MessagingException {
        if ((personneReposytory.existsById(toCreat.getId()) && (!toCreat.getPassword().getMdp().equals(toCreat.getVerifMDP()))))
            throw new PersonneSimpleExisteExeption(toCreat.getId());

        String code = codeActivation();

        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Personne");
        actionDTO.setIdClasse(toCreat.getId());
        actionDTO.setAction("Création");
        actionDTO.setDescription("Création de compte au nom de  " + toCreat.getNom() + "-" + toCreat.getPrenom());

        Personne entity = personneCreaMapper.toEntity(toCreat);
        entity.setActive(false);
        entity.setCodeActivation(bCryptPasswordEncoder.encode(code));

        Personne idPer = personneReposytory.save(entity);

        System.out.println("mdp" + toCreat.getPassword().getMdp() + " et " + toCreat.getVerifMDP());

        PassWord passWord = new PassWord();
        passWord.setId(0);
        passWord.setMdp(bCryptPasswordEncoder.encode(toCreat.getPassword().getMdp()));
        passWord.setMode(true);
        passWord.setAppartienA(personneReposytory.getOne(idPer.getId()));
        passWordRepository.save(passWord);

        ContactUser contactUser = new ContactUser();
        contactUser.setId(0);
        contactUser.setEmail(toCreat.getContactUser().getEmail());
        contactUser.setTelephone(toCreat.getContactUser().getTelephone());
        contactUser.setAppartienA(personneReposytory.getOne(idPer.getId()));
        contactUserRepository.save(contactUser);

        mail.envoyer(toCreat.getContactUser().getEmail(), textMail.getSujetCrea(), textMail.creationMessageInscription(code,toCreat.getPrenom()));
        System.out.println("message envoyée");

        actionRepository.save(actionMapper.toEntity(actionDTO));

    }

    @Override
    public PersonneSimpleDTO readOne(Integer integer) throws PersonneSimpleFoundExeption {
        Personne entity = personneReposytory.findById(integer)
                .orElseThrow(()-> new PersonneSimpleFoundExeption(integer));

        return personneMapper.toDTO(entity);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, PersonneSimpleExisteExeption {
        Optional<Personne> user = personneReposytory.findByCodeActivation(username);
        user.orElseThrow(() -> new PersonneSimpleExisteExeption(user.get().getId()));
        return user.map(MyUserPrincipal::new).get();
    }

    @Transactional
    public void seloguer(MdpDTO mdp) throws NoSuchAlgorithmException {
        if (mdp == null)
            throw new NoSuchAlgorithmException();

        Optional<ContactUser> contactUser = contactUserRepository.findByEmail(mdp.getMail());

        if (contactUser.isPresent()){
            List<PassWord> passWord = passWordRepository.findAllByAppartienA(contactUser.get().getAppartienA());

            for (PassWord word : passWord) {
                if (bCryptPasswordEncoder.matches(mdp.getMdp(), word.getMdp())){
                    System.out.println("1er etape ok");
                    userDetailsServiceImpl.loadUserByUsername(contactUser.get().getEmail());
                }
            }
        }
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

    @Transactional
    public boolean isActive(String codeActive) throws NoSuchAlgorithmException {
        Optional<Personne> personne = personneReposytory.findByCodeActivation(bCryptPasswordEncoder.encode(codeActive));
        if (personne.isPresent()){
            personne.get().setActive(true);
            personne.get().setCodeActivation(bCryptPasswordEncoder.encode(personne.get().getId() + "_Nikel_je suis active. Mon compte est ok"));
            personneReposytory.save(personne.get());
        }
        return personne.isPresent();
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

    private String codeActivation()  {
        Random rand = new Random();

        String str1="", str2="";
        int str3;
        do{
            str3 = (int)(Math.random() * ((1000 - 1) + 1));

            for(int i = 0 ; i < 4 ; i++){
                char c = (char)(rand.nextInt(26) + 97);
                str1 += c;
            }

            for(int i = 0 ; i < 2 ; i++){
                char c = (char)(rand.nextInt(26) + 97);
                str2 += c;
            }
        }while(personneReposytory.findByCodeActivation(bCryptPasswordEncoder.encode(bCryptPasswordEncoder.encode(str1 + str3 + str2))).isPresent());


        return str1+str3+str2;
    }


}
