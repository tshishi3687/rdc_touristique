package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.RollDTO;
import com.example.rdc_touristique.data_access.entity.Roll;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class RollMapper implements Mapper<RollDTO, Roll> {
    @Override
    public RollDTO toDTO(Roll roll) {
        if (roll==null)
            return null;

        return new RollDTO(
                roll.getId(),
                roll.getNomRoll()
        );
    }

    @Override
    public Roll toEntity(RollDTO rollDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (rollDTO==null)
            return null;

        Roll roll = new Roll();
        roll.setId(rollDTO.getId());
        roll.setNomRoll(rollDTO.getNomRoll());

        return roll;
    }
}
