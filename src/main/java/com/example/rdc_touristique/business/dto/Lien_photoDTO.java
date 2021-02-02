package com.example.rdc_touristique.business.dto;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lien_photoDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private ImageModelDTO image;
    private ProvinceDTO province;
    private VilleDTO ville;
    private BienDTO bien;

}
