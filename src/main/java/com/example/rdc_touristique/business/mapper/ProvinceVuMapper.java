package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.data_access.entity.Province;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class ProvinceVuMapper implements Mapper<ProvinceVuMapper, Province>{


    @Override
    public ProvinceVuMapper toDTO(Province province) {
        return new ProvinceVuMapper(
        );
    }

    @Override
    public Province toEntity(ProvinceVuMapper provinceVuMapper) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
