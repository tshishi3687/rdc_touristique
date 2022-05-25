package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailsDTO {
    private String id;
    private String payerId;
    private String createTime;
    private String updateTime;
    private String intent;
    private String status;
    private String nom;
    private String prenom;
    private String email;
    private String adressLine1;
    private String adminArea1;
    private String adminArea2;
    private String countryCode;
    private String postalcode;
}
