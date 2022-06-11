package com.example.rdc_touristique.business.dto;

import com.example.rdc_touristique.data_access.entity.ImageBien;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BienVuSimplifierDTO {

    private Integer id;
    private Type_bienDTO type_bien;
    private AladispositionDTO aladisposition;
    private int prix;
    private int npmin;
    private int npmax;
    private int nchambre;
    private int nsdb;
    private int nwc;
    private int superficie;
    private String description;
    private CoordonneeDTO coordonnee;
    private int likes;
    private boolean modeActive;
    private ImageBien image;
}
