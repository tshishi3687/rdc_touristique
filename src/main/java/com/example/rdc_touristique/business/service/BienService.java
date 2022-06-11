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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
    @Autowired
    private Mapper<DetailsDTO, Details> detailsMapper;
    @Autowired
    private DetailsRepository detailsRepository;
    @Autowired
    private Mapper<ContratLocationDTO, ContratLocation> contratLocationMapper;
    @Autowired
    private Mapper<BienVuSimplifierDTO, Bien> bienVuSimplierMapper;


    @Transactional
    public List<BienVuSimplifierDTO> selonLaPersonne() {

        return bienRepository.findAllByAppartientAndModeActiveFalse(JwtRequestFilter.maPersonne()).stream()
        .map(bienVuSimplierMapper::toDTO)
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
    public List<BienVuDTO> readAll() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }

    @Transactional
    public TryListAllBiens tousBiens(TryListAllBiens tryListAllBiens){
        Pageable pageable = PageRequest.of(tryListAllBiens.getPage() - 1, 6);

        TryListAllBiens newTryListAllBiens = new TryListAllBiens();
        newTryListAllBiens.setList(bienRepository.findByType_NomContainingAndCoordonnee_Ville_NomVilleContainingAndCoordonnee_Ville_Province_NomprovinceContainingAndModeActiveTrueOrderByIdDesc(
                        tryListAllBiens.getTypeId(), tryListAllBiens.getVilleId(), tryListAllBiens.getProvinceId(), pageable
                )
                .stream()
                .map(bienVuSimplierMapper::toDTO)
                .collect(Collectors.toList()));
        newTryListAllBiens.setNbPage(bienRepository.countByType_NomContainingAndCoordonnee_Ville_NomVilleContainingAndCoordonnee_Ville_Province_NomprovinceContainingAndModeActiveTrue(tryListAllBiens.getTypeId(), tryListAllBiens.getVilleId(), tryListAllBiens.getProvinceId()));
        return newTryListAllBiens;
    }

    @Transactional
    public BienVuDTO infoBien(int idBien){
        return bienVuMapper.toDTO(bienRepository.getOne(idBien));
    }

    @Override
    public void update(BienVuDTO toUpdate) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!bienRepository.existsById( toUpdate.getId() ))
            throw new BienFoundExeption(toUpdate.getId());
        bienRepository.save( bienVuMapper.toEntity(toUpdate));
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
        for (ContratMisEnLigne cmel: contratRepository.findAllByIdBien(bien)){
            if (cmel.isEnCour()){
                cmel.setEnCour(false);
                contratRepository.save(cmel);
            }
        }
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
                contrat.getIdBien().setModeActive(true);
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
          contratRepository.save(contrat);

            bienRepository.save(bien);
        }
    }

    @Transactional
    public void annulationCMEL(PayPalDTO payPalDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!contratRepository.existsById(payPalDTO.getId()))
            throw  new RuntimeException();

        ContratMisEnLigne cmel = contratRepository.getOne(payPalDTO.getId());

        if (
                cmel.getPreneur().getId() == JwtRequestFilter.maPersonne().getId() ||
                        JwtRequestFilter.maPersonne().getRoleId().getId() == 1
        ){

            cmel.setEnCour(false);
            cmel.setDdFin(LocalDate.now());
            cmel.getIdBien().setModeActive(false);
            cmel.getDetails().add(detailsRepository.save(detailsMapper.toEntity(payPalDTO.getDetails())));
            contratRepository.save(cmel);
        }else throw new RuntimeException();
    }

    @Transactional
    public boolean isDisponible(ReservationBienDTO DTO){
        List<ContratLocation> allContrat = contratLocationRepository.findAllByIdBien(bienRepository.getOne(DTO.getBienConserne().getId()));

        for (ContratLocation contratLocation : allContrat) {
            if (DTO.getDdArrivee().isAfter(DTO.getDdDepart()) || DTO.getDdArrivee().isBefore(contratLocation.getDdFin()))
                return false;
        }
        return true;
    }

    @Transactional
    public int reservationBien(PayPalRDTO paypalDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, MessagingException {

        List<ContratLocation> allContrat = contratLocationRepository.findAllByIdBien(bienRepository.getOne(paypalDTO.getReservationBienDTO().getBienConserne().getId()));

        for (ContratLocation contratLocation : allContrat) {
            if (
                    paypalDTO.getReservationBienDTO().getDdArrivee().isAfter(paypalDTO.getReservationBienDTO().getDdDepart()) ||
                            paypalDTO.getReservationBienDTO().getDdArrivee().isBefore(contratLocation.getDdFin())
            )
                throw new RuntimeException();
        }

        Personne bailleur = personneReposytory.findByRoleId_NomRole(constParam.roleA);
        Personne preneur = JwtRequestFilter.maPersonne();
        Bien bien = bienRepository.getOne(paypalDTO.getReservationBienDTO().getBienConserne().getId());

        TextContrat textContrat = new TextContrat(
                bailleur, // le Bailleur: personne ENTITY
                preneur, // le Preneur: personne ENTITY
                bien, // bien consernée: bienDTO
                paypalDTO.getReservationBienDTO().getDdArrivee(), // jour-J
                paypalDTO.getReservationBienDTO().getDdDepart()
        );

        ContratLocation newContrat = new ContratLocation();
            newContrat.setId(0);
            newContrat.setDdDebut(paypalDTO.getReservationBienDTO().getDdArrivee());
            newContrat.setDdFin(paypalDTO.getReservationBienDTO().getDdDepart());
            newContrat.setIdBien(bienMapper.toEntity(paypalDTO.getReservationBienDTO().getBienConserne()));
            newContrat.setNPersonneSurLieu(paypalDTO.getReservationBienDTO().getNPersonneSurLieu());
            newContrat.setBailleur(bailleur);
            newContrat.setPreneur(preneur);
            newContrat.setEnCour(true);
            newContrat.setEntre(textContrat.EntreBailler());
            newContrat.setEntre2(textContrat.EntrePreneur());
            newContrat.setObjet(textContrat.objet());
            newContrat.setEtatLieu(textContrat.etatLieu());
            newContrat.setLoyer(textContrat.loyer());
            newContrat.setDuree(textContrat.dureeClient());
            newContrat.setDardl(textContrat.dARDL());

        int idcontrat = contratLocationRepository.save(newContrat).getId();


        mail.envoyer(JwtRequestFilter.maPersonne().getContactUser().getEmail(), textMail.getSujetConfirmationReservation(),textMail.confimationEnvoisDemande(
                paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getVille().getProvince().getNomprovince(),
                paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getVille().getNomVille(),
                paypalDTO.getReservationBienDTO().getBienConserne().getType_bien().getNom(),
                paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getNum()+ " " +paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getRue(),
                JwtRequestFilter.maPersonne().getNom(),
                paypalDTO.getPrix(),
                paypalDTO.getReservationBienDTO().getDdArrivee(),
                paypalDTO.getReservationBienDTO().getDdDepart(),
                paypalDTO.getDetails()) );

        mail.envoyer(bien.getAppartient().getContactUser().getEmail(), textMail.getSujetEnvoisDemande() ,textMail.notificationReservation(
                paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getVille().getProvince().getNomprovince(),
                paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getVille().getNomVille(),
                paypalDTO.getReservationBienDTO().getBienConserne().getType_bien().getNom(),
                paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getNum()+ " " +paypalDTO.getReservationBienDTO().getBienConserne().getCoordonnee().getRue(),
                JwtRequestFilter.maPersonne().getNom(),
                paypalDTO.getReservationBienDTO().getDdArrivee(),
                paypalDTO.getReservationBienDTO().getDdDepart()
        ));
        return idcontrat;
    }

    @Transactional
    public void ajoutDetails(DetailesDTO detailesDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!contratLocationRepository.existsById(detailesDTO.getId()))
            throw new RuntimeException();
        ContratLocation contratLocation = contratLocationRepository.getOne(detailesDTO.getId());
        Details details = detailsRepository.save(detailsMapper.toEntity(detailesDTO.getDetails()));
        detailesDTO.getDetails().setId(detailesDTO.getDetails().getId());
        contratLocation.getDetails().add(details);
        contratLocationRepository.save(contratLocation);
    }

    @Transactional
    public void maildeconfirmationBienMisEnLigne(BienVuDTO bienVuDTO) throws BienExisteExeption, NoSuchAlgorithmException, MessagingException{
        if (!bienRepository.existsById(bienVuDTO.getId()))
            throw  new BienExisteExeption(bienVuDTO.getId());

        Bien bien = bienRepository.getOne(bienVuDTO.getId());


        String code = codeActivation();

        mail.envoyer(JwtRequestFilter.maPersonne().getContactUser().getEmail(), textMail.getSujetEnvoisConfMisEnLigne(), textMail.confirmationMisEnLigne(JwtRequestFilter.maPersonne(),bien, bienVuDTO.getIdNNuit(), code));
        JwtRequestFilter.maPersonne().setCodeActivation(hasMdp(code));
        personneReposytory.save(JwtRequestFilter.maPersonne());
    }

    @Transactional
    public long maildeconfirmationBienReserve(ReservationBienDTO reservationBienDTO) throws BienExisteExeption, NoSuchAlgorithmException, MessagingException{
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
        StringBuilder hexString = new StringBuilder();
        for (byte byteDatum : byteData) {
            String hex = Integer.toHexString(0xff & byteDatum);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Transactional
    public void deleteBien(BienVuDTO bienDTO) throws Exception {
        if( !bienRepository.existsById(bienDTO.getId()))
            throw new BienFoundExeption(bienDTO.getId());

        Personne maPersonne = JwtRequestFilter.maPersonne();
        Bien monBien = bienRepository.getOne(bienDTO.getId());

        List<ContratLocation> contratLocations = contratLocationRepository.findAllByIdBien(monBien);
        List<ContratMisEnLigne> contratMisEnLigneList = contratRepository.findAllByIdBien(monBien);

        // si le bien n'a pas de contrat lié on l'efface directement
        if (contratLocations.isEmpty() && contratMisEnLigneList.isEmpty()){
            if (maPersonne.getId() == monBien.getAppartient().getId()){
                List<Personne> listPersonne = personneReposytory.findAll();
                for (Personne personne : listPersonne) {
                    if (personne.getLikedBien().contains(monBien))
                        personne.getLikedBien().removeIf(bien -> bien.getId() == bien.getId());
                }

                imageRepository.deleteAllByBienid(monBien);
                bienRepository.deleteById(monBien.getId());
                coordorRepository.deleteById(monBien.getCoordonnee().getId());
            }
            // sinon je je change le propriétaire diu bien
        }else {
            if (!JwtRequestFilter.maPersonne().getContactUser().getEmail().equals("tshishi@outlook.be") && monBien.getAppartient().getId() == maPersonne.getId()) {
                monBien.setAppartient(personneReposytory.findByContactUser_Email("tshishi@outlook.be"));
                for (ContratLocation contratLocation : contratLocations) {
                    contratLocation.setIdBien(bienRepository.save(monBien));
                    contratLocationRepository.save(contratLocation);
                }

                for (ContratMisEnLigne contratMisEnLigne : contratMisEnLigneList) {
                    contratMisEnLigne.setIdBien(bienRepository.save(monBien));
                    contratRepository.save(contratMisEnLigne);
                }

            }
        }


    }

    @Transactional
    public List<ContratLocationDTO> voirReservation(){
        if (JwtRequestFilter.maPersonne().getRoleId().getId() == 1) {
            return contratLocationRepository.findAllByBailleurOrderByIdDesc(JwtRequestFilter.maPersonne()).stream()
                    .map(contratLocationMapper::toDTO)
                    .collect(Collectors.toList());

        }else {
            return contratLocationRepository.findAllByPreneurOrderByIdDesc(JwtRequestFilter.maPersonne()).stream()
                    .map(contratLocationMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }
}
