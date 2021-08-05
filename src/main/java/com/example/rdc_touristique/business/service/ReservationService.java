package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Reservation;
import com.example.rdc_touristique.data_access.repository.ReservationRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
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

    @Transactional
    public List<ReservationDTO> selonLaPer(PersonneSimpleDTO personne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        personne.setMdp("salut123456789");
        return reservationRepository.findAllByReserverPar(personneMapper.toEntity(personne)).stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public boolean verifDate(ReservationDTO reservationDTO) throws ReservationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
//        List<Reservation> liestReservation = reservationRepository.findAllByBAndBien_reserve(bienMapper.toEntity(reservationDTO.getBien_reserve()));
//
//        if(reservationDTO.getDdd().isBefore(reservationDTO.getDda()) || reservationDTO.getDda().isBefore(LocalDate.now()))
//            return false;
//
//
//        for (int i=0;i< liestReservation.size();i++){
//            // Range covers other ?
//            Reservation reservation = liestReservation.get(i);
//            Reservation reservation2 = liestReservation.get(i+1);
//            // Range covers other ?
//            if((reservationDTO.getDda().isAfter(reservation.getDda()) && reservationDTO.getDda().isBefore(reservation.getDdd())) || reservationDTO.getDda().isEqual(reservation.getDda()) || reservationDTO.getDda().isEqual(reservation.getDdd()) || reservationDTO.getDdd().isEqual(reservation2.getDda()))
//            {
//
//            }else{
//                return true;
//            }
//        }
//
//        return false;
//
//    }

    @Override
    public void creat(ReservationDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (reservationRepository.existsById(toCreat.getId())) {
            throw new ReservationExisteExeption(toCreat.getId());
        }
        reservationRepository.save(reservationMapper.toEntity(toCreat));
    }

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

    @Override
    public void update(ReservationDTO toUpdate) throws ReservationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !reservationRepository.existsById( toUpdate.getId() ))
            throw new ReservationFoundExeption(toUpdate.getId());

        reservationRepository.save( reservationMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws ReservationFoundExeption {
        if( !reservationRepository.existsById(toDelete))
            throw new ReservationFoundExeption(toDelete);

        reservationRepository.deleteById(toDelete);
    }
}
