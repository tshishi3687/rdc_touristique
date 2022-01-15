package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pays extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int code;

    @Column
    private String alpha2;

    @Column
    private String alpha3;

    @Column(name = "nom_en_gb")
    private String 	nomEnGb;

    @Column(name = "nom_fr_fr")
    private String nomFrFr;
}
