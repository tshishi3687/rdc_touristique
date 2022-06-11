package com.example.rdc_touristique.business.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContratMisEnLigneDTO implements IdentifiedDTO<Integer> {
    private Integer Id;
    private BienVuSimplifierDTO bienVuDTO;
    private LocalDate ddDebut;
    private LocalDate ddFin;
    private boolean enCour;
    private String entre;
    private String entre2;
    private String objet;
    private String etatLieu;
    private String loyer;
    private String duree;
    private String dardl;
    private DetailsDTO details;
}
