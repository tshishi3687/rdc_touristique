package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type_bien type;

    @Column
    private int prix;

    @Column
    private int npmin;

    @Column
    private int npmax;

    @Column
    private int nchambre;

    @Column
    private int nsdb;

    @Column
    private int nwc;

    @Column
    private int superficie;

    @Column
    private String aladisposition;

    @Column
    private String description;


    @ManyToOne
    @JoinColumn(name = "coordonnee")
    private Coordonnee coordonnee;

    @ManyToOne
    @JoinColumn(name = "appartient")
    private Personne appartient;

    @Column
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "bien_reserve")
    private List<Reservation> deReservation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "BienID")
    private List<ImageBien> Image;
}
