package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private String nomtype;
}
