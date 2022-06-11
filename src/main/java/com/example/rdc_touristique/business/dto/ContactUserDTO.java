package com.example.rdc_touristique.business.dto;

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
