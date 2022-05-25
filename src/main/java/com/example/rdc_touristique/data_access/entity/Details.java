package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    @Id
    private String id;
    @Column
    private String payerId;
    @Column
    private String createTime;
    @Column
    private String updateTime;
    @Column
    private String intent;
    @Column
    private String status;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String adressLine1;
    @Column
    private String adminArea1;
    @Column
    private String adminArea2;
    @Column
    private String countryCode;
    @Column
    private String postalcode;

}
