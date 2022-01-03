package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.ContratDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.data_access.entity.Contrat;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class ContratMapper implements Mapper<ContratDTO, Contrat> {

    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;

    @Override
    public ContratDTO toDTO(Contrat contrat) {
        if (contrat==null)
            return null;

        return new ContratDTO(
                contrat.getId(),
                personneMapper.toDTO(contrat.getBailleur()),
                personneMapper.toDTO(contrat.getPreneur()),
                contrat.isEnCour(),
                contrat.getEntre(),
                contrat.getEntre2(),
                contrat.getObjet(),
                contrat.getEtatLieu(),
                contrat.getLoyer(),
                contrat.getDuree(),
                contrat.getDardl()
        );
    }

    @Override
    public Contrat toEntity(ContratDTO contratDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
