package com.example.rdc_touristique.data_access.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Personne extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(length = 50, nullable = false)
    private String prenom;

    @Column(nullable = false)
    private LocalDate ddn;

    @ManyToOne
    @JoinColumn(name = "role_Id", referencedColumnName = "id", nullable = false)
    private RolePersonne roleId;

    @Column
    private String codeActivation;

    @Column
    private boolean active;

    @OneToOne(mappedBy = "appartienA")
    private AdressePersonne adresse;

    @OneToOne(mappedBy = "appartienA")
    private ContactPersonne contactUser;

    @OneToOne(mappedBy = "appartienA")
    private PassWord mdp;

    @OneToOne(mappedBy = "appartienA")
    private InfoBancairePersonne infoBancaires;

    @OneToMany(mappedBy= "personneId")
    @ToString.Exclude
    private List<DocOfficielPersonne> docOfficiels;

    @OneToMany(mappedBy = "appartient")
    @ToString.Exclude
    private List<Bien> bien;

    @OneToMany(mappedBy = "preneur")
    @ToString.Exclude
    private List<ContratMisEnLigne> contratsPreneur;

    @OneToMany(mappedBy = "bailleur")
    @ToString.Exclude
    private List<ContratMisEnLigne> contratsBailleur;

    @ManyToMany
    @JoinTable(
            name = "personne_like_bien",
            joinColumns = @JoinColumn(name = "PersonneID"),
            inverseJoinColumns = @JoinColumn(name = "bienID"))
    @ToString.Exclude
    private List<Bien> likedBien;
}
