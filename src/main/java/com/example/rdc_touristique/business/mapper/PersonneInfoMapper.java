package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneInfoDTO;
import com.example.rdc_touristique.business.dto.DemandeDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Demande;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.stream.Collectors;

@Component
public class PersonneInfoMapper implements Mapper<PersonneInfoDTO, Personne>{

    @Autowired
    private Mapper<BienDTO, Bien> bienDTOMapper;
    @Autowired
    private Mapper<DemandeDTO, Demande> reservationMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private DemandeRepository demandeRepository;


    @Override
    public PersonneInfoDTO toDTO(Personne personne) {
        if(personne==null)
            return null;

        return null;
//        return new PersonneInfoDTO(
//                personne.getId(),
//                personne.getBien().stream()
//                .map((bien -> bienDTOMapper.toDTO(bien)))
//                .collect(Collectors.toList()),
//                personne.getReservation().stream()
//                .map((reservation -> reservationMapper.toDTO(reservation)))
//                .collect(Collectors.toList())
//        );
    }

    @Override
    public Personne toEntity(PersonneInfoDTO personneInfoDTO) {
        if(personneInfoDTO==null)
            return null;

        Personne personne = new Personne();
        personne.setId(personneInfoDTO.getId());
        personne.setBien(personneInfoDTO.getBien().stream()
                    .map(bienDTO -> {
                        try {
                            return bienDTOMapper.toEntity(bienDTO);
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList()));
        personne.setDemande(demandeRepository.findAllById(personneInfoDTO.getReservation()
                .stream()
                .map(DemandeDTO::getId)
                .collect(Collectors.toList())))
        ;

        return null;
//        return personne;
    }
}
