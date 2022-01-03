package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nomprovince;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "provinceID")
    private List<ImageProvince> imageProvinces;


//    @Column
//    private LocalDateTime dateCreation;
//
//    @Column
//    private int superid;
}
