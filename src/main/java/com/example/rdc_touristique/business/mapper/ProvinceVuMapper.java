package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.VilleVuDTO;
import com.example.rdc_touristique.business.service.ImageProvinceService;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class ProvinceVuMapper implements Mapper<ProvinceVuMapper, Province>{

    @Autowired
    private ImageProvinceService imageProvince;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private Mapper<VilleVuDTO, Ville> villeMapper;

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
