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
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private LocalDate ddn;

    @Column
    private String mdp;

    @Column
    private int telephone;

    @Column
    private String email;

    @Column
    private String status;

    @Column
    private LocalDateTime dateCreation;

    @Column
    private int superid;

    @OneToMany(mappedBy = "appartient")
    private List<Bien> bien;

    @OneToMany(mappedBy = "reserverPar")
    private List<Reservation> reservation;
}
