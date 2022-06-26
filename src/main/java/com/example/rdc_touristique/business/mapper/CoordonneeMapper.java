package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.data_access.entity.Coordonnee;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CoordonneeMapper implements Mapper<CoordonneeDTO, Coordonnee>{
    @Autowired
    private Mapper<VilleDTO, Ville> villeMapper;
    @Autowired
    private VilleRepository villeRepository;

    @Override
    public CoordonneeDTO toDTO(Coordonnee coordonnee) {
        if(coordonnee==null)
            return null;
        return new CoordonneeDTO(
                coordonnee.getId(),
                villeMapper.toDTO(coordonnee.getVille()),
                "****",
                "****",
                "****",
                "****",
                "****",
                coordonnee.getDateCreation()
        );
    }

    @Override
    public Coordonnee toEntity(CoordonneeDTO coordonneeDTO) {
        if(coordonneeDTO==null)
            return null;

        Coordonnee coordonnee = new Coordonnee();
          coordonnee.setId(coordonneeDTO.getId());
          coordonnee.setVille(villeRepository.getOne(coordonneeDTO.getVille().getId()));
          coordonnee.setCpostal(coordonneeDTO.getCpostal());
          coordonnee.setRue(coordonneeDTO.getRue());
          coordonnee.setNum(coordonneeDTO.getNum());
          coordonnee.setEmail(coordonneeDTO.getEmail());
          coordonnee.setTelephone(coordonneeDTO.getTelephone());
          coordonnee.setDateCreation(LocalDateTime.now());
        return coordonnee;
    }
}
