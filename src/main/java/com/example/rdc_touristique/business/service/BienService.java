package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.contratLocation.TextContrat;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    private PersonneReposytory personneReposytory;
    @Autowired
    private BienMisEnLigneRepository bienMisEnLigneRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private PersonneService personneService;

    @Transactional
    public List<BienVuDTO> selonLaPersonne(PersonneSimplifierDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException, BienFoundExeption {

        if (personne == null)
            throw new BienFoundExeption(personne.getId());
        actionDTO.setId(0);
        actionDTO.setDate(LocalDateTime.now());
        actionDTO.setClassName("Bien-personne");
        actionDTO.setIdClasse(personne.getId());
        actionDTO.setAction("Recherche");
        actionDTO.setDescription("Recherche de tous les biens appartenants à " + personne.getNom() + " / " + personne.getPrenom());
        actionRepository.save(actionMapper.toEntity(actionDTO));

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
            System.out.println("1 ok");
            actionDTO.setId(0);
            actionDTO.setDate(today);
            actionDTO.setClassName("Bien");
            actionDTO.setIdClasse(toCreat.getId());
            actionDTO.setAction("Création");
            actionDTO.setDescription("Création de/d' " +toCreat.getType_bien().getNom() +
                    " bien  à " + toCreat.getCoordonnee().getVille().getNomVille() +
                    " dans la province de " + toCreat.getCoordonnee().getVille().getProvince().getNomprovince()
                    + " par " + toCreat.getAppartient().getNom() + "-" + toCreat.getAppartient().getPrenom());
            actionRepository.save(actionMapper.toEntity(actionDTO));

            System.out.println("2 ok");
            Bien entity = bienMapper.toEntity(toCreat);
            entity.setModeActive(false);
            aladispositionRepository.save(entity.getAladisposition());
            System.out.println("3 ok");
            coordorRepository.save(entity.getCoordonnee());

            System.out.println("4 ok");
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
            // créé une ligne dans la table "bien_mis_en_ligne

        // 1. je reccupère le bien dans la bd.
        Bien bien = bienRepository.getOne(bienDTO.getId());
            // j'ai besoin de le transformer en personneSimple
        PersonneSimpleDTO personneSimpleDTO = personneSimpleMapper.toDTO(personneReposytory.getOne(bien.getAppartient().getId()));

        // 2. verifi si le bien reccupéré n'est pas déja active:
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
            Contrat contrat = new Contrat();
                contrat.setId(0); // id 0 pour la création
                contrat.setBailleur(bailleur); // bailleur: personne ENTITY
                contrat.setPreneur(preneur); // preneur: personne ENTITY
                contrat.setEntre(textContrat.EntreBailler());
                contrat.setEntre2(textContrat.EntrePreneur());
                contrat.setObjet(textContrat.objet());
                contrat.setEtatLieu(textContrat.etatLieu());
                contrat.setLoyer(textContrat.loyerMobembo());
                contrat.setDuree(textContrat.dureeMobembo());
                contrat.setDardl(textContrat.dARDL());

            Optional<BienMisEnLigne> bmel = bienMisEnLigneRepository.findByBienLie(bien);
            if (bmel.isPresent()){
                bmel.get().getBienLie().setDateFinMisEnLigne(LocalDate.now().plusDays(bienDTO.getIdNNuit()));
                contratRepository.deleteById(bmel.get().getContratBienMisEnLigne().getId());
                bmel.get().setContratBienMisEnLigne(contrat);
                bienMisEnLigneRepository.save(bmel.get());
            }else{
                // 6. je cree l'entité de la table bien_mis_en_ligne
                BienMisEnLigne bienMisEnLigne = new BienMisEnLigne();
                bienMisEnLigne.setId(0); // id mis à 0 pour s'assurée que le bien n'existe pas
                bienMisEnLigne.setBienLie(bien); // le bien consernée ou lié
                bienMisEnLigne.setContratBienMisEnLigne(contratRepository.save(contrat)); // j'intégre le nouveau contrat créé
                // 7. j'enregistre l'entité "bien_mis_en_ligne
                bienMisEnLigneRepository.save(bienMisEnLigne);
            }

            // 8. je change le modeActive du bien en active
            bien.setModeActive(true);
            bienRepository.save(bien);

        }

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
