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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate ddj;

    @Column
    private LocalDate dda;

    @Column
    private LocalDate ddd;

    @Column
    private int npersonne;

    @ManyToOne
    @JoinColumn(name = "bien_reserve")
    private Bien bien_reserve;

    @ManyToOne
    @JoinColumn(name = "reserver_par")
    private Personne reserverPar;

    @Column
    private LocalDateTime dateCreation;

}
