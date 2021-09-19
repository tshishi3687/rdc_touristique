package com.example.rdc_touristique.business.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocOfficielDTO implements IdentifiedDTO<Integer>{
    private Integer id;
    private byte[] img_byte;
    private String type;
    private String Name;
    private String FileName;
    private int size;
}
