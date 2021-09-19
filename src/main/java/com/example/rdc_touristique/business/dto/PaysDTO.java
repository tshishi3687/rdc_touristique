package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaysDTO {

    private int id;
    private int code;
    private String alpha2;
    private String alpha3;
    private String 	nomEnGb;
    private String nomFrFr;
}
