package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aladisposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private boolean securite;

    @Column
    private boolean wifi;

    @Column
    private boolean television;

    @Column
    private boolean vesselle;

    @Column
    private boolean literie;

    @Column
    private boolean lingeMaison;

    @Column
    private boolean eauFroide;

    @Column
    private boolean eauChaude;

    @Column
    private boolean eauPotable;

    @Column
    private boolean jardin;

    @Column
    private boolean cour;

    @Column
    private boolean terrasse;

    @Column
    private boolean piscinePrive;

    @Column
    private boolean piscineCommune;

    @Column
    private boolean voiture;

    @Column
    private boolean moto;

    @Column
    private boolean velo;

    @Column
    private boolean animaux;
}
