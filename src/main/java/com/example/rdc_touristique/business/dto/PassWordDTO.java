package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PassWordDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String mdp;
    private boolean mode;
    private PersonneSimplifierDTO appartienA;
}
