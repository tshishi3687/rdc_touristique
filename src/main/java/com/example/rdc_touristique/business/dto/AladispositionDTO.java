package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AladispositionDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private boolean securite;
    private boolean wifi;
    private boolean television;
    private boolean vesselle;
    private boolean literie;
    private boolean lingeMaison;
    private boolean eauFroide;
    private boolean eauChaude;
    private boolean eauPotable;
    private boolean jardin;
    private boolean cour;
    private boolean terrasse;
    private boolean piscinePrive;
    private boolean piscineCommune;
    private boolean voiture;
    private boolean moto;
    private boolean velo;
    private boolean animaux;
}
