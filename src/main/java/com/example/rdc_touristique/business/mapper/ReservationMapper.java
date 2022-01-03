package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ContratDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Contrat;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReservationMapper implements Mapper<ReservationDTO, Reservation>{
    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private Mapper<ContratDTO, Contrat> contratMapper;
    @Override
    public ReservationDTO toDTO(Reservation reservation) {
        if(reservation ==null)
            return null;

        return new ReservationDTO(
                reservation.getId(),
                reservation.getDateCreation(),
                bienMapper.toDTO(reservation.getBienReservee()),
                reservation.getDateArrivee(),
                reservation.getDateDepart(),
                reservation.getNPersonneSurLieu(),
                contratMapper.toDTO(reservation.getContratLieeReservation())
        );
    }

    @Override
    public Reservation toEntity(ReservationDTO reservationDTO) {
        return null;
    }
}
