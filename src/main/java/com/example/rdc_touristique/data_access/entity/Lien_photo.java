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
public class Lien_photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "img")
    private ImageModel image;

    @ManyToOne
    @JoinColumn(name = "province")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "ville")
    private Ville ville;

    @ManyToOne
    @JoinColumn(name = "bien")
    private Bien bien;


}
