package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.data_access.repository.ReservationRepository;
import com.example.rdc_touristique.data_access.repository.Type_bienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BienMapper implements Mapper<BienDTO, Bien>{

    @Autowired
    private Mapper<ReservationDTO, Reservation> reservationMapper;
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private Mapper<Type_bienDTO, Type_bien> type_bienMapper;
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Type_bienRepository type_bienRepository;
    @Autowired
    private CoordonneeRepository coordorRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private ReservationRepository reservationReposytory;

    @Override
    public BienDTO toDTO(Bien bien) {
        if (bien==null)
            return null;

        return new BienDTO(
                bien.getId(),
                type_bienMapper.toDTO(bien.getType()),
                bien.getPrix(),
                bien.getNpmin(),
                bien.getNpmax(),
                bien.getNchambre(),
                bien.getNsdb(),
                bien.getNwc(),
                bien.getSuperficie(),
                bien.getAladisposition(),
                bien.getDescription(),
                coordonneeMapper.toDTO(bien.getCoordonnee()),
                personneMapper.toDTO(bien.getAppartient())
        );
    }

    @Override
    public Bien toEntity(BienDTO bienDTO) {
        if (bienDTO==null)
            return null;

        Bien bien = new Bien();
        bien.setId(bienDTO.getId());
        bien.setType(type_bienRepository.getOne(bienDTO.getType_bien().getId()));
        bien.setPrix(bienDTO.getPrix());
        bien.setNpmin(bienDTO.getNpmin());
        bien.setNpmax(bienDTO.getNpmax());
        bien.setNchambre(bienDTO.getNchambre());
        bien.setNsdb(bienDTO.getNsdb());
        bien.setNwc(bienDTO.getNwc());
        bien.setSuperficie(bienDTO.getSuperficie());
        bien.setAladisposition(bienDTO.getAladisposition());
        bien.setDescription(bienDTO.getDescription());
        bien.setCoordonnee(coordonneeMapper.toEntity(bienDTO.getCoordonnee()));
        bien.setAppartient(personneReposytory.getOne(bienDTO.getAppartient().getId()));
        return bien ;
    }
}
