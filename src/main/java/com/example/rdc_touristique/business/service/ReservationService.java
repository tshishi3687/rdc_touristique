package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Reservation;
import com.example.rdc_touristique.data_access.repository.ReservationRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService implements CrudService<ReservationDTO, Integer> {

    @Autowired
    private Mapper<ReservationDTO, Reservation> reservationMapper;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void creat(ReservationDTO toCreat) throws ElementAlreadyExistsException {
        if (reservationRepository.existsById(toCreat.getId()))
            throw new ReservationExisteExeption(toCreat.getId());

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
    public void update(ReservationDTO toUpdate) throws ReservationFoundExeption {
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
