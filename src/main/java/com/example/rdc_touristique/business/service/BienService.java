package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.contratLocation.TextContrat;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BienService implements CrudService<BienVuDTO, Integer> {

    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private Mapper<BienVuDTO, Bien> bienVuMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private CoordonneeRepository coordorRepository;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneSimpleMapper;
    @Autowired
    private AladispositionRepository aladispositionRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private ContratMisEnLigneRepository contratRepository;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private EngistrementEmail mail;
    @Autowired
    private StringText textMail;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public List<BienVuDTO> selonLaPersonne(PersonneSimplifierDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException, BienFoundExeption {

        if (personne == null)
            throw new BienFoundExeption(personne.getId());

        return bienRepository.findAllByAppartientAndModeActiveFalse(personneMapper.toEntity(personne)).stream()
        .map(bienVuMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public void creat(BienVuDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public int creatKey(BienDTO toCreat) throws Exception {

        if (bienRepository.existsById(toCreat.getId()))
            throw new BienExisteExeption(toCreat.getId());


//        if (!nNuitRepository.existsById(toCreat.getDureeOnLine()))
//            throw new Exception();

        LocalDateTime today =  LocalDateTime.now();

        Personne personne = personneReposytory.getOne(personneMapper.toEntity(toCreat.getAppartient()).getId());
        if (personneReposytory.existsById(toCreat.getAppartient().getId()) && (personne.getRoll().getId() == 1 || personne.getRoll().getId() == 3)){

            Bien entity = bienMapper.toEntity(toCreat);
            entity.setModeActive(false);
            aladispositionRepository.save(entity.getAladisposition());
            coordorRepository.save(entity.getCoordonnee());
            return bienVuMapper.toDTO(bienRepository.save(entity)).getId();

        }

        return 0;
    }

    @Override
    public BienVuDTO readOne(Integer integer) throws  BienFoundExeption {
        Bien entity = bienRepository.findById(integer)
                .orElseThrow(()-> new BienFoundExeption(integer));


        return bienVuMapper.toDTO(entity);
    }

    @Override
    public List<BienVuDTO> readAll() {
        return bienRepository.findAllByModeActiveIs(true).stream()
                .map(bienVuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BienVuDTO toUpdate) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !bienRepository.existsById( toUpdate.getId() ))
            throw new BienFoundExeption(toUpdate.getId());

        bienRepository.save( bienVuMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    @Transactional
    public void activationBien(BienVuDTO bienDTO) throws BienFoundExeption, PersonneSimpleExisteExeption {
        if( !bienRepository.existsById(bienDTO.getId()))
            throw new BienFoundExeption(bienDTO.getId());

        // Pour faire passer un bien en mode active, je dois:
            // créé un contrat entre le propiétaire du bien et Mobembo (representé par personne Admin

        // 1. je reccupère le bien dans la bd.
        Bien bien = bienRepository.getOne(bienDTO.getId());
            // j'ai besoin de le transformer en personneSimple pour la partie 2
        PersonneSimpleDTO personneSimpleDTO = personneSimpleMapper.toDTO(personneReposytory.getOne(bien.getAppartient().getId()));

        // 2. verifi si le bien reccupéré n'est pas déja active et si la personne a déjà donnée ses info bancaire et ses coordonnée
        if (!bien.isModeActive() && personneService.infoBanAdreUser(personneSimpleDTO)){

            // Préparation du contrat liant le propiétaire du bien et Mobembo.cd
            // 3. je cherche les deux partie
                // 3.1 Preneur: c'est la le propiétaire du bien
            Personne preneur = bien.getAppartient();
                // 3.2 Bailleur: Le représenttant de Mobembo.cd (pour le test c'est l'Admin)
            Personne bailleur = personneReposytory.findByRoll_NomRoll("Admin");

            // 4 crée le text du contrat
            TextContrat textContrat = new TextContrat(
                    bailleur, // le Bailleur: personne ENTITY
                    preneur, // le Preneur: personne ENTITY
                    bienDTO, // bien consernée: bienDTO
                    LocalDate.now(), // jour-J
                    LocalDate.now().plusDays(bienDTO.getIdNNuit()) // jour-J + nombre de nuit
            );

            // 5. je cree l'entité contrat
            ContratMisEnLigne contrat = new ContratMisEnLigne();
                contrat.setId(0); // id 0 pour la création
                contrat.setDdDebut(LocalDate.now());
                contrat.setDdFin(LocalDate.now().plusDays(bienDTO.getIdNNuit()));
                contrat.setIdBien(bien);
                contrat.setBailleur(bailleur); // bailleur: personne ENTITY
                contrat.setPreneur(preneur); // preneur: personne ENTITY
                contrat.setEnCour(true);
                contrat.setEntre(textContrat.EntreBailler());
                contrat.setEntre2(textContrat.EntrePreneur());
                contrat.setObjet(textContrat.objet());
                contrat.setEtatLieu(textContrat.etatLieu());
                contrat.setLoyer(textContrat.loyerMobembo());
                contrat.setDuree(textContrat.dureeMobembo());
                contrat.setDardl(textContrat.dARDL());
;           contratRepository.save(contrat);

            // 8. je change le modeActive du bien en active
            bien.setModeActive(true);
            bienRepository.save(bien);
        }
    }

    @Transactional
    public void maildeconfirmationBienMisEnLigne(BienVuDTO bienVuDTO) throws BienExisteExeption, NoSuchAlgorithmException, MessagingException {
        if (!bienRepository.existsById(bienVuDTO.getId()))
            throw  new BienExisteExeption(bienVuDTO.getId());

        String code = codeActivation();

        Bien bien = bienRepository.getOne(bienVuDTO.getId());
        Personne personne = personneReposytory.getOne(bien.getAppartient().getId());

        mail.envoyer(personne.getContactUser().getEmail(), textMail.getSujetEnvoisConfMisEnLigne(), textMail.confirmationMisEnLigne(personne,bien, bienVuDTO.getIdNNuit(), code));
        personne.setCodeActivation(hasMdp(code));
        personneReposytory.save(personne);
    }

    private String codeActivation() throws NoSuchAlgorithmException {
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
        }while(personneReposytory.findByCodeActivation(hasMdp(str1 + str3 + str2)).isPresent());


        return str1+str3+str2;
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
        for (byte byteDatum : byteData) {
            String hex = Integer.toHexString(0xff & byteDatum);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Transactional
    public void deleteBien(BienVuDTO bienDTO) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !bienRepository.existsById(bienDTO.getId()))
            throw new BienFoundExeption(bienDTO.getId());

        List<Personne> listPersonne = personneReposytory.findAll();
        for (Personne personne : listPersonne) {
            if (personne.getLikedBien().contains(bienRepository.getOne(bienDTO.getId())))
                personne.getLikedBien().removeIf(bien -> bien.getId() == bien.getId());
        }
        imageRepository.deleteAllByBienid(bienVuMapper.toEntity(bienDTO));
//        reservationRepository.deleteAllByBienReservation(bienVuMapper.toEntity(bienDTO));
        bienRepository.deleteById(bienDTO.getId());
    }
}
