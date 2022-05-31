package com.example.rdc_touristique.business.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContratLocationDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private LocalDate ddDebut;
    private LocalDate ddFin;
    private BienVuDTO bienVuDTO;
    private int nPersonneSurLieu;
    private PersonneSimpleDTO preneur;
    private PersonneSimpleDTO bailleur;
    private boolean enCour;
    private String entre;
    private String entre2;
    private String objet;
    private String etatLieu;
    private String loyer;
    private String duree;
    private String  dardl;
    private DetailsDTO details;
}
