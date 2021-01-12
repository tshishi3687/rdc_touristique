package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "appartient")
    private List<Bien> bien;

    @OneToMany(mappedBy = "reserver_par")
    private List<Reservation> reservation;
}
