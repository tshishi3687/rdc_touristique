package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdressUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String numHabitation;

    @Column
    private String nomRue;

    @Column
    private String CodePostal;

    @ManyToOne
    @JoinColumn(name = "pays", referencedColumnName = "id")
    private Pays pays;

    @ManyToOne
    @JoinColumn(name = "adressUser")
    private Personne appartienA;


}
