package com.example.rdc_touristique.business.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BienDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private Type_bienDTO type_bien;
    private int dureeOnLine;
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
    private PersonneSimplifierDTO appartient;
    private LocalDateTime dateCreation;
    private int likes;
    private boolean modeActive;
    private List<MultipartFile> img;
}
