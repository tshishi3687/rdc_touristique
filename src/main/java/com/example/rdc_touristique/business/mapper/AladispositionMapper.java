package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.AladispositionDTO;
import com.example.rdc_touristique.data_access.entity.Aladisposition;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class AladispositionMapper implements Mapper<AladispositionDTO, Aladisposition>{
    @Override
    public AladispositionDTO toDTO(Aladisposition aladisposition) {
        if(aladisposition==null)
            return null;
        return new AladispositionDTO(
                aladisposition.getId(),
                aladisposition.isSecurite(),
                aladisposition.isWifi(),
                aladisposition.isTelevision(),
                aladisposition.isVesselle(),
                aladisposition.isLiterie(),
                aladisposition.isLingeMaison(),
                aladisposition.isEauFroide(),
                aladisposition.isEauChaude(),
                aladisposition.isEauPotable(),
                aladisposition.isJardin(),
                aladisposition.isCour(),
                aladisposition.isTerrasse(),
                aladisposition.isPiscinePrive(),
                aladisposition.isPiscineCommune(),
                aladisposition.isVoiture(),
                aladisposition.isMoto(),
                aladisposition.isVelo(),
                aladisposition.isAnimaux()
        );
    }

    @Override
    public Aladisposition toEntity(AladispositionDTO aladispositionDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(aladispositionDTO==null)
            return null;
        Aladisposition aladisposition = new Aladisposition();
        aladisposition.setId(aladispositionDTO.getId());
        aladisposition.setSecurite(aladispositionDTO.isSecurite());
        aladisposition.setWifi(aladispositionDTO.isWifi());
        aladisposition.setTelevision(aladispositionDTO.isTelevision());
        aladisposition.setVesselle(aladispositionDTO.isVesselle());
        aladisposition.setLiterie(aladispositionDTO.isLiterie());
        aladisposition.setLingeMaison(aladispositionDTO.isLingeMaison());
        aladisposition.setEauFroide(aladispositionDTO.isEauFroide());
        aladisposition.setEauChaude(aladispositionDTO.isEauChaude());
        aladisposition.setEauPotable(aladispositionDTO.isEauPotable());
        aladisposition.setJardin(aladispositionDTO.isJardin());
        aladisposition.setCour(aladispositionDTO.isCour());
        aladisposition.setTerrasse(aladispositionDTO.isTerrasse());
        aladisposition.setPiscinePrive(aladispositionDTO.isPiscinePrive());
        aladisposition.setPiscineCommune(aladispositionDTO.isPiscineCommune());
        aladisposition.setVoiture(aladispositionDTO.isVoiture());
        aladisposition.setMoto(aladispositionDTO.isMoto());
        aladisposition.setVelo(aladispositionDTO.isVelo());
        aladisposition.setAnimaux(aladispositionDTO.isAnimaux());
        return aladisposition;
    }
}
