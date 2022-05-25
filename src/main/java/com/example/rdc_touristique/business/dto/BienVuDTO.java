package com.example.rdc_touristique.business.dto;


import com.example.rdc_touristique.data_access.entity.Details;
import com.example.rdc_touristique.data_access.entity.ImageBien;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BienVuDTO implements IdentifiedDTO<Integer>{

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
    private List<ImageBien> images;
    private List<ServiceDTO> service;
    private int idNNuit;
    private List<LocalDate> disponibles;
    private List<LocalDate> reservers;
}
