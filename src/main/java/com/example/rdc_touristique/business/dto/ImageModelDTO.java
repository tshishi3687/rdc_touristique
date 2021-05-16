package com.example.rdc_touristique.business.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageModelDTO implements IdentifiedDTO<Integer> {

    private Integer id;
    private String image;
}
