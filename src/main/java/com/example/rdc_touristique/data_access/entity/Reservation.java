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

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column
    private LocalDate dateArrivee;

    @Column
    private LocalDate dateDepart;

    @Column
    private int nPersonneSurLieu;

    @OneToOne( cascade = CascadeType.ALL )
    private Contrat contratLieeReservation;

    @ManyToOne
    @JoinColumn(name  = "bienReservee")
    private Bien bienReservee;

}
