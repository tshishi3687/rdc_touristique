package com.example.rdc_touristique.business.dto;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageModelDTO implements IdentifiedDTO<Integer> {
    private Integer Id;
    private String Name;
    private String type;
    private byte[] picByte;
}
