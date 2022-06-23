package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.InfoBancairePersonne;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class InfoBancaireMapper implements Mapper<InfoBancaireDTO, InfoBancairePersonne> {
    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;

    @Override
    public InfoBancaireDTO toDTO(InfoBancairePersonne infoBancaire) {
        if (infoBancaire==null)
            return null;

        return new InfoBancaireDTO(
                infoBancaire.getId(),
                infoBancaire.getNomBanque().isEmpty() ? null : "****" + infoBancaire.getNomBanque().charAt(infoBancaire.getNomBanque().length() - 1),
                infoBancaire.getNumCarte().isEmpty() ? null : "**** **** **** ***" + infoBancaire.getNumCarte().charAt(infoBancaire.getNumCarte().length() -1),
                infoBancaire.getNumCompte().isEmpty() ? null : "**** **** **** ***" + infoBancaire.getNumCompte().charAt(infoBancaire.getNumCompte().length() - 1),
                infoBancaire.getDateExpiration(),
                personneMapper.toDTO(infoBancaire.getAppartienA())
        );
    }

    @Override
    public InfoBancairePersonne toEntity(InfoBancaireDTO infoBancaireDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (infoBancaireDTO==null)
            return null;

        InfoBancairePersonne infoBancaire = new InfoBancairePersonne();
        infoBancaire.setId(infoBancaireDTO.getId());
        infoBancaire.setNomBanque(infoBancaireDTO.getNomBanque());
        infoBancaire.setNumCarte(infoBancaireDTO.getNumCarte());
        infoBancaire.setNumCompte(infoBancaireDTO.getNumCompte());
        infoBancaire.setDateExpiration(infoBancaireDTO.getDateExpiration());
        infoBancaire.setAppartienA(JwtRequestFilter.maPersonne());
        return infoBancaire;
    }
}
