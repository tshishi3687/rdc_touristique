package com.example.rdc_touristique.business.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageVilleDTO implements IdentifiedDTO<Integer>{
    private byte[] picByte;
    private String type;
    private String Name;
    private VilleDTO VilleDTO;

    @Override
    public Integer getId() {
        return null;
    }
}
