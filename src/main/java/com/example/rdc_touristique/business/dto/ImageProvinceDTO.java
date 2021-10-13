package com.example.rdc_touristique.business.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageProvinceDTO implements IdentifiedDTO<Integer> {
    private byte[] picByte;
    private String type;
    private String Name;
    private ProvinceDTO provinceID;

    @Override
    public Integer getId() {
        return null;
    }
}
