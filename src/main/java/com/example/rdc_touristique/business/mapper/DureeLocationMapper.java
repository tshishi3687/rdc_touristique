package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.DureeLocationDTO;
import com.example.rdc_touristique.data_access.entity.DureeLocation;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class DureeLocationMapper implements Mapper<DureeLocationDTO, DureeLocation>{
    @Override
    public DureeLocationDTO toDTO(DureeLocation dureeLocation) {
        if(dureeLocation==null)
            return null;
        return new DureeLocationDTO(
                dureeLocation.getId(),
                dureeLocation.getDuree(),
                dureeLocation.getDesciption()
        );
    }

    @Override
    public DureeLocation toEntity(DureeLocationDTO dureeLocationDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(dureeLocationDTO==null)
            return null;

        DureeLocation dureeLocation = new DureeLocation();
        dureeLocation.setId(dureeLocationDTO.getId());
        dureeLocation.setDuree(dureeLocationDTO.getDuree());
        dureeLocation.setDesciption(dureeLocationDTO.getDesciption());
        return dureeLocation;
    }
}
