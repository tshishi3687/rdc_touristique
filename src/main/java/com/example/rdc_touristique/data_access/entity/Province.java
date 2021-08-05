package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private String description;

    @Column
    private LocalDateTime dateCreation;

    @Column
    private int superid;
}
