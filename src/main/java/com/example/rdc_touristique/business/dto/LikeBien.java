package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LikeBien {

    private PersonneSimplifierDTO personneSimplifierDTO;
    private BienDTO bienDTO;
}
