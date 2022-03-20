package com.example.rdc_touristique.business.dto;

import com.example.rdc_touristique.data_access.entity.ImageProvince;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProvinceDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private String nomprovince;
    private String description;
    private List<ImageProvince> img;
    private List<VilleVuDTO> villes;
}
