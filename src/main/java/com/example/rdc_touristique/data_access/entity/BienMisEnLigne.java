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

public class BienMisEnLigne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bienLie")
    private Bien bienLie;

    @OneToOne( cascade = CascadeType.ALL )
    private Contrat contratBienMisEnLigne;
}
