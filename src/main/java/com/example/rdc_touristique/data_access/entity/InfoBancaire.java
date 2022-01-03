package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nomBanque;

    @Column
    private String numCarte;

    @Column
    private String numCompte;

    @Column
    private LocalDate dateExpiration;

    @OneToOne
    @JoinColumn(name = "appartien_a")
    private Personne appartienA;
}
