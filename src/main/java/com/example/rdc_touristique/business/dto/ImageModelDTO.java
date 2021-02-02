package com.example.rdc_touristique.business.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageModelDTO {

    private int id;
    private String name;
    private String type;
    private byte[] picByte;
}
