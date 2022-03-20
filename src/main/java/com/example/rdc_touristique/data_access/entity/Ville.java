package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ville extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nomVille;

    @Column
    private int nhabitant;

    @ManyToOne
    @JoinColumn(name = "province", referencedColumnName = "id", nullable = false)
    private Province province;

    @Column(length = 1000)
    private String desciption;

    @OneToMany(mappedBy = "ville")
    private List<ImageVille> imageVilles;
}
