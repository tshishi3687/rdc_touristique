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
public class PassWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String mdp;

    @Column
    private boolean mode;

    @OneToOne
    @JoinColumn(name = "appartien_a")
    private Personne appartienA;
}
