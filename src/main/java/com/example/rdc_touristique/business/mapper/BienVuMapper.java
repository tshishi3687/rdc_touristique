package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public BienVuDTO toDTO(Bien bien) {
        if (bien==null)
            return null;


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
        List<LocalDate> ListDateMEL = new ArrayList<>();
            List<ContratMisEnLigne> contratMisEnLignes = bien.getContratMisEnLigneList();
            if (contratMisEnLignes != null) {
                for (ContratMisEnLigne misEnLigne : contratMisEnLignes) {
                    if (misEnLigne.isEnCour()) {
                        // TODO temporary workaround To refactor later
                        date = misEnLigne.getDdDebut().datesUntil(misEnLigne.getDdFin().plusDays(1));
                        ListDateMEL.addAll(date.collect(Collectors.toList()));
                    }
                }
            }

        // list date réservé
        Stream<LocalDate> date2 = null;
            List<LocalDate> ListDateReserv = new ArrayList<>();
            List<ContratLocation> contratLocations = bien.getContratLocationList();
            if (contratLocations != null) {
                for (ContratLocation location : contratLocations) {
                    if (location.isEnCour()) {

                        date2 = location.getDdDebut().datesUntil(location.getDdFin().plusDays(1));
                        ListDateReserv.addAll(date2.collect(Collectors.toList()));
                    }
                }
            }


        int likes;
        if((bien.getLikes()== null) || (bien.getLikes().size()<=0)){
            likes = 0;
        }else{
            likes = bien.getLikes().size();
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
                imageModelService.getImage(bien.getId()).isEmpty() ? null : imageModelService.getImage(bien.getId()),
                serviceDTOS,
                0,
                ListDateMEL,
                ListDateReserv,
                bien.getAppartirDe()
        );
    }

    @Override
    public Bien toEntity(BienVuDTO bienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
