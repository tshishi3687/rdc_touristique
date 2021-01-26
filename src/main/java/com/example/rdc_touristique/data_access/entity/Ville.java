package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom_ville;

    @Column
    private int nhabitant;

    @OneToOne
    @JoinColumn(name = "province")
    private Province province;

    @Column
    private String desciption;
}
