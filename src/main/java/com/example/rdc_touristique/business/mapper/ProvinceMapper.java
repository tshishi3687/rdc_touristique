package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.dto.VilleVuDTO;
import com.example.rdc_touristique.business.service.ImageProvinceService;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProvinceMapper implements Mapper<ProvinceDTO, Province>{

    @Autowired
    private ImageProvinceService imageProvince;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private Mapper<VilleVuDTO, Ville> villeMapper;


    @Override
    public ProvinceDTO toDTO(Province province) {
        if(province==null)
            return null;
        return new ProvinceDTO(
                province.getId(),
                province.getNomprovince(),
                province.getDescription(),
                imageProvince.getImage(province.getId()),
                villeRepository.findAllByProvinceOrderByNomVilleAsc(province).stream()
                        .map(villeMapper::toDTO)
                        .collect(Collectors.toList())
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

        return province;
    }
}
