package com.example.rdc_touristique.business.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContratMisEnLigneDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private LocalDate ddDebut;
    private LocalDate ddFin;
    private BienVuDTO idBien;
    private PersonneSimpleDTO bailler;
    private PersonneSimpleDTO preneur;
    private boolean enCour;
    private String entre;
    private String entre2;
    private String objet;
    private String etatLieu;
    private String loyer;
    private String duree;
    private String dardl;
}
