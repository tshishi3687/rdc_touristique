package com.example.rdc_touristique.business.dto;

import com.example.rdc_touristique.data_access.entity.Action;
import com.example.rdc_touristique.data_access.entity.DureeLocation;
import lombok.*;

import javax.persistence.Column;
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
    private DureeLocationDTO dureeLocation;
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
}
