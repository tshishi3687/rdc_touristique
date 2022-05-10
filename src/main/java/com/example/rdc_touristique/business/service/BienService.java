package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.contrat.TextContrat;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
import com.example.rdc_touristique.security.config.constParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private ContratLocationRepository contratLocationRepository;

    @Transactional
    public List<BienVuDTO> selonLaPersonne() throws NoSuchAlgorithmException, InvalidKeySpecException, BienFoundExeption {

        return bienRepository.findAllByAppartientAndModeActiveFalse(JwtRequestFilter.maPersonne()).stream()
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


        if (JwtRequestFilter.maPersonne() != null){

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
        return bienRepository.findAllByModeActiveTrue().stream()
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

        if (bien.getAppartient().getId() != personneSimpleDTO.getId())
            throw new BienFoundExeption(bien.getId());

        // 2. verifi si le bien reccupéré n'est pas déja active et si la personne a déjà donnée ses info bancaire et ses coordonnée
        if (!bien.isModeActive() && personneService.infoBanAdreUser()){

            // Préparation du contrat liant le propiétaire du bien et Mobembo.cd
            // 3. je cherche les deux partie
                // 3.1 Preneur: c'est la le propiétaire du bien
            Personne preneur = JwtRequestFilter.maPersonne();
                // 3.2 Bailleur: Le représenttant de Mobembo.cd (pour le test c'est l'Admin)
            Personne bailleur = personneReposytory.findByRoleId_NomRole(constParam.roleA);

            // 4 crée le text du contrat
            TextContrat textContrat = new TextContrat(
                    bailleur, // le Bailleur: personne ENTITY
                    preneur, // le Preneur: personne ENTITY
                    bien, // bien consernée: bienDTO
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
    public boolean reservationBien(ReservationBienDTO reservationBienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<ContratLocation> allContrat = contratLocationRepository.findAll();

        for (ContratLocation contratLocation : allContrat) {
            if (
                    (!reservationBienDTO.getDdArrivee().isBefore(LocalDate.now()) || !reservationBienDTO.getDdArrivee().equals(LocalDate.now())) || // si la date d'arrivé < la date du jour
                            reservationBienDTO.getDdArrivee().isAfter(reservationBienDTO.getDdDepart()) || // si la date d'arrivé et avant la date de depart
                            !contratLocation.getDdDebut().equals(reservationBienDTO.getDdArrivee()) || //
                            (reservationBienDTO.getDdArrivee().isBefore(contratLocation.getDdFin()) && reservationBienDTO.getDdArrivee().isAfter(contratLocation.getDdDebut())) ||
                            (reservationBienDTO.getDdDepart().isAfter(contratLocation.getDdDebut()) && reservationBienDTO.getDdDepart().isBefore(contratLocation.getDdFin()))
            )
                return false;
        }

        Personne bailleur = personneReposytory.findByRoleId_NomRole(constParam.roleA);
        Personne preneur = personneReposytory.getOne(reservationBienDTO.getFaitPar().getId());
        Bien bien = bienRepository.getOne(reservationBienDTO.getBienConserne().getId());

        TextContrat textContrat = new TextContrat(
                bailleur, // le Bailleur: personne ENTITY
                preneur, // le Preneur: personne ENTITY
                bien, // bien consernée: bienDTO
                reservationBienDTO.getDdArrivee(), // jour-J
                reservationBienDTO.getDdDepart()
        );

        ContratLocation newContrat = new ContratLocation();
            newContrat.setId(0);
            newContrat.setDdDebut(reservationBienDTO.getDdArrivee());
            newContrat.setDdFin(reservationBienDTO.getDdDepart());
            newContrat.setIdBien(bienMapper.toEntity(reservationBienDTO.getBienConserne()));
            newContrat.setNPersonneSurLieu(reservationBienDTO.getNPersonneSurLieu());
            newContrat.setBailleur(bailleur);
            newContrat.setPreneur(preneur);
            newContrat.setEnCour(reservationBienDTO.getDdArrivee().equals(LocalDate.now()));
            newContrat.setEntre(textContrat.EntreBailler());
            newContrat.setEntre2(textContrat.EntrePreneur());
            newContrat.setObjet(textContrat.objet());
            newContrat.setEtatLieu(textContrat.etatLieu());
            newContrat.setLoyer(textContrat.loyerMobembo());
            newContrat.setDuree(textContrat.dureeMobembo());
            newContrat.setDardl(textContrat.dARDL());
        contratLocationRepository.save(newContrat);

        return true;

    }

    @Transactional
    public void maildeconfirmationBienMisEnLigne(BienVuDTO bienVuDTO) throws BienExisteExeption, NoSuchAlgorithmException, MessagingException, PersonneSimpleExisteExeption {
        if (!bienRepository.existsById(bienVuDTO.getId()))
            throw  new BienExisteExeption(bienVuDTO.getId());

        Bien bien = bienRepository.getOne(bienVuDTO.getId());


        String code = codeActivation();

        mail.envoyer(JwtRequestFilter.maPersonne().getContactUser().getEmail(), textMail.getSujetEnvoisConfMisEnLigne(), textMail.confirmationMisEnLigne(JwtRequestFilter.maPersonne(),bien, bienVuDTO.getIdNNuit(), code));
        JwtRequestFilter.maPersonne().setCodeActivation(hasMdp(code));
        personneReposytory.save(JwtRequestFilter.maPersonne());
    }

    @Transactional
    public long maildeconfirmationBienReserve(ReservationBienDTO reservationBienDTO) throws BienExisteExeption, NoSuchAlgorithmException, MessagingException, PersonneSimpleExisteExeption {
        if (!bienRepository.existsById(reservationBienDTO.getBienConserne().getId()))
            throw  new BienExisteExeption(reservationBienDTO.getBienConserne().getId());



        long nJour = ChronoUnit.DAYS.between(reservationBienDTO.getDdArrivee(), reservationBienDTO.getDdDepart());

        String code = codeActivation();

        Bien bien = bienRepository.getOne(reservationBienDTO.getBienConserne().getId());

        mail.envoyer(JwtRequestFilter.maPersonne().getContactUser().getEmail(), textMail.getSujetConfirmationReservation(), textMail.confirmationReservation(JwtRequestFilter.maPersonne(),bien, nJour, code));
        JwtRequestFilter.maPersonne().setCodeActivation(hasMdp(code));
        personneReposytory.save(JwtRequestFilter.maPersonne());

        return nJour;
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

        Personne maPersonne = JwtRequestFilter.maPersonne();
        Bien monBien = bienRepository.getOne(bienDTO.getId());

        if (maPersonne.getId() == monBien.getAppartient().getId()){

            List<Personne> listPersonne = personneReposytory.findAll();
            for (Personne personne : listPersonne) {
                if (personne.getLikedBien().contains(monBien))
                    personne.getLikedBien().removeIf(bien -> bien.getId() == bien.getId());
            }
            imageRepository.deleteAllByBienid(monBien);
//        reservationRepository.deleteAllByBienReservation(bienVuMapper.toEntity(bienDTO));
            bienRepository.deleteById(monBien.getId());
            coordorRepository.deleteById(monBien.getCoordonnee().getId());
        }
    }
}
