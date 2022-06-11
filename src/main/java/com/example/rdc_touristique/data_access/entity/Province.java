package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Province extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nomprovince;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "provinceID")
    @ToString.Exclude
    private List<ImageProvince> imageProvinces;

    @OneToMany(mappedBy = "province")
    @ToString.Exclude
    private List<Ville> villeList;
}
