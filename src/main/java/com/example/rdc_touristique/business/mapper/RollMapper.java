package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.RollDTO;
import com.example.rdc_touristique.data_access.entity.RolePersonne;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class RollMapper implements Mapper<RollDTO, RolePersonne> {
    @Override
    public RollDTO toDTO(RolePersonne roll) {
        if (roll==null)
            return null;

        return new RollDTO(
                roll.getId(),
                roll.getNomRole()
        );
    }

    @Override
    public RolePersonne toEntity(RollDTO rollDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (rollDTO==null)
            return null;

        RolePersonne roll = new RolePersonne();
        roll.setId(rollDTO.getId());
        roll.setNomRole(rollDTO.getNomRoll());

        return roll;
    }
}
