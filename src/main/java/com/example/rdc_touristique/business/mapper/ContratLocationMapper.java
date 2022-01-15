package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.business.dto.ContratLocationDTO;
import com.example.rdc_touristique.business.dto.ContratMisEnLigneDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.ContratLocation;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ContratLocationMapper implements Mapper<ContratLocationDTO, ContratLocation>{

    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private Mapper<BienVuDTO, Bien> bienMapper;

    @Override
    public ContratLocationDTO toDTO(ContratLocation contratLocation) {
        if (contratLocation==null)
            return null;

        return new ContratLocationDTO(
                contratLocation.getId(),
                contratLocation.getDdDebut(),
                contratLocation.getDdFin(),
                bienMapper.toDTO(contratLocation.getIdBien()),
                contratLocation.getNPersonneSurLieu(),
                personneMapper.toDTO(contratLocation.getBailleur()),
                personneMapper.toDTO(contratLocation.getPreneur()),
                contratLocation.isEnCour(),
                contratLocation.getEntre(),
                contratLocation.getEntre2(),
                contratLocation.getObjet(),
                contratLocation.getEtatLieu(),
                contratLocation.getLoyer(),
                contratLocation.getDuree(),
                contratLocation.getDardl()
        );
    }

    @Override
    public ContratLocation toEntity(ContratLocationDTO contratLocationDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}