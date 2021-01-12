package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneInfoDTO;
import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Reservation;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersonneInfoMapper implements Mapper<PersonneInfoDTO, Personne>{

    @Autowired
    private Mapper<BienDTO, Bien> bienDTOMapper;
    @Autowired
    private Mapper<ReservationDTO, Reservation> reservationMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public PersonneInfoDTO toDTO(Personne personne) {
        if(personne==null)
            return null;

        return new PersonneInfoDTO(
                personne.getId(),
                personne.getBien().stream()
                .map((bien -> bienDTOMapper.toDTO(bien)))
                .collect(Collectors.toList()),
                personne.getReservation().stream()
                .map((reservation -> reservationMapper.toDTO(reservation)))
                .collect(Collectors.toList())
        );
    }

    @Override
    public Personne toEntity(PersonneInfoDTO personneInfoDTO) {
        if(personneInfoDTO==null)
            return null;

        Personne personne = new Personne();
        personne.setId(personneInfoDTO.getId());
        personne.setBien(personneInfoDTO.getBien().stream()
                    .map(bienDTO -> bienDTOMapper.toEntity(bienDTO))
                    .collect(Collectors.toList()));
        personne.setReservation(reservationRepository.findAllById(personneInfoDTO.getReservation()
                .stream()
                .map(reservationDTO -> reservationDTO.getId())
                .collect(Collectors.toList())))
        ;

        return personne;
    }
}
