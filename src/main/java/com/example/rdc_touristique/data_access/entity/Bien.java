package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bien extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type_bien type;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name="aladisposition", referencedColumnName = "id")
    private Aladisposition aladisposition;

    @Column
    private int prix;

    @Column
    private int npmin;

    @Column
    private int npmax;

    @Column
    private int nchambre;

    @Column
    private int nsdb;

    @Column
    private int nwc;

    @Column
    private int superficie;

    @Column(length = 3000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "coordonnee", referencedColumnName = "id", nullable = false)
    private Coordonnee coordonnee;

    @ManyToOne
    @JoinColumn(name = "appartient", nullable = false)
    private Personne appartient;

    @OneToMany(mappedBy = "bienid",cascade = CascadeType.ALL)
    private List<ImageBien> Image;

    @ManyToMany(mappedBy = "likedBien")
    private List<Personne> likes;

    @OneToMany(mappedBy = "idBien",cascade = CascadeType.ALL)
    private List<ContratMisEnLigne> contratMisEnLigneList;

    @OneToMany(mappedBy = "idBien",cascade = CascadeType.ALL)
    private List<ContratLocation> contratLocationList;

    @Column
    private boolean modeActive;


}
