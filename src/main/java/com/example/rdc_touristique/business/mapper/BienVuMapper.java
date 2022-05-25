package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.ContratLocationRepository;
import com.example.rdc_touristique.data_access.repository.ContratMisEnLigneRepository;
import com.example.rdc_touristique.data_access.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BienVuMapper implements Mapper<BienVuDTO, Bien>{

    @Autowired
    private Mapper<Type_bienDTO, Type_bien> type_bienMapper;
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Mapper<AladispositionDTO, Aladisposition> aladispositionMapper;
    @Autowired
    private ImageModelService imageModelService;
    @Autowired
    private ServiceRepository service;
    @Autowired
    private Mapper<Type_serviceDTO, Type> typeMapper;
    @Autowired
    private ContratMisEnLigneRepository contratRepository;
    @Autowired
    private ContratLocationRepository contratLocationRepository;

    @Override
    public BienVuDTO toDTO(Bien bien) {
        if (bien==null)
            return null;

        int likes;
        if((bien.getLikes()== null) || (bien.getLikes().size()<=0)){
            likes = 0;
        }else{
            likes = bien.getLikes().size();
        }


        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        List<Service> serviceList = service.findAllByCoordonnee_Ville(bien.getCoordonnee().getVille());
        for (Service value : serviceList) {
            ServiceDTO dto = new ServiceDTO();
            dto.setNom(value.getNom());
            dto.setType(typeMapper.toDTO(value.getType()));
            dto.setCoordonnee(coordonneeMapper.toDTO(value.getCoordonnee()));
            serviceDTOS.add(dto);
        }

        // list date de mise en ligne
        Stream<LocalDate> date = null;
            List<ContratMisEnLigne> contratMisEnLignes = contratRepository.findAllByIdBien(bien);
            for (ContratMisEnLigne misEnLigne : contratMisEnLignes){
                if (misEnLigne.isEnCour()){
                     date = LocalDate.now().datesUntil(misEnLigne.getDdFin());
                }
            }

        // list date réservé
        Stream<LocalDate> date2 = null;
            List<ContratLocation> contratLocations = contratLocationRepository.findAllByIdBien(bien);
            List<LocalDate> dateReservation = new ArrayList<>();
            for (ContratLocation location : contratLocations){
                if (location.isEnCour()){
                    date2 = location.getDdDebut().datesUntil(location.getDdFin());
                }
            }





        return new BienVuDTO(
                bien.getId(),
                type_bienMapper.toDTO(bien.getType()),
                aladispositionMapper.toDTO(bien.getAladisposition()),
                bien.getPrix(),
                bien.getNpmin(),
                bien.getNpmax(),
                bien.getNchambre(),
                bien.getNsdb(),
                bien.getNwc(),
                bien.getSuperficie(),
                bien.getDescription(),
                coordonneeMapper.toDTO(bien.getCoordonnee()),
                likes,
                bien.isModeActive(),
                imageModelService.getImage(bien.getId()),
                serviceDTOS,
                0,
                date.collect(Collectors.toList()),
                date2.collect(Collectors.toList())
        );
    }

    @Override
    public Bien toEntity(BienVuDTO bienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
