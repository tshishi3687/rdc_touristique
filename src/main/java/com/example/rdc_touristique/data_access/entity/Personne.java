package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private LocalDate ddn;

    @ManyToOne
    @JoinColumn(name = "roll", referencedColumnName = "id")
    private Roll roll;

    @Column(name = "date_creation")
    private LocalDateTime ddj;

    @OneToMany(mappedBy = "appartienA")
    private List<AdressUser> adressUser;

    @OneToMany(mappedBy = "appartienA")
    private List<ContactUser> contactUser;

    @OneToMany(mappedBy = "appartienA")
    private List<PassWord> mdp;

    @OneToMany(mappedBy = "appartienA")
    private List<InfoBancaire> infoBancaires;

    @OneToMany(mappedBy= "personneId")
    private List<DocOfficiel> docOfficiels;

    @OneToMany(mappedBy = "appartient")
    private List<Bien> bien;

    @OneToMany(mappedBy = "faitPar")
    private List<Demande> demande;

    @ManyToMany
    @JoinTable(
            name = "personne_like_bien",
            joinColumns = @JoinColumn(name = "PersonneID"),
            inverseJoinColumns = @JoinColumn(name = "bienID"))
    private List<Bien> likedBien;
}
