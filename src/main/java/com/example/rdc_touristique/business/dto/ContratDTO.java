package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContratDTO implements IdentifiedDTO<Integer>{
    private Integer id;
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
