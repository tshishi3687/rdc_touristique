package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.InfoBancaire;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class InfoBancaireMapper implements Mapper<InfoBancaireDTO, InfoBancaire> {
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public InfoBancaireDTO toDTO(InfoBancaire infoBancaire) {
        if (infoBancaire==null)
            return null;

        return new InfoBancaireDTO(
                infoBancaire.getId(),
                infoBancaire.getNomBanque().charAt(0) + "****",
                "**** **** **** ***" + infoBancaire.getNumCarte().charAt(infoBancaire.getNumCarte().length() -1),
                "**** **** **** ***" + infoBancaire.getNumCompte().charAt(infoBancaire.getNumCompte().length() - 1),
                infoBancaire.getDateExpiration(),
                personneMapper.toDTO(infoBancaire.getAppartienA())
        );
    }

    @Override
    public InfoBancaire toEntity(InfoBancaireDTO infoBancaireDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (infoBancaireDTO==null)
            return null;

        InfoBancaire infoBancaire = new InfoBancaire();
        infoBancaire.setId(infoBancaireDTO.getId());
        infoBancaire.setNomBanque(infoBancaireDTO.getNomBanque());
        infoBancaire.setNumCarte(infoBancaireDTO.getNumCarte());
        infoBancaire.setNumCompte(infoBancaireDTO.getNumCompte());
        infoBancaire.setDateExpiration(infoBancaireDTO.getDateExpiration());
        infoBancaire.setAppartienA(personneReposytory.getOne(infoBancaireDTO.getAppartienA().getId()));
        return infoBancaire;
    }
}
