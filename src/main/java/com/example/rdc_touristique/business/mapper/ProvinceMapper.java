package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.data_access.entity.Province;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProvinceMapper implements Mapper<ProvinceDTO, Province>{
    @Override
    public ProvinceDTO toDTO(Province province) {
        if(province==null)
            return null;
        return new ProvinceDTO(
                province.getId(),
                province.getNomprovince(),
                province.getDescription(),
                province.getDateCreation(),
                province.getSuperid()
        );
    }

    @Override
    public Province toEntity(ProvinceDTO provinceDTO) {
        if(provinceDTO==null)
            return null;

        Province province = new Province();
        province.setId(provinceDTO.getId());
        province.setNomprovince(provinceDTO.getNomprovince());
        province.setDescription(provinceDTO.getDescription());
        province.setDateCreation(LocalDateTime.now());
        province.setSuperid(0);

        return province;
    }
}
