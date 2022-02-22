package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.PaysDTO;
import com.example.rdc_touristique.data_access.entity.Pays;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class PaysMapper implements Mapper<PaysDTO, Pays> {
    @Override
    public PaysDTO toDTO(Pays pays) {
        if (pays == null)
            return null;
        return new PaysDTO(
                pays.getId(),
                pays.getCode(),
                pays.getAlpha2(),
                pays.getAlpha3(),
                pays.getNomEnGb(),
                pays.getNomFrFr()
        );
    }

    @Override
    public Pays toEntity(PaysDTO paysDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
