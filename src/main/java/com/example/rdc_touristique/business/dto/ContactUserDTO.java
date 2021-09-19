package com.example.rdc_touristique.business.dto;

import com.example.rdc_touristique.data_access.entity.Personne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactUserDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String email;
    private String telephone;
    private PersonneSimplifierDTO appartienA;

}