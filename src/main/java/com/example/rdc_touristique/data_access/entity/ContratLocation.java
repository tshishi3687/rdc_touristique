package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratLocation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate ddDebut;

    @Column
    private LocalDate ddFin;

    @ManyToOne
    @JoinColumn(name = "id_bien", nullable = false)
    private Bien idBien;

    @Column
    private int nPersonneSurLieu;

    @ManyToOne
    private Personne preneur;

    @ManyToOne
    private Personne bailleur;

    @Column
    private boolean enCour;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String entre;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String entre2;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String objet;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String etatLieu;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String loyer;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String duree;

    @Column(length = 1000, nullable = false)
    @Size(min = 20, max = 1000)
    private String  dardl;
}
