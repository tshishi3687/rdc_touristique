package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Reservation;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper implements Mapper<ReservationDTO, Reservation>{
    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public ReservationDTO toDTO(Reservation reservation) {
        if(reservation==null)
            return null;

        return new ReservationDTO(
                reservation.getId(),
                reservation.getDdj(),
                reservation.getDda(),
                reservation.getDdd(),
                reservation.getNpersonne(),
                bienMapper.toDTO(reservation.getBien_reserve()),
                personneMapper.toDTO(reservation.getReserverPar())
        );
    }

    @Override
    public Reservation toEntity(ReservationDTO reservationDTO) {
        if(reservationDTO==null)
            return null;

        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setDdj(reservationDTO.getDdj());
        reservation.setDda(reservationDTO.getDda());
        reservation.setDdd(reservationDTO.getDdd());
        reservation.setNpersonne(reservationDTO.getNpersonne());
        reservation.setBien_reserve(bienRepository.getOne(reservationDTO.getBien_reserve().getId()));
        reservation.setReserverPar(personneReposytory.getOne(reservationDTO.getReserverPar().getId()));
        return reservation;
    }
}
