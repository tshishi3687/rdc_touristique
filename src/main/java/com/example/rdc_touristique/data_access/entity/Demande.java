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
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_creation")
    private LocalDateTime ddj;

    @Column
    private LocalDate dda;

    @Column
    private LocalDate ddd;

    @Column
    private int npersonne;

    @ManyToOne
    @JoinColumn(name = "etat_demande")
    private EtatDemande etatDemande;

    @ManyToOne
    @JoinColumn(name = "bien_reserve")
    private Bien bienDemandee;

    @ManyToOne
    @JoinColumn(name = "reserver_par")
    private Personne faitPar;

}
