package com.example.rdc_touristique.business.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdressUserDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private String numHabitation;
    private String nomRue;
    private String codePostal;
    private PaysDTO pays;
}
