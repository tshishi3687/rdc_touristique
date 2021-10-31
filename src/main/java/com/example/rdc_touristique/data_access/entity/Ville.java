package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String nomVille;

    @Column
    private int nhabitant;

    @OneToOne
    @JoinColumn(name = "province")
    private Province province;

    @Column
    private String desciption;

//    @Column
//    private LocalDateTime dateCreation;
//
//    @Column
//    private int superid;
}
