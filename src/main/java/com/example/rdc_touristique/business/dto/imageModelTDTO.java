package com.example.rdc_touristique.business.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class imageModelTDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private String nom_photo;
}
