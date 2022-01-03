package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "bienLie",cascade = CascadeType.ALL)
    private List<BienMisEnLigne> dureeOnLine;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name="aladisposition", referencedColumnName = "id")
    private Aladisposition aladisposition;

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

    @Column(length = 1000)
    private String description;


    @ManyToOne
    @JoinColumn(name = "coordonnee", referencedColumnName = "id")
    private Coordonnee coordonnee;

    @ManyToOne
    @JoinColumn(name = "appartient")
    private Personne appartient;

    @Column
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "bienReservee",cascade = CascadeType.ALL )
    private List<Reservation> deReservation;

    @OneToMany(mappedBy = "bienid",cascade = CascadeType.ALL)
    private List<ImageBien> Image;

    @ManyToMany(mappedBy = "likedBien")
    private List<Personne> likes;

    @OneToMany(mappedBy = "bienLie")
    private List<BienMisEnLigne> bienOnline;

    @Column
    private boolean modeActive;

    @Column
    private LocalDate dateFinMisEnLigne;
}
