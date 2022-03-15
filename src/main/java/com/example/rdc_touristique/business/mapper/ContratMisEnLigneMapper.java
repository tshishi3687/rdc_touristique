package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.business.dto.ContratMisEnLigneDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.ContratMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class ContratMisEnLigneMapper implements Mapper<ContratMisEnLigneDTO, ContratMisEnLigne> {

    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private Mapper<BienVuDTO, Bien> bienMapper;

    @Override
    public ContratMisEnLigneDTO toDTO(ContratMisEnLigne contrat) {
        if (contrat==null)
            return null;

        return new ContratMisEnLigneDTO(
                contrat.getId(),
                bienMapper.toDTO(contrat.getIdBien()),
                contrat.getDdDebut(),
                contrat.getDdFin(),
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
    public ContratMisEnLigne toEntity(ContratMisEnLigneDTO contratDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // tous les action je les fait dans BienServie
        return null;
    }
}
