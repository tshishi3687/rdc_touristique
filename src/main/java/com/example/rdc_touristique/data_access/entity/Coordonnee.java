package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordonnee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "ville")
    private Ville ville;

    @Column
    private int cpostal;

    @Column
    private String rue;

    @Column
    private int num;

    @Column
    private String email;

    @Column
    private int telephone;

    @Column
    private LocalDateTime dateCreation;

    @Column
    private int superid;
}
