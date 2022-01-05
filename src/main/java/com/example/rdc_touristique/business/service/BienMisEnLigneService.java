package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienMisEnLigneDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.BienMisEnLigneRepository;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BienMisEnLigneService implements CrudService<BienMisEnLigneDTO, Integer>{

    @Autowired
    private BienMisEnLigneRepository bienMisEnLigneRepository;
    @Autowired
    private Mapper<BienMisEnLigneDTO, BienMisEnLigne> bienMisEnLigneMapper;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private BienRepository bienRepository;

    @Override
    public void creat(BienMisEnLigneDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, MessagingException, ReservationFoundExeption {
        // créé dans le BienService.
    }

    @Override
    public BienMisEnLigneDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption, PaysFoundExeption {
        // pas besoin de cette méthode pour le moment.
        return null;
    }

    @Override
    public List<BienMisEnLigneDTO> readAll() {
        // Pas besoin de cette méthode pour le moment.
        // Mais je vais la personaliser comme suite :
            // pour le bailleur → dans une fonction nommée "allByContrat_bailleur"
            // pour le preneur → dans une fonction nommée "allByContrat_preneur"
        return null;
    }

    // readAll pour le bailleur
    @Transactional
    public List<BienMisEnLigneDTO> allByContrat_bailleur(PersonneSimpleDTO bailleur) throws PersonneSimplifierFoundExeption {
        if (!personneReposytory.existsById(bailleur.getId()))
            throw new PersonneSimplifierFoundExeption(bailleur.getId());

        // je vais chercher tous les bien_mis_en_ligne qui on dans leur contratBienEnLigne un bailleur donnée.
        return bienMisEnLigneRepository.findByContratBienMisEnLigne_Bailleur(personneReposytory.getOne(bailleur.getId())).stream()
                .map(bienMisEnLigneMapper::toDTO)
                .collect(Collectors.toList());
    }

    // readAll pour le preneur
    @Transactional
    public List<BienMisEnLigneDTO> allByContrat_preneur(PersonneSimpleDTO preneur) throws  PersonneSimplifierFoundExeption {
        if (!personneReposytory.existsById(preneur.getId()))
            throw new PersonneSimplifierFoundExeption(preneur.getId());

        // je vais chercher tous les bien_mis_en_ligne qui on dans leur contratBienEnLigne un preneur donnée.
        return bienMisEnLigneRepository.findByContratBienMisEnLigne_Preneur(personneReposytory.getOne(preneur.getId())).stream()
                .map(bienMisEnLigneMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BienMisEnLigneDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption, BienMisEnLigneFoundExeption {
        // Une modification implique:
            // 1. la verification si le bien_mis_en_ligne consernée existe.
            // 2. cette modification ne pourra être réalisé que par le preneur consernee.
            // 3. la modification du modeActive du bien lié en false.
            // 4. la modification de la date de fin de l'entité bien_mis_en_ligne consernee
            // 5. .... ..... à definer .... ....

        // 1. je verifie le bien_mis_en_ligne consernée existe en recherchant le contraire
        if (!bienMisEnLigneRepository.existsById(toUpdate.getId()))
            throw new BienMisEnLigneFoundExeption(toUpdate.getId());

        // 2. je veriffie si c'est le preneur consernée
            // 2.1 je reccupère le bien consernée car j'ai besoin de savoir qui en est le propriètaire.
        Bien bienConsernee = bienRepository.getOne(toUpdate.getBienLie().getId());

            // 2.2 je verifie si le propiètaire du bien consernée est le preneur du contrat
        if (bienConsernee.getAppartient().getId() == personneMapper.toEntity(toUpdate.getPreneurOuBailleur()).getId()){
            // si c'est la même personne alors:

                // 3. je modifie le modeActive du bien consernée en false.
            bienConsernee.setModeActive(false);
            bienRepository.save(bienConsernee);

                // 4. je modifie la date de fin de l'entité bien_mis_en_ligne consernee.
                    // 4.1. je reprend l'entité bien_mis_en_ligne consernée
            BienMisEnLigne bienMisEnLigneConserne = bienMisEnLigneRepository.getOne(toUpdate.getId());
                    // 4.3. je sauvegarde bien_mis_en_ligne consernée
            bienMisEnLigneRepository.save(bienMisEnLigneConserne);

                // 5. à définir....
                    // ex:  payement indemnité...
        }else{
            // si ce n'est pas la même personne alors: pas d'action... pour le moment...
        }


    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {
        // pour le moment pas d'action possible.
    }
}
