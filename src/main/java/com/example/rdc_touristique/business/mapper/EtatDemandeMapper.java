package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.EtatDemandeDTO;
import com.example.rdc_touristique.data_access.entity.EtatDemande;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class EtatDemandeMapper implements Mapper<EtatDemandeDTO, EtatDemande> {
    @Override
    public EtatDemandeDTO toDTO(EtatDemande etatDemande) {
        if (etatDemande == null)
            return null;

        return new EtatDemandeDTO(
                etatDemande.getId(),
                etatDemande.getEtat()
        );
    }

    @Override
    public EtatDemande toEntity(EtatDemandeDTO etatDemandeDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (etatDemandeDTO == null)
            return null;
        EtatDemande etatDemande = new EtatDemande();
        etatDemande.setId(etatDemandeDTO.getId());
        etatDemande.setEtat(etatDemandeDTO.getEtat());
        return etatDemande;
    }
}
