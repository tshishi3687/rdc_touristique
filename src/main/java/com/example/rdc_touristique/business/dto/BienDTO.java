package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BienDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private Type_bienDTO type_bien;
    private int prix;
    private int npmin;
    private int npmax;
    private int nchambre;
    private int nsdb;
    private int nwc;
    private int superficie;
    private String aladisposition;
    private String animaux;
    private String description;
    private CoordonneeDTO coordonnee;
    private PersonneSimplifierDTO appartient;
}
