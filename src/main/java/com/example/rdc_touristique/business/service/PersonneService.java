package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
import com.example.rdc_touristique.security.config.JwtResponse;
import com.example.rdc_touristique.security.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private Mapper<CreatPersonneDTO, Personne> personneCreaMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private PassWordRepository passWordRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private ContactUserRepository contactUserRepository;
    @Autowired
    private EngistrementEmail mail;
    @Autowired
    private StringText textMail;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private ContratMisEnLigneRepository contratMisEnLigneRepository;


    @Override
    public void creat(PersonneSimpleDTO toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public void creatPersonne(CreatPersonneDTO toCreat) throws PersonneSimpleExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException, MessagingException {
        if ((personneReposytory.existsById(toCreat.getId()) && (!toCreat.getPassword().getMdp().equals(toCreat.getVerifMDP()))))
            throw new PersonneSimpleExisteExeption(toCreat.getId());

        String code = codeActivation();

        Personne entity = personneCreaMapper.toEntity(toCreat);
        entity.setActive(false);
        entity.setCodeActivation(hasMdp(code));

        Personne idPer = personneReposytory.save(entity);

        PassWord passWord = new PassWord();
        passWord.setId(0);
        passWord.setMdp(bCryptPasswordEncoder.encode(toCreat.getPassword().getMdp()));
        passWord.setMode(true);
        passWord.setAppartienA(personneReposytory.getOne(idPer.getId()));
        passWordRepository.save(passWord);

        ContactPersonne contactUser = new ContactPersonne();
        contactUser.setId(0);
        contactUser.setEmail(toCreat.getContactUser().getEmail());
        contactUser.setTelephone(toCreat.getContactUser().getTelephone());
        contactUser.setAppartienA(personneReposytory.getOne(idPer.getId()));
        contactUserRepository.save(contactUser);

        mail.envoyer(toCreat.getContactUser().getEmail(), textMail.getSujetCrea(), textMail.creationMessageInscription(code,toCreat.getPrenom()));

     }

    @Transactional
    public ValidatorDTO validateElement(){
        ValidatorDTO validator = new ValidatorDTO();
        if (JwtRequestFilter.maPersonne().getRoleId().getId() != 1) {
            validator.setIbau(JwtRequestFilter.maPersonne().getInfoBancaires() != null && JwtRequestFilter.maPersonne().getAdresse() != null);
            validator.setReservation(JwtRequestFilter.maPersonne().getContratsLocationsPreneur().isEmpty());
            validator.setMel((JwtRequestFilter.maPersonne().getContratsPreneur().isEmpty()));
            validator.setBiensNonMel(bienRepository.findAllByAppartientAndModeActiveFalseOrderByIdDesc(JwtRequestFilter.maPersonne()).isEmpty());
        }else if (JwtRequestFilter.maPersonne().getRoleId().getId() == 1){
            validator.setIbau(JwtRequestFilter.maPersonne().getInfoBancaires() != null && JwtRequestFilter.maPersonne().getAdresse() != null);
            validator.setReservation(JwtRequestFilter.maPersonne().getContratsLocationBailleur().isEmpty());
            validator.setMel((JwtRequestFilter.maPersonne().getContratsBailleur().isEmpty()));
            validator.setBiensNonMel(bienRepository.findAllByAppartientAndModeActiveFalseOrderByIdDesc(JwtRequestFilter.maPersonne()).isEmpty());
        }
        return validator;
    }

    @Override
    public PersonneSimpleDTO readOne(Integer integer) throws PersonneSimpleFoundExeption {
        Personne entity = personneReposytory.findById(integer)
                .orElseThrow(()-> new PersonneSimpleFoundExeption(integer));

        return personneMapper.toDTO(entity);
    }

    @Transactional
    public ResponseEntity<?> seloguer(MdpDTO mdp) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mdp.getMail(), mdp.getMdp()));
        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(mdp.getMail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }

//    @Transactional
//    public PersonneSimpleDTO seloguerSansJWT(MdpDTO mdp) throws NoSuchAlgorithmException {
//        if (mdp == null)
//            throw new NoSuchAlgorithmException();
//
//        Optional<ContactPersonne> contactUser = contactUserRepository.findByEmail(mdp.getMail());
//
//        if (contactUser.isPresent()){
//            PassWord passWord = passWordRepository.findByAppartienA(contactUser.get().getAppartienA());
//
//            if (bCryptPasswordEncoder.matches(mdp.getMdp(), passWord.getMdp())){
//                return personneMapper.toDTO(contactUser.get().getAppartienA());
//            }
//
//        }
//        return null;
//    }

    @Transactional
    public PersonneVuDTO infoPersonne(){
        return new PersonneVuDTO(
                JwtRequestFilter.maPersonne().getNom(),
                JwtRequestFilter.maPersonne().getPrenom(),
                JwtRequestFilter.maPersonne().getRoleId().getNomRole(),
                JwtRequestFilter.maPersonne().isActive()
        );
    }

    @Transactional
    public void likes(LikeBienDTO likes) throws Exception {
        if( likes == null)
            throw new Exception();
        likes.getPersonneSimplifierDTO().setId(JwtRequestFilter.maPersonne().getId());

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
        Optional<ContactPersonne> contact = contactUserRepository.findByEmail(mdp.getMail());
        return contact.isPresent();
    }

    @Transactional
    public boolean isActive(String codeActive) throws NoSuchAlgorithmException {
        Optional<Personne> personne = personneReposytory.findByCodeActivation(hasMdp(codeActive));
        if (personne.isPresent()){
            personne.get().setActive(true);
            personne.get().setCodeActivation(bCryptPasswordEncoder.encode( LocalDateTime.now()+ "_" + personne.get().getId() + "_Nikel_je suis active. Mon compte est ok" +  "_" + LocalDate.now()));
            personneReposytory.save(personne.get());
        }
        return personne.isPresent();
    }

    @Transactional
    public boolean modifMDP(ModifPassDTO pass) throws  NoSuchAlgorithmException {
        Optional<Personne> personne = personneReposytory.findByCodeActivation(hasMdp(pass.getCodeActive()));
        if (personne.isPresent() && pass.getNewPass().equals(pass.getVerifPass())){
            personne.get().getMdp().setMdp(bCryptPasswordEncoder.encode(pass.getNewPass()));
            personne.get().setCodeActivation(bCryptPasswordEncoder.encode( LocalDateTime.now()+ "_" + personne.get().getId() + "_Nikel_je suis active. Mon compte est ok" +  "_" + LocalDate.now()));
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

    @Transactional
    public boolean modifMDP(CreatPersonneDTO personne) throws NoSuchAlgorithmException, MessagingException {

        Optional<ContactPersonne> entity = contactUserRepository.findByEmail(personne.getContactUser().getEmail());
        if (entity.isPresent() &&
                entity.get().getAppartienA().getNom().equals(personne.getNom()) &&
                entity.get().getAppartienA().getPrenom().equals(personne.getPrenom()) &&
                entity.get().getAppartienA().getContactUser().getEmail().equals(personne.getContactUser().getEmail()) &&
                entity.get().getAppartienA().getDdn().equals(personne.getDdn())
        ){

            String code = codeActivation();

            mail.envoyer(
                    entity.get().getAppartienA().getContactUser().getEmail(),
                    textMail.getSujetmodifMDP(),
                    textMail.demandeModifMDP(entity.get().getAppartienA(),code));

            entity.get().getAppartienA().setCodeActivation(hasMdp(code));

            return true;

        }

        return false;
    }

    private String codeActivation() throws NoSuchAlgorithmException {
        Random rand = new Random();

        StringBuilder str1= new StringBuilder();
        StringBuilder str2= new StringBuilder();
        int str3;
        do{
            str3 = (int)(Math.random() * ((1000 - 1) + 1));

            for(int i = 0 ; i < 4 ; i++){
                char c = (char)(rand.nextInt(26) + 97);
                str1.append(c);
            }

            for(int i = 0 ; i < 2 ; i++){
                char c = (char)(rand.nextInt(26) + 97);
                str2.append(c);
            }
        }while(personneReposytory.findByCodeActivation(hasMdp(str1.toString() + str3 + str2)).isPresent());


        return str1.toString() +str3+str2;
    }

    private String hasMdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (byte datum : byteData) {
            sb.append(Integer.toString((datum & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (byte byteDatum : byteData) {
            String hex = Integer.toHexString(0xff & byteDatum);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Transactional
    public void alertStopContrat(int dtoID) throws ContratMisEnLigneExisteExeption, NoSuchAlgorithmException, MessagingException {
        Optional<ContratMisEnLigne> entity = contratMisEnLigneRepository.findById(dtoID);

        if (entity.isEmpty())
            throw new ContratMisEnLigneExisteExeption(dtoID);

        String code =  codeActivation();
        entity.get().getPreneur().setCodeActivation(code);
        personneReposytory.save(entity.get().getPreneur());
        mail.envoyer(entity.get().getPreneur().getContactUser().getEmail(),textMail.getSujetStopContrat(),textMail.alertStopContrat(entity.get(),code));
    }

    @Transactional
    public long nbCompte(){
        return personneReposytory.count();
    }
}
