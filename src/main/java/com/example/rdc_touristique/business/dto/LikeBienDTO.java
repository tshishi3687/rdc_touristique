package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LikeBienDTO {

    private PersonneSimplifierDTO personneSimplifierDTO;
    private BienDTO bienDTO;
}
