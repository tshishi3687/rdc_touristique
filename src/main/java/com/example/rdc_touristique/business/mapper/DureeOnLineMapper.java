package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.DureeOnLineDTO;
import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class DureeOnLineMapper implements Mapper<DureeOnLineDTO, BienMisEnLigne>{


    @Override
    public DureeOnLineDTO toDTO(BienMisEnLigne dureeOnLine) {
        return null;
    }

    @Override
    public BienMisEnLigne toEntity(DureeOnLineDTO dureeOnLineDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
