package com.example.rdc_touristique.business.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DureeLocationDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private String duree;
    private String desciption;
}
