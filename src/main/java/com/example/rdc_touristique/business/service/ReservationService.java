package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.Email.EngistrementEmail;
import com.example.rdc_touristique.Email.StringText;
import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.contratLocation.TextContrat;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.*;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService implements CrudService<ReservationDTO, Integer> {

    @Autowired
    private Mapper<ReservationDTO, Reservation> reservationMapper;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private EngistrementEmail mail;
    @Autowired
    private StringText textMail;
    @Autowired
    private Mapper<BienVuDTO, Bien> bienVuMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private ContratRepository contratRepository;

    @Transactional
    public List<ReservationDTO> selonLaPer(PersonneSimpleDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {

        return reservationRepository.findAllByContratLieeReservation_PreneurOrderByIdDesc(personneMapper.toEntity(personne)).stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ReservationDTO> reservationFait(PersonneSimpleDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }

    @Override
    public void creat(ReservationDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException, MessagingException, ReservationFoundExeption {
        if (reservationRepository.existsById(toCreat.getId())) {
            throw new ReservationExisteExeption(toCreat.getId());
        }

        // je vérifi les date
            //si elles ne sont "vide"
            //si elles ne sont coérantes
        if (toCreat.getDateArrivee().isBefore(LocalDate.now()) || toCreat.getDateDepart().isBefore(toCreat.getDateArrivee()))
            throw new ReservationFoundExeption(0);

        // je defini les deux parties
            // le Preuneur: personne qui reserve
        Personne preuneur = personneReposytory.getOne(toCreat.getContratLieeReservation().getPreneur().getId());
            // le Bailleur: personne qui donne accès au bien
            // pour le projet, personne qui à le rôle Admin
        Personne bailleur = personneReposytory.findByRoll_NomRoll("Admin");

        // je le transforme en DTO Vu
        Bien bien = bienRepository.getOne(toCreat.getBienReservee().getId());
        BienVuDTO bienVuDTO= bienVuMapper.toDTO(bien);


        // j'enregistre la réservation et je récupaire son id
        int numDemande = reservationRepository.save(reservationMapper.toEntity(toCreat)).getId();

        // je crée le text du contrat
        TextContrat textContrat = new TextContrat(
            bailleur,
            preuneur,
            bienVuDTO,
            toCreat.getDateArrivee(),
            toCreat.getDateDepart()
        );

        // je crée le contrat
        Contrat contrat = new Contrat();
            contrat.setId(0);
            contrat.setBailleur(bailleur);
            contrat.setPreneur(preuneur);
            // je verifi si le client à réservé aujourd'hui... si oui, j'active le contrat
            contrat.setEnCour(toCreat.getDateArrivee().isEqual(LocalDate.now()));
            contrat.setEntre(textContrat.EntreBailler());
            contrat.setEntre2(textContrat.EntrePreneur());
            contrat.setObjet(textContrat.objet());
            contrat.setEtatLieu(textContrat.etatLieu());
            contrat.setLoyer(textContrat.loyer());
            contrat.setDuree(textContrat.dureeClient());
            contrat.setDardl(textContrat.dARDL());


        // je lie le contrat à la réservation
        Reservation reservation = reservationMapper.toEntity(toCreat); // transforme la reservation en entité
            reservation.setDateCreation(LocalDateTime.now()); // date du jour (serveur)
            reservation.setDateArrivee(toCreat.getDateArrivee()); // date arrivé donné par le client
            reservation.setDateDepart(toCreat.getDateDepart()); // date départ donné par le client
            reservation.setNPersonneSurLieu(toCreat.getNPersonneSurLieu()); // nombre de personne donné pars le client
            reservation.setContratLieeReservation(contratRepository.save(contrat)); // je sauvegarde le contract créé à la ligne 98
            reservation.setBienReservee(bienRepository.getOne(toCreat.getBienReservee().getId())); // je vai rechercher le bien réservé grace à son ID

        // je sauvegarde la réservation
        reservationRepository.save(reservation);


        // Envoi maill bailler
            mail.envoyer(preuneur.getContactUser().getEmail(),
                    textMail.getSujetEnvoisDemande(),
                    textMail.confimationEnvoisDemande(toCreat.getBienReservee().getCoordonnee().getVille().getProvince().getNomprovince(),
                            toCreat.getBienReservee().getCoordonnee().getVille().getNomVille(),
                            toCreat.getBienReservee().getType_bien().getNom(),
                            toCreat.getBienReservee().getCoordonnee().getNum() + " " + toCreat.getBienReservee().getCoordonnee().getRue(),
                            bailleur.getNom() + " " +bailleur.getPrenom(),
                            numDemande,
                            "En Attente",
                            toCreat.getDateArrivee(),
                            toCreat.getDateDepart()));


        // Envoi mail preneur
            mail.envoyer(bailleur.getContactUser().getEmail(),
                    textMail.getSujetDemanderecu(),
                    textMail.notificationDEmande(toCreat.getBienReservee().getCoordonnee().getVille().getProvince().getNomprovince(),
                            toCreat.getBienReservee().getCoordonnee().getVille().getNomVille(),
                            toCreat.getBienReservee().getType_bien().getNom(),
                            toCreat.getBienReservee().getCoordonnee().getNum() + " " + toCreat.getBienReservee().getCoordonnee().getRue(),
                            "Mobembo.cd",
                            numDemande,
                            "En Attente",
                            toCreat.getDateArrivee(),
                            toCreat.getDateDepart(),
                            preuneur.getNom() + " " + preuneur.getPrenom()));

    }

    // lire une cotrat spécifique
    @Override
    public ReservationDTO readOne(Integer integer) throws ReservationFoundExeption {
        Reservation entity = reservationRepository.findById(integer)
                .orElseThrow(()-> new ReservationFoundExeption(integer));

        return reservationMapper.toDTO(entity);
    }

    @Override
    public List<ReservationDTO> readAll() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    //  pas de modification possible
    @Override
    public void update(ReservationDTO toUpdate) throws ReservationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {

    }

    // pas de suppréssion possible
    @Override
    public void delete(Integer toDelete) throws ReservationFoundExeption {

    }
}
