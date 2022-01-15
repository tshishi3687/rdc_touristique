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
public class Service extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "coordonnee")
    private Coordonnee coordonnee;

    @Column
    private LocalDateTime dateCreation;
}
