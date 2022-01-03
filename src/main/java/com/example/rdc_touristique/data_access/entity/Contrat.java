package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Personne preneur;

    @ManyToOne
    private Personne bailleur;

    @Column
    private boolean enCour;

    @Column(length = 1000)
    private String entre;

    @Column(length = 1000)
    private String entre2;

    @Column(length = 1000)
    private String objet;

    @Column(length = 1000)
    private String etatLieu;

    @Column(length = 1000)
    private String loyer;

    @Column(length = 1000)
    private String duree;

    @Column(length = 1000)
    private String  dardl;
}
