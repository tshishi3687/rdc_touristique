package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.awt.*;
import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lien_photoDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private byte[] image;
    private ProvinceDTO province;
    private VilleDTO ville;
    private BienDTO bien;
}
