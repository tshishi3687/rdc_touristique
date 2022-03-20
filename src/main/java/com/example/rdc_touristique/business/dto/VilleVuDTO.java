package com.example.rdc_touristique.business.dto;

import com.example.rdc_touristique.data_access.entity.ImageVille;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VilleVuDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nomVille;
    private Integer nhabitant;
    private String description;
    private List<ImageVille> img;
}
