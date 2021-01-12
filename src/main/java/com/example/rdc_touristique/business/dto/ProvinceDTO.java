package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProvinceDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nomprovince;
    private String description;
}
